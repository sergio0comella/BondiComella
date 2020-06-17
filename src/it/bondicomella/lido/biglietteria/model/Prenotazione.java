package it.bondicomella.lido.biglietteria.model;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class Prenotazione {

    private int id;
    private int fkIdUtente;
    private int fkIdPostazione;
    private boolean pagata;
    private Date dataPrenotazione;
    private Time oraInizio;
    private Time oraFine;
    private boolean annullata;
    private String codicePrenotazione;

    /**
     * MAP annullata
     * 0 - tutto in regola
     * 1 - cancellata/terminata
     *
     */

    public Prenotazione(){}

    public Prenotazione(int fkIdUtente, int fkIdPostazione, boolean pagata, Date dataPrenotazione, Time oraInizio, Time oraFine, boolean annullata) {
        this.fkIdUtente = fkIdUtente;
        this.fkIdPostazione = fkIdPostazione;
        this.pagata = pagata;
        this.dataPrenotazione = dataPrenotazione;
        this.oraInizio = oraInizio;
        this.oraFine = oraFine;
        this.annullata = annullata;
    }

    public Prenotazione(int id, int fkIdUtente, int fkIdPostazione, boolean pagata, Date dataPrenotazione, Time oraInizio, Time oraFine, boolean annullata, String codicePrenotazione) {
        this.id = id;
        this.fkIdUtente = fkIdUtente;
        this.fkIdPostazione = fkIdPostazione;
        this.pagata = pagata;
        this.dataPrenotazione = dataPrenotazione;
        this.oraInizio = oraInizio;
        this.oraFine = oraFine;
        this.annullata = annullata;
        this.codicePrenotazione = codicePrenotazione;
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

    public Date getDataPrenotazione() {
        return dataPrenotazione;
    }

    public void setDataPrenotazione(Date dataPrenotazione) {
        this.dataPrenotazione = dataPrenotazione;
    }

    public Time getOraInizio() {
        return oraInizio;
    }

    public void setOraInizio(Time oraInizio) {
        this.oraInizio = oraInizio;
    }

    public Time getOraFine() {
        return oraFine;
    }

    public void setOraFine(Time oraFine) {
        this.oraFine = oraFine;
    }

    public boolean isAnnullata() {
        return annullata;
    }

    public void setAnnullata(boolean annullata) {
        this.annullata = annullata;
    }

    public String getCodicePrenotazione() {
        return codicePrenotazione;
    }

    public void setCodicePrenotazione(String codicePrenotazione) {
        this.codicePrenotazione = codicePrenotazione;
    }
}
