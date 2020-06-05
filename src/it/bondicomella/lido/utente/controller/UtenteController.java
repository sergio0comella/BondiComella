package it.bondicomella.lido.utente.controller;

import it.bondicomella.lido.ConnectionDB;
import it.bondicomella.lido.Util;
import org.json.JSONArray;

import java.sql.*;

public class UtenteController {

    Connection conn;
    Util util;

    public UtenteController() throws Exception {
        this.conn = new ConnectionDB().connect();
        this.util = new Util();
    }

    public JSONArray getListaUtenti() throws Exception {
        PreparedStatement query = this.conn.prepareStatement("SELECT * FROM utente");
        JSONArray utenti;
        try{
            ResultSet rs = query.executeQuery();
            utenti = util.convert(rs);
        }catch (Exception e){
            throw new Exception(); //TODO
        }

        return utenti;
    }
}
