package com.example.utente.pooltrainingn;

import java.io.Serializable;

/**
 * Created by Utente on 25/10/2017.
 */

public class Nuotatori implements Serializable {
    private String nome;
    private String cognome;
    private String idAllenatore;

    public Nuotatori(){}

    public Nuotatori(String nome,String cognome, String idAllenatore){
        this.nome=nome;
        this.cognome=cognome;
        this.idAllenatore=idAllenatore;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome= nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognomeNuotatore) {
        this.cognome = cognomeNuotatore;
    }
    public String getIdAllenatore() {
        return idAllenatore;
    }
    public void setIdAllenatore(String idAllenatore) {
        this.idAllenatore = idAllenatore;
    }


}
