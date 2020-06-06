package it.bondicomella.lido.biglietteria.controller;

import it.bondicomella.lido.ConnectionDB;
import it.bondicomella.lido.biglietteria.model.Postazione;
import it.bondicomella.lido.utente.model.Utente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostazioneController {

    Connection conn;

    public PostazioneController() throws Exception {
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
}
