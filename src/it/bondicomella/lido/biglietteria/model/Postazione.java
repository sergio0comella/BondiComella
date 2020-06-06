package it.bondicomella.lido.biglietteria.model;

import java.util.Objects;

public class Postazione {

    private int id;
    private String stato;

    /**
     * TODO
     * Map Stato:
     * L - Libera
     * O - Occupata
     * P - Prenotata
     */

    public Postazione(){

    }

    public Postazione(int id, String stato) {
        this.id = id;
        this.stato = stato;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

}
