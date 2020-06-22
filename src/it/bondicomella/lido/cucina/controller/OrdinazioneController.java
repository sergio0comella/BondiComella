package it.bondicomella.lido.cucina.controller;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import it.bondicomella.lido.ConnectionDB;
import it.bondicomella.lido.cucina.model.Menu;
import it.bondicomella.lido.cucina.model.Ordine;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class OrdinazioneController {

    Connection conn;

    public OrdinazioneController() throws Exception {
        this.conn = new ConnectionDB().connect();
    }

    private Ordine createOrdineFromRS(ResultSet rs) throws SQLException {

        int id = rs.getInt("id");
        String stato = rs.getString("stato");
        String quantita = rs.getString("quantita");
        int fkUtente = rs.getInt("fk_id_utente");
        int fkMenu = rs.getInt("fk_id_menu");
        Time ora = rs.getTime("ora");

        Ordine o = new Ordine();
        o.setId(id);
        o.setStato(stato);
        o.setQuantita(quantita);
        o.setIdMenu(fkMenu);
        o.setIdUtente(fkUtente);
        o.setOra(ora);
        return o;
    }

    public List<Ordine> getListOrdini() throws Exception {

        PreparedStatement query = this.conn.prepareStatement("SELECT * FROM ordine WHERE stato = 1");
        return formatListOrdini(query);
    }

    private List<Ordine> formatListOrdini(PreparedStatement query) throws SQLException {
        List<Ordine> ordine = new ArrayList<>();
        try{
            ResultSet rs = query.executeQuery();
            while (rs.next()){
                Ordine o = this.createOrdineFromRS(rs);
                ordine.add(o);
            }
        }catch (SQLException e){
            throw new SQLException();
        }
        return ordine;
    }

    public void effettuaOrdinazione(JsonObject ordine, int idUtente) throws SQLException {
        this.conn.setAutoCommit(false);

        for(Map.Entry<String,JsonElement> entry : ordine.entrySet()){
            PreparedStatement query = this.conn.prepareStatement("INSERT INTO ordine (fk_id_utente,fk_id_menu,quantita,stato,ora) VALUES(?,?,?,?,current_time)");
            query.setInt(1, idUtente);
            query.setString(2, entry.getKey());
            query.setInt(3, entry.getValue().getAsInt());
            query.setInt(4, 1);
            query.execute();
        }
        this.conn.commit();
    }

    public void completaOrdinazione(String id) throws SQLException {
        PreparedStatement query = this.conn.prepareStatement("UPDATE ordine SET stato = '0' WHERE id = ? ");
        query.setString(1, id);
        query.execute();

    }

    public Ordine getTheFirstOrdine() throws SQLException {
        PreparedStatement query = this.conn.prepareStatement("SELECT * FROM ordine WHERE ora =(SELECT min(ora) FROM ordine where stato=1 ) ");
        ResultSet rs = query.executeQuery();
        if(rs.next()) {
            Ordine o = createOrdineFromRS(rs);
            System.out.println(o);
            return o;
        }
        return null;
    }

}
