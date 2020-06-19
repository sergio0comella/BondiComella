package it.bondicomella.lido.cucina.model;

public class Menu {
    private int id;
    private String nomePiatto;
    private String costo;
    private boolean attivo ;

    public Menu(){

    }

    public Menu(String nomePiatto, String costo, Boolean attivo) {
        this.nomePiatto = nomePiatto;
        this.costo = costo;
        this.attivo = attivo;
    }


    public String getNomePiatto() {
        return nomePiatto;
    }

    public void setNomePiatto(String nomePiatto) {
        this.nomePiatto = nomePiatto;
    }

    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }

    public boolean isAttivo() {
        return attivo;
    }

    public void setAttivo(boolean attivo) {
        this.attivo = attivo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
