package it.bondicomella.lido.biglietteria.controller;

import it.bondicomella.lido.ConnectionDB;
import it.bondicomella.lido.biglietteria.model.Postazione;

import java.net.PortUnreachableException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostazioneController {

    Connection conn;

    public PostazioneController() throws SQLException {
        this.conn = new ConnectionDB().connect();
    }

    public List<Postazione> getSchemaPostazioni() throws SQLException {
        PreparedStatement query = this.conn.prepareStatement("SELECT * FROM postazione");
        List<Postazione> postazioni = new ArrayList<>();
        try{
            ResultSet rs = query.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String stato = rs.getString("stato");
                Postazione pt = new Postazione(id, stato);
                postazioni.add(pt);
            }
        }catch (SQLException e){
            throw new SQLException();
        }

        return postazioni;
    }

    public Postazione getPostazioneById(String idPostazione) throws SQLException {
        String query = "SELECT * FROM postazione WHERE id = ?";
        PreparedStatement preparedQuery = this.conn.prepareStatement(query);
        preparedQuery.setString(1, idPostazione);
        Postazione pt = null;
        try {
            ResultSet rs = preparedQuery.executeQuery();
            if(rs.next()) {
                int id = rs.getInt("id");
                String stato = rs.getString("stato");
                pt = new Postazione(id, stato);
            }
        } catch (SQLException e) {
            throw new SQLException();
        }

        return pt;
    }

    public boolean occupaPostazione(Postazione postazione) throws SQLException {
        String qr = "UPDATE postazione SET stato = ? where id = ?";
        PreparedStatement query = this.conn.prepareStatement(qr);
        query.setString(1, Postazione.OCCUPATA);
        query.setInt(2, postazione.getId());
        try{
            int result = query.executeUpdate();
        }catch (SQLException e){
            throw new SQLException();
        }
        return true;
    }

    public boolean liberaPostazione(Postazione postazione) throws SQLException {
        String qr = "UPDATE postazione SET stato = ? where id = ?";
        PreparedStatement query = this.conn.prepareStatement(qr);
        query.setString(1, Postazione.LIBERA);
        query.setInt(2, postazione.getId());
        try{
            int result = query.executeUpdate();
        }catch (SQLException e){
            throw new SQLException();
        }
        return true;
    }

    //TODO gestione prenotazione
    public boolean prenotaPostazione(Postazione postazione) throws SQLException {
        PreparedStatement query = this.conn.prepareStatement("UPDATE stato = ? FROM postazione where id = '?'");
        query.setString(1, Postazione.PRENOTATA);
        query.setInt(2, postazione.getId());
        try{
            int result = query.executeUpdate();
        }catch (SQLException e){
            throw new SQLException();
        }
        return true;
    }
}
