package it.bondicomella.lido.utente.controller;

import it.bondicomella.lido.ConnectionDB;

import java.sql.*;
import java.util.List;

public class UtenteController {

    Connection conn;

    public UtenteController() throws Exception {
        this.conn = new ConnectionDB().connect();
    }

    public List<String> getListaUtenti() throws SQLException {
        PreparedStatement query = this.conn.prepareStatement("SELECT * FROM utenti");
        List<String> utenti = null;
        try{
            ResultSet rs = query.executeQuery();
            while(rs.next()){
                utenti.add(rs.getString(0 ));
            }
        }catch (SQLException e){
            throw new SQLException(); //TODO
        }

        return utenti;
    }
}
