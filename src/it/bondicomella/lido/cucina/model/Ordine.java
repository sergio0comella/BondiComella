package it.bondicomella.lido.cucina.model;

import java.sql.Time;

public class Ordine {

        private int id;
        private int idUtente;
        private int idMenu;
        private String stato;
        private String quantita;
        private Time ora;

        public Ordine(){

        }

        public Ordine(int idUtente, int idMenu, String stato, String quantita,Time ora) {
            this.idUtente = idUtente;
            this.idMenu = idMenu;
            this.stato = stato;
            this.quantita = quantita;
            this.ora = ora;
        }


    public String getQuantita() {
        return quantita;
    }

    public void setQuantita(String quantita) {
        this.quantita = quantita;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }


    public Time getOra() {
        return ora;
    }

    public void setOra(Time ora) {
        this.ora = ora;
    }
}
