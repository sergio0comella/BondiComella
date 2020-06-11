package it.bondicomella.lido.biglietteria.controller;

import it.bondicomella.lido.ConnectionDB;
import it.bondicomella.lido.biglietteria.model.Prenotazione;
import it.bondicomella.lido.utente.controller.UtenteController;
import it.bondicomella.lido.utente.model.Utente;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrenotazioneController {
    protected Connection conn;

    public PrenotazioneController() throws SQLException {
        this.conn = new ConnectionDB().connect();
    }

    /**
     * Partendo da un resultset delle prenotazioni mi genera un oggetto
     * di tipo Prenotazione
     * @param rs
     * @return
     * @throws SQLException
     */
    private Prenotazione createPrenotazioneFromRS(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int fkIdUtente = rs.getInt("fk_id_utente");
        int fkIdPostazione = rs.getInt("fk_id_postazione");
        boolean pagata = rs.getBoolean("pagata");
        boolean annullata = rs.getBoolean("annullata");
        Timestamp dataOraInizio = rs.getTimestamp("data_ora_inizio");
        Timestamp dataOraFine = rs.getTimestamp("data_ora_fine");

        return new Prenotazione(id, fkIdUtente, fkIdPostazione, pagata, dataOraInizio, dataOraFine, annullata);
    }

    /**
     * Restituisce la lista delle prenotazioni
     *
     * @return
     * @throws SQLException
     */
    public Map<Prenotazione, Utente> getListaPrenotazioni() throws Exception {

        UtenteController utController = new UtenteController();
        Map<Prenotazione, Utente> prenotazioni = new HashMap<>();

        String query = "SELECT * FROM prenotazione ORDER BY data_ora_inizio desc";
        PreparedStatement ps = this.conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            prenotazioni.put(createPrenotazioneFromRS(rs), utController.getUtenteById(rs.getInt("fk_id_utente")));
        }
        return prenotazioni;
    }

    public Prenotazione getPrenotazioneById(int id) throws SQLException {

        String query = "SELECT * FROM prenotazione WHERE id = ?";
        PreparedStatement ps = this.conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return createPrenotazioneFromRS(rs);
        } else {
            return null;
        }
    }


    public void annullaPrenotazione(String id) throws SQLException {

        String query = "UPDATE prenotazione SET annullata = 1 WHERE id = ?";
        PreparedStatement ps = this.conn.prepareStatement(query);
        ps.setString(1, id);
        ps.executeUpdate();

    }

}
