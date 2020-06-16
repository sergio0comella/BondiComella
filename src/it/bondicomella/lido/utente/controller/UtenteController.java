package it.bondicomella.lido.utente.controller;

import it.bondicomella.lido.ConnectionDB;
import it.bondicomella.lido.utente.model.Utente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtenteController {

    Connection conn;

    public UtenteController() throws Exception {
        this.conn = new ConnectionDB().connect();
    }

    private Utente createUtenteFromRS(ResultSet rs) throws SQLException {
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

        return ut;
    }

    public List<Utente> getListaUtenti() throws Exception {

        PreparedStatement query = this.conn.prepareStatement("SELECT * FROM utente");
        List<Utente> utenti = new ArrayList<>();
        try{
            ResultSet rs = query.executeQuery();
            while (rs.next()){
                Utente ut = this.createUtenteFromRS(rs);
                utenti.add(ut);
            }
        }catch (SQLException e){
            throw new SQLException();
        }
        return utenti;
    }

    public Utente getUtenteById(int id) throws SQLException {

        Utente utente = new Utente();
        String query = "SELECT * FROM utente WHERE id = ?";
        PreparedStatement ps = this.conn.prepareStatement(query);
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            utente = this.createUtenteFromRS(rs);
        }

        return utente;
    }

    public Utente getUtenteByEmail(String email) throws SQLException {
        PreparedStatement query = this.conn.prepareStatement("SELECT * FROM utente u WHERE u.email = ?");
        query.setString(1,email);
        ResultSet rs = query.executeQuery();
        if(rs.next()){
            return this.createUtenteFromRS(rs);
        }else{
            return new Utente();
        }
    }

    public void creaUtente(String nome, String cognome, String email, String password,String ruolo) throws SQLException {
        PreparedStatement query = this.conn.prepareStatement("INSERT INTO utente (nome,cognome,email,password,ruolo) " + " VALUES(?,?,?,?,?)");
        query.setString(1,nome);
        query.setString(2,cognome);
        query.setString(3,email);
        query.setString(4,password);
        query.setString(5,ruolo);
        query.execute();
    }

    public boolean checkEmail(String email) throws SQLException {
        PreparedStatement query = this.conn.prepareStatement("SELECT email FROM utente WHERE email = ?");
        query.setString(1,email);
        ResultSet rs = query.executeQuery();
        if(rs.next()) {
            return true;
        }
        return false;
    }

}
