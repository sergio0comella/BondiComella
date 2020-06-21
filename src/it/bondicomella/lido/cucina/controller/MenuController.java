package it.bondicomella.lido.cucina.controller;

import it.bondicomella.lido.ConnectionDB;
import it.bondicomella.lido.cucina.model.Menu;
import it.bondicomella.lido.utente.model.Utente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class MenuController {

    Connection conn;

    public MenuController() throws Exception {
        this.conn = new ConnectionDB().connect();
    }

    private Menu createMenuFromRS(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String nomePiatto = rs.getString("piatto");
        String costo = rs.getString("costo");
        Boolean attivo= rs.getBoolean("attivo");

        Menu m = new Menu();
        m.setId(id);
        m.setNomePiatto(nomePiatto);
        m.setCosto(costo);
        m.setAttivo(attivo);
        return m;
    }

    public List<Menu> getMenu() throws Exception {

        PreparedStatement query = this.conn.prepareStatement("SELECT * FROM menu");
        return getMenus(query);
    }

    public List<Menu> getMenuDelGiorno() throws SQLException{
        PreparedStatement query = this.conn.prepareStatement("SELECT * FROM menu WHERE attivo = ?");
        query.setString(1, String.valueOf(1));
        return getMenus(query);
    }

    private List<Menu> getMenus(PreparedStatement query) throws SQLException {
        List<Menu> menuDelGiorno = new ArrayList<>();
        try{
            ResultSet rs = query.executeQuery();
            while (rs.next()){
                Menu m= this.createMenuFromRS(rs);
                menuDelGiorno.add(m);
            }
        }catch (SQLException e){
            throw new SQLException();
        }
        return menuDelGiorno;
    }

    public Menu getMenuById(int id) throws SQLException {

        Menu menu = new Menu();
        String query = "SELECT * FROM menu WHERE id = ?";
        PreparedStatement ps = this.conn.prepareStatement(query);
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            menu = this.createMenuFromRS(rs);
        }

        return menu;
    }

    public void modificaMenu(String[] dataActive, String[] dataNotActive) throws SQLException {
        int i = 0;

        for(i = 0; i < dataActive.length; i++) { ;
            PreparedStatement query1 = this.conn.prepareStatement("UPDATE menu SET attivo = '1' WHERE id = ? ");
            query1.setString(1,(dataActive[i]));
            query1.execute();
        }
        for(i = 0; i < dataNotActive.length; i++) { ;
            PreparedStatement query2 = this.conn.prepareStatement("UPDATE menu SET attivo = '0' WHERE id = ? ");
            query2.setString(1,(dataNotActive[i]));
            query2.execute();
        }
    }

    public void aggiungiPiatto(String nomePiatto, String costo) throws SQLException {
            PreparedStatement query = this.conn.prepareStatement("INSERT INTO menu (piatto,costo,attivo) VALUES(?,?,1) ");
            query.setString(1, nomePiatto);
            query.setString(2, costo);
            query.execute();
    }

    public boolean controlloPiattoDuplicato(String nomePiatto) throws SQLException{
        PreparedStatement query = this.conn.prepareStatement("SELECT piatto FROM menu WHERE piatto = ?");
        query.setString(1,nomePiatto);
        ResultSet rs = query.executeQuery();
        if(rs.next()){
            return true;
        }
        return false;
    }
}
