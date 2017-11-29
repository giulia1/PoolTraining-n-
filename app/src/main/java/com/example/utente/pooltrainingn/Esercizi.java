package com.example.utente.pooltrainingn;

/**
 * Created by Utente on 25/10/2017.
 */

public class Esercizi {

    private String nomeEsercizio;
    private String giornoSettimana;
    private int numeroVasche;

    public Esercizi(){}

    public Esercizi(String nomeEsercizio, String giornoSettimanaEsercizio,int numeroVascheEsercizio){
        this.nomeEsercizio=nomeEsercizio;
        this.giornoSettimana=giornoSettimanaEsercizio;
        this.numeroVasche=numeroVascheEsercizio;
    }

    public String getNomeEsercizio() {
        return nomeEsercizio;
    }

    public void setNomeEsercizio(String nomeEsercizio) {
        this.nomeEsercizio = nomeEsercizio;
    }

    public String getGiornoSettimana() {
        return giornoSettimana;
    }

    public void setGiornoSettimana(String giornoSettimanaEsercizio) {
        this.giornoSettimana = giornoSettimanaEsercizio;
    }

    public int getNumeroVasche() {
        return numeroVasche;
    }

    public void setNumeroVascheEsercizio(int numeroVascheEsercizio) {
        this.numeroVasche = numeroVascheEsercizio;
    }



}
