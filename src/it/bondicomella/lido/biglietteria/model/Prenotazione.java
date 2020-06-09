package it.bondicomella.lido.biglietteria.model;

import java.sql.Timestamp;

public class Prenotazione {

    private int id;
    private int fkIdUtente;
    private int fkIdPostazione;
    private boolean pagata;
    private Timestamp dataOraInizio;
    private Timestamp dataOraFine;
    private int stato;

    public Prenotazione(){}

    public Prenotazione(int fkIdUtente, int fkIdPostazione, boolean pagata, Timestamp dataOraInizio, Timestamp dataOraFine, int stato) {
        this.fkIdUtente = fkIdUtente;
        this.fkIdPostazione = fkIdPostazione;
        this.pagata = pagata;
        this.dataOraInizio = dataOraInizio;
        this.dataOraFine = dataOraFine;
        this.stato = stato;
    }

    public Prenotazione(int id, int fkIdUtente, int fkIdPostazione, boolean pagata, Timestamp dataOraInizio, Timestamp dataOraFine, int stato) {
        this.id = id;
        this.fkIdUtente = fkIdUtente;
        this.fkIdPostazione = fkIdPostazione;
        this.pagata = pagata;
        this.dataOraInizio = dataOraInizio;
        this.dataOraFine = dataOraFine;
        this.stato = stato;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFkIdUtente() {
        return fkIdUtente;
    }

    public void setFkIdUtente(int fkIdUtente) {
        this.fkIdUtente = fkIdUtente;
    }

    public int getFkIdPostazione() {
        return fkIdPostazione;
    }

    public void setFkIdPostazione(int fkIdPostazione) {
        this.fkIdPostazione = fkIdPostazione;
    }

    public boolean isPagata() {
        return pagata;
    }

    public void setPagata(boolean pagata) {
        this.pagata = pagata;
    }

    public Timestamp getDataOraInizio() {
        return dataOraInizio;
    }

    public void setDataOraInizio(Timestamp dataOraInizio) {
        this.dataOraInizio = dataOraInizio;
    }

    public Timestamp getDataOraFine() {
        return dataOraFine;
    }

    public void setDataOraFine(Timestamp dataOraFine) {
        this.dataOraFine = dataOraFine;
    }

    public int getStato() {
        return stato;
    }

    public void setStato(int stato) {
        this.stato = stato;
    }

}
