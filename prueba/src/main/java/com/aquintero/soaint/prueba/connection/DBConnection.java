package com.aquintero.soaint.prueba.connection;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection
{
    private static final Logger logger = Logger.getLogger(DBConnection.class.getName());

    private static Connection conn = null;
    protected Statement stmt;
    protected String msgError = "";
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:mem:testdb";
    //  Database credentials
    static final String USER = "soaint";
    static final String PASS = "soaint";


    public DBConnection()
    {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            String sql =  "DROP TABLE IF EXISTS logs_value; " +
                    "CREATE TABLE logs_value " +
                    "(mensaje VARCHAR(500) not null, " +
                    " tipo VARCHAR(1) not null)";
            stmt.executeUpdate(sql);
            stmt.close();
        }catch(Exception e){
            logger.log(Level.SEVERE, e.getMessage());
        }
    }



    public static Connection getConnection(){

        if (conn == null){
            new DBConnection();
        }
        return conn;
    }

    /**Mtodo que verifica si la conexin con la base de datos est abierta.
     @return retorna true si la conexin est abierta de lo contrario retorna false*/
    public boolean hayConexion(){
        if(conn!=null){
            return true;
        }
        else{
            return false;
        }
    }


    /**Mtodo que se encarga de cerrar la conexin con la base de datos */
    public void desconectar() {
        if(this.hayConexion()){
            try{
                conn.close();
                conn=null;
            }

            catch(Exception e){
            }

            finally{
                if(conn!=null){
                    try{conn.close();}
                    catch(SQLException e){}
                    conn = null;
                }
            }
        }
    }


    public String getMsgError() {
        return msgError;
    }

    public Connection getConn() {
        return conn;
    }


}
