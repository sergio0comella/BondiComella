package it.bondicomella.lido.biglietteria.controller;

import it.bondicomella.lido.ConnectionDB;
import it.bondicomella.lido.biglietteria.model.Postazione;
import it.bondicomella.lido.biglietteria.model.Prenotazione;
import it.bondicomella.lido.utente.controller.UtenteController;
import it.bondicomella.lido.utente.model.Utente;
import it.bondicomella.lido.util.Mailer;

import java.sql.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.SimpleFormatter;

public class PrenotazioneController {
    protected Connection conn;

    public PrenotazioneController() throws SQLException {
        this.conn = new ConnectionDB().connect();
    }

    /**
     * Partendo da un resultset delle prenotazioni mi genera un oggetto
     * di tipo Prenotazione
     *
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
        Date dataPrenotazione = rs.getDate("data_prenotazione");
        Time oraInizio = rs.getTime("ora_inizio");
        Time oraFine = rs.getTime("ora_fine");
        String codicePrentonazione = rs.getString("codice_prenotazione");

        return new Prenotazione(id, fkIdUtente, fkIdPostazione, pagata, dataPrenotazione, oraInizio, oraFine, annullata, codicePrentonazione);
    }

    private String generateCodePrenotazione(){
       return UUID.randomUUID().toString().substring(0, 8);
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

    public Prenotazione getPrenotazioneById(String id) throws SQLException {

        String query = "SELECT * FROM prenotazione WHERE id = ?";
        PreparedStatement ps = this.conn.prepareStatement(query);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return createPrenotazioneFromRS(rs);
        } else {
            return null;
        }
    }


    public void annullaPrenotazione(String id) throws SQLException {
        try {

            this.conn.setAutoCommit(false);

            /** Recupero la prenotazione a partire dall'id **/
            Prenotazione prenotazione = this.getPrenotazioneById(id);

            /** Recupero la postazione relativa alla prenotazione a partire dalla FK**/
            PostazioneController postController = new PostazioneController();
            Postazione postazione = postController.getPostazioneById(String.valueOf(prenotazione.getFkIdPostazione()));

            /** Aggiorno la prenotazione e la imposto ad annullata**/
            String query = "UPDATE prenotazione SET annullata = 1 WHERE id = ?";
            PreparedStatement ps = this.conn.prepareStatement(query);
            ps.setString(1, id);

            /** Aggiorno lo stato della postazione a L (libera) **/
            String querySecond = "UPDATE postazione SET stato = 'L' WHERE id = ?";
            PreparedStatement psSecond = this.conn.prepareStatement(querySecond);
            psSecond.setInt(1, postazione.getId());

            ps.executeUpdate();
            psSecond.executeUpdate();

            this.conn.commit();

        } catch (SQLException e) {
            System.out.println("Errore nella commit:" + e.getSQLState());
            this.conn.rollback();
        }

    }

    public Prenotazione addNewPrenotazione(Prenotazione prenotazione, String emailUtente) throws SQLException {

        try {
            this.conn.setAutoCommit(false);

            /** Creo un codice identificativo per la prenotazione **/
            String codice = this.generateCodePrenotazione();
            prenotazione.setCodicePrenotazione(codice);

            String query = "INSERT INTO prenotazione(fk_id_utente, fk_id_postazione, pagata, data_prenotazione, ora_inizio, ora_fine, annullata, codice_prenotazione) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
            /**
             * Inserisco la prenotazione
             */
            PreparedStatement ps = this.conn.prepareStatement(query);
            ps.setInt(1, prenotazione.getFkIdUtente());
            ps.setInt(2, prenotazione.getFkIdPostazione());
            ps.setBoolean(3, prenotazione.isPagata());
            ps.setDate(4, prenotazione.getDataPrenotazione());
            ps.setTime(5, prenotazione.getOraInizio());
            ps.setTime(6, prenotazione.getOraFine());
            ps.setBoolean(7, false);
            ps.setString(8, prenotazione.getCodicePrenotazione());

            ps.execute();

            /**
             * Aggiorno lo stato della postazione a L (libera)
             */
            String querySecond = "UPDATE postazione SET stato = 'P' WHERE id = ?";
            PreparedStatement psSecond = this.conn.prepareStatement(querySecond);
            psSecond.setInt(1, prenotazione.getFkIdPostazione());
            psSecond.executeUpdate();

            this.conn.commit();

        } catch (SQLException e) {
            System.out.println("Errore nella commit addNewPrenotazione: " + e.getSQLState());
            this.conn.rollback();
        }

        return prenotazione;
    }




}
