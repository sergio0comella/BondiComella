package it.bondicomella.lido.biglietteria.model;

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
    public static String OCCUPATA = "O";
    public static String LIBERA = "L";
    public static String PRENOTATA = "P";

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
