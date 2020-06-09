package it.bondicomella.lido.utente.controller;

import it.bondicomella.lido.ConnectionDB;
import it.bondicomella.lido.utente.model.Utente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UtenteController {

    Connection conn;

    public UtenteController() throws Exception {
        this.conn = new ConnectionDB().connect();
    }

    public List<Utente> getListaUtenti() throws Exception {
        PreparedStatement query = this.conn.prepareStatement("SELECT * FROM utente");
        List<Utente> utenti = new ArrayList<>();
        try{
            ResultSet rs = query.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String cognome = rs.getString("cognome");
                String email = rs.getString("email");
                String ruolo = rs.getString("ruolo");

                Utente ut = new Utente();
                ut.setId(id);
                ut.setNome(nome);
                ut.setCognome(cognome);
                ut.setEmail(email);
                ut.setRuolo(ruolo);

                utenti.add(ut);
            }
        }catch (SQLException e){
            throw new SQLException();
        }
        return utenti;
    }

    public boolean doLogin(String email, String password) throws SQLException {
        PreparedStatement query = this.conn.prepareStatement("SELECT utente.email, utente.password FROM utente WHERE email= ? and password = ? ");
        query.setString(1,email);
        query.setString(2,password);
        ResultSet rs = query.executeQuery();
        return rs.next();
    }

}
