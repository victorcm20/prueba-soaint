package com.aquintero.soaint.prueba.util;

import com.aquintero.soaint.prueba.connection.DBConnection;
import com.aquintero.soaint.prueba.daofactory.DaoFactory;
import com.aquintero.soaint.prueba.dto.LogValuesDto;
import com.aquintero.soaint.prueba.entity.Logs;
import com.aquintero.soaint.prueba.entity.User;
import com.aquintero.soaint.prueba.idao.ILogValueDAO;
import com.aquintero.soaint.prueba.repository.LogsRepositry;
import com.aquintero.soaint.prueba.repository.UserRepository;

import java.io.*;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;

public class LogFileUtil
{

    private static final Logger logger = Logger.getLogger(LogFileUtil.class.getName());

    private ResourceBundle bundle = ResourceBundle.getBundle("application");

    public void insertLogMessage(DBConnection conn, Logger logger, String messageText, Boolean message, Boolean warning, Boolean error,
                                   Boolean logError, Boolean logWarning, Boolean logMessage, Boolean logToFile,
                                   Boolean logToConsole, Boolean logToDatabase, User user, LogsRepositry logsRepositry)
        throws IOException
    {
        ILogValueDAO logValueDAO = DaoFactory.getInstance().getILogValueDAO();
        String msg = new String();

        File logFile = ResourceUtils.getFile("classpath:logFileFolder/logFile.txt");
        logFile.delete();
        if (!logFile.exists()) {
            logFile.createNewFile();
        }
        FileHandler fh = new FileHandler(bundle.getString("nameFile"));
        ConsoleHandler ch = new ConsoleHandler();
        if (message && logMessage) {
            showTypeLogs(logFile, logger, logToFile, logToConsole, getConcatMessageText(messageText, "message "), ch);
            insertLogsValues(conn, logToDatabase,
                    logValueDAO, getConcatMessageText(messageText, "message "), "1", user, logsRepositry);
        }
        if (error && logError) {
            showTypeLogs(logFile, logger, logToFile, logToConsole, getConcatMessageText(messageText, "error "), ch);
            insertLogsValues(conn, logToDatabase,
                            logValueDAO, getConcatMessageText(messageText, "error "), "2", user, logsRepositry);
        }
        if (warning && logWarning) {
            showTypeLogs(logFile, logger, logToFile, logToConsole, getConcatMessageText(messageText, "warning "), ch);
            insertLogsValues(conn, logToDatabase,
                             logValueDAO, getConcatMessageText(messageText, "warning "), "3", user, logsRepositry);
        }


        //metodo adicional para imprimir lo q se registro en h2 database
        printContentFile(logFile);
        printAllInfoTableLogsValue(conn, logValueDAO);
    }

    private void showTypeLogs(File logFile, Logger logger, Boolean logToFile, Boolean logToConsole,
                              String msg, ConsoleHandler ch)
    {
        if(logToFile) {
            writeFile(logFile, msg);
        }
        if(logToConsole) {
            logger.addHandler(ch);
            logger.log(Level.INFO, msg);
        }
    }

    private void printContentFile(File logFile)
    {
        Scanner s = null;
        try {
            // Leemos el contenido del fichero
            System.out.println("****************************** CONTENIDO DEL FICHERO ***********************************");
            s = new Scanner(logFile);
            // Leemos linea a linea el fichero
            while (s.hasNextLine()) {
                String linea = s.nextLine(); 	// Guardamos la linea en un String
                System.out.println(linea);      // Imprimimos la linea
            }
            System.out.println("****************************************************************************************");
        } catch (Exception ex) {
            System.out.println("Mensaje: " + ex.getMessage());
        } finally {
            // Cerramos el fichero tanto si la lectura ha sido correcta o no
            try {
                if (s != null)
                    s.close();
            } catch (Exception ex2) {
                System.out.println("Mensaje 2: " + ex2.getMessage());
            }
        }
    }

    private void writeFile(File logFile, String msg)
    {
        FileWriter fichero = null;
        try {
            fichero = new FileWriter(logFile, true);
            // Escribimos linea a linea en el fichero
            fichero.write(msg);
            fichero.close();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage());
        }
    }

    private String getConcatMessageText(String messageText, String s) {
        return s.concat(DateFormat.getDateInstance(DateFormat.LONG).format(new Date())).concat(" - ").concat(messageText).concat("\n");
    }

    private void insertLogsValues(DBConnection conn, Boolean logToDatabase, ILogValueDAO logValueDAO, String msg, String tipo, User user, LogsRepositry logsRepositry) {
        if(logToDatabase) {
        	Logs log = new Logs();
        	log.setMessage(msg);
        	log.setType(tipo);
        	log.setUser(user);
        	logsRepositry.save(log);
        }
    }

    private void printAllInfoTableLogsValue(DBConnection conn, ILogValueDAO logValueDAO) {
        List<LogValuesDto> allLogValues = logValueDAO.allLogValues(conn);
        System.out.println("\n***************************** RECORDS LOGS_VALUE ***************************************");
        allLogValues.forEach(p -> System.out.println("MENSAJE = " + p.getMessage() + ", TIPO = " + p.getTipo()));
        System.out.println("****************************************************************************************");
    }



}
