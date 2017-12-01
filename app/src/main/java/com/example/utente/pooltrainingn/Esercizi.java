package com.example.utente.pooltrainingn;

import java.io.Serializable;

/**
 * Created by Utente on 25/10/2017.
 */


public class Esercizi implements Serializable {

    private String nomeEsercizio;
    private int numeroVasche;
    private String idNuotatore;


    public Esercizi()
    {

    }
    public Esercizi(String nomeEsercizio, int numeroVascheEsercizio)
    {
        this.nomeEsercizio=nomeEsercizio;
        this.numeroVasche=numeroVascheEsercizio;

    }

    public Esercizi (String nomeEsercizio, int numeroVascheEsercizio, String idNuotatore){

        this.nomeEsercizio=nomeEsercizio;
        this.numeroVasche=numeroVascheEsercizio;
        this.idNuotatore=idNuotatore;

    }

    public String getNomeEsercizio() {

        return nomeEsercizio;
    }

    public void setNomeEsercizio(String nomeEsercizio) {

        this.nomeEsercizio = nomeEsercizio;
    }



    public int getNumeroVasche() {
        return numeroVasche;
    }

    public void setNumeroVasche(int numeroVascheEsercizio) {

        this.numeroVasche = numeroVascheEsercizio;
    }

    public String getIdNuotatore(){
        return idNuotatore;
    }
    public void setIdNuotatore(String idNuotatore){
        this.idNuotatore=idNuotatore;
    }



}
