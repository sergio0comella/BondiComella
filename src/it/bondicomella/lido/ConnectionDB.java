package it.bondicomella.lido;

import java.sql.*;

public class ConnectionDB{

    public Connection connect() throws Exception {

        try{
            String url = "jdbc:mysql://localhost:3306/lido_db";
            String user = "root";
            String password = "";

            Class.forName("org.mariadb.jdbc.Driver"); // TODO ERRORE
            Connection conn = DriverManager.getConnection(url, user, password);
            return conn;

        } catch (SQLException | ClassNotFoundException e){
            throw new Exception("Connessione al db non riuscita.");
        }
    }

}