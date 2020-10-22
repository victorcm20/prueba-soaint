package com.aquintero.soaint.prueba.dao;

import com.aquintero.soaint.prueba.connection.DBConnection;
import com.aquintero.soaint.prueba.dto.LogValuesDto;
import com.aquintero.soaint.prueba.idao.ILogValueDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class LogValueDAO implements ILogValueDAO
{

    private static final Logger logger = Logger.getLogger(LogValueDAO.class.getName());

    @Override
    public void insertLogValue(DBConnection con, String messageText, String type)
    {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean res = false;
        try {

                conn = con.getConn();
                ps = conn.prepareStatement("INSERT INTO logs_value (mensaje, tipo) VALUES (?, ?)");
                ps.setString(1, messageText);
                ps.setString(2, type);
                ps.executeUpdate();
            ps.close();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage());
        }
    }

    @Override
    public List<LogValuesDto> allLogValues(DBConnection con)
    {
        List<LogValuesDto> lista = new ArrayList();
        LogValuesDto logValuesDto = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

                conn = con.getConn();
                ps = conn.prepareStatement("SELECT MENSAJE, TIPO FROM LOGS_VALUE");
                rs = ps.executeQuery();
                while(rs.next()){
                    logValuesDto = new LogValuesDto();
                    logValuesDto.setMessage(rs.getString("MENSAJE").trim());
                    logValuesDto.setTipo(rs.getString("TIPO").trim());
                    lista.add(logValuesDto);
                }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage());
        }
        return lista;
    }
}
