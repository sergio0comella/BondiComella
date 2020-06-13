package it.bondicomella.lido.utente.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Utente {

    private int id;
    private String email;
    private String nome;
    private String cognome;
    private String password;
    private String ruolo;

    /**
     * TODO
     * Map Role:
     * CCN - Gestore Cucina
     * BGT - Gestore Biglietteria
     * BGN - Bagnino
     * CLT - Clienti/Utenti
     */

    public Utente(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return this.password;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    public String getCognomeNome(){
        return this.cognome + ' ' + this.nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Utente)) return false;
        Utente utente = (Utente) o;
        return getEmail().equals(utente.getEmail()) &&
                getPassword().equals(utente.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail(), getPassword());
    }

}
