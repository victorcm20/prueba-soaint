package com.aquintero.soaint.prueba.service;

import com.aquintero.soaint.prueba.connection.DBConnection;
import com.aquintero.soaint.prueba.entity.User;
import com.aquintero.soaint.prueba.repository.LogsRepositry;
import com.aquintero.soaint.prueba.util.LogFileUtil;
import java.util.Objects;
import java.util.logging.Logger;

public class DemoServiceImpl
{

    private static final Logger logger = Logger.getLogger(DemoServiceImpl.class.getName());
    private boolean logToFile;
    private boolean logToConsole;
    private boolean logMessage;
    private boolean logWarning;
    private boolean logError;
    private boolean logToDatabase;
    private boolean initialized;
    protected DBConnection conn;
    private LogFileUtil logFileUtil;

    public DemoServiceImpl(Boolean logToFile, Boolean logToConsole, Boolean logMessage, Boolean logWarning,
                           Boolean logError, Boolean logToDatabase)
    {
        this.logToFile = logToFile;
        this.logToConsole = logToConsole;
        this.logMessage = logMessage;
        this.logWarning = logWarning;
        this.logError = logError;
        this.logToDatabase = logToDatabase;
    }

    public void logMessage(String messageText, boolean message, boolean warning, boolean error, User user, LogsRepositry logsRepositry)
            throws Exception
    {
        logFileUtil = new LogFileUtil();
        conn = new DBConnection();

        if (validateMessageIsNullOrEmpty(messageText)) return;
        validateIfThereExceptions(message, warning, error);
        logFileUtil.insertLogMessage(conn, logger, messageText, message, warning, error,
                                     logError, logWarning, logMessage, logToFile, logToConsole, logToDatabase, user, logsRepositry);
        conn.desconectar();
    }

    private boolean validateMessageIsNullOrEmpty(String messageText)
    {
        return Objects.isNull(messageText) || messageText.isEmpty();
    }

    private void validateIfThereExceptions(boolean message, boolean warning, boolean error)
            throws Exception
    {
        if (!logToConsole && !logToFile && !logToDatabase) {
            throw new Exception("Invalid configuration");
        }
        if ((!logError && !logMessage && !logWarning) || (!message && !warning && !error)) {
            throw new Exception("Error or Warning or Message must be specified");
        }
    }

    public static Logger getLogger() {
        return logger;
    }

    public boolean isLogToFile() {
        return logToFile;
    }

    public void setLogToFile(boolean logToFile) {
        this.logToFile = logToFile;
    }

    public boolean isLogToConsole() {
        return logToConsole;
    }

    public void setLogToConsole(boolean logToConsole) {
        this.logToConsole = logToConsole;
    }

    public boolean isLogMessage() {
        return logMessage;
    }

    public void setLogMessage(boolean logMessage) {
        this.logMessage = logMessage;
    }

    public boolean isLogWarning() {
        return logWarning;
    }

    public void setLogWarning(boolean logWarning) {
        this.logWarning = logWarning;
    }

    public boolean isLogError() {
        return logError;
    }

    public void setLogError(boolean logError) {
        this.logError = logError;
    }

    public boolean isLogToDatabase() {
        return logToDatabase;
    }

    public void setLogToDatabase(boolean logToDatabase) {
        this.logToDatabase = logToDatabase;
    }

    public boolean isInitialized() {
        return initialized;
    }

    public void setInitialized(boolean initialized) {
        this.initialized = initialized;
    }
}