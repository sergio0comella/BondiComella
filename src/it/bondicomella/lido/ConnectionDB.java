package it.bondicomella.lido;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

public class ConnectionDB{

    public Connection connect() throws SQLException {

        try{

            Context context = new InitialContext();
            DataSource dataSource = (DataSource)context.lookup("java:comp/env/jdbc/bondicomella");
            return dataSource.getConnection();

        } catch (SQLException | NamingException e){
            throw new SQLException("Connessione al db non riuscita.");
        }
    }

}