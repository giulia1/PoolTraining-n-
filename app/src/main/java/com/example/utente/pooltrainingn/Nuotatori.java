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

    public Nuotatori(String nomeNuotatore,String cognomeNuotatore, String idAllenatore){
        this.nome=nomeNuotatore;
        this.cognome=cognomeNuotatore;
        this.idAllenatore=idAllenatore;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nomeNuotatore) {
        this.nome= nomeNuotatore;
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
