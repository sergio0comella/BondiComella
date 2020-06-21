package it.bondicomella.lido.biglietteria.controller;

import it.bondicomella.lido.ConnectionDB;
import it.bondicomella.lido.biglietteria.model.Postazione;

import java.net.PortUnreachableException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PostazioneController {

    protected Connection conn;

    public PostazioneController() throws SQLException {
        this.conn = new ConnectionDB().connect();
    }

    /**
     * Fix per allineare lo stato delle postazioni sulla base delle prenotazioni
     * @throws SQLException
     */
    private void updateStatoPostazioni() throws SQLException {
        Calendar currenttime = Calendar.getInstance();
        Date today = new Date((currenttime.getTime()).getTime());
        Time now = new Time((currenttime.getTime()).getTime());

        /**
         * Modifico le postazioni e imposto a prenotate tutte quelle
         * prenotate in data odierna
         */
        String updateGiornPost = "UPDATE postazione INNER JOIN prenotazione " +
                        "ON postazione.id = prenotazione.fk_id_postazione " +
                        "SET postazione.stato = ?" +
                        "WHERE prenotazione.data_prenotazione = ? " +
                        "AND prenotazione.ora_inizio > ?" +
                        "AND postazione.stato <> ? ";

        PreparedStatement psGiornaliere = this.conn.prepareStatement(updateGiornPost);
        psGiornaliere.setString(1, Postazione.PRENOTATA);
        psGiornaliere.setDate(2, today);
        psGiornaliere.setTime(3, now);
        psGiornaliere.setString(4, Postazione.OCCUPATA);

        psGiornaliere.executeUpdate();

    }

    public List<Postazione> getSchemaPostazioni() throws SQLException {
        this.updateStatoPostazioni();
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

    public void occupaPostazione(Postazione postazione) throws SQLException {
        String qr = "UPDATE postazione SET stato = ? where id = ?";
        PreparedStatement query = this.conn.prepareStatement(qr);
        query.setString(1, Postazione.OCCUPATA);
        query.setInt(2, postazione.getId());
        try{
            query.executeUpdate();
        }catch (SQLException e){
            throw new SQLException();
        }
    }

    public void liberaPostazione(Postazione postazione) throws SQLException {
        
        Calendar currenttime = Calendar.getInstance();
        Date today = new Date((currenttime.getTime()).getTime());
        Time now = new Time((currenttime.getTime()).getTime());
        String qrCheck = "SELECT COUNT(*) > 0 AS result FROM prenotazione " +
                            " where fk_id_postazione = ? AND data_prenotazione = ? AND ora_inizio > ?";

        boolean isPrenotata = false;
        PreparedStatement checkPrenotazioni = this.conn.prepareStatement(qrCheck);
        checkPrenotazioni.setInt(1, postazione.getId());
        checkPrenotazioni.setDate(2, today);
        checkPrenotazioni.setTime(3, now);

        ResultSet rs = checkPrenotazioni.executeQuery();
        if(rs.next()){
            isPrenotata = rs.getBoolean("result") ;
        }

        String qr = "UPDATE postazione SET stato = ? where id = ?";
        PreparedStatement query = this.conn.prepareStatement(qr);

        String statoPostazione = isPrenotata ? Postazione.PRENOTATA : Postazione.LIBERA;
        query.setString(1, statoPostazione);

        query.setInt(2, postazione.getId());

        try{
            query.executeUpdate();
        }catch (SQLException e){
            throw new SQLException();
        }
    }

}
