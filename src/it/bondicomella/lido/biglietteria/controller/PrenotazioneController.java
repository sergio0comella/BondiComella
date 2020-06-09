package it.bondicomella.lido.biglietteria.controller;

import it.bondicomella.lido.ConnectionDB;
import it.bondicomella.lido.biglietteria.model.Prenotazione;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrenotazioneController {
    protected Connection conn;

    public PrenotazioneController() throws SQLException {
        this.conn = new ConnectionDB().connect();
    }

    /**
     * Restituisce la lista delle prenotazioni
     * @return
     * @throws SQLException
     */
    public List<Prenotazione> getListaPrenotazioni() throws SQLException {

        List<Prenotazione> prenotazioni = new ArrayList<>();

        String query = "SELECT * FROM prenotazione";
        PreparedStatement ps = this.conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            int id = rs.getInt("id");
            int fkIdUtente = rs.getInt("fk_id_utente");
            int fkIdPostazione = rs.getInt("fk_id_postazione");
            boolean pagata = rs.getBoolean("pagata");
            Timestamp dataOraInizio = rs.getTimestamp("data_ora_inizio");
            Timestamp dataOraFine = rs.getTimestamp("data_ora_fine");
            int stato = rs.getInt("stato");

            Prenotazione pt = new Prenotazione(id, fkIdUtente, fkIdPostazione, pagata, dataOraInizio, dataOraFine, stato);
            prenotazioni.add(pt);
        }

        return prenotazioni;
    }
    
}
