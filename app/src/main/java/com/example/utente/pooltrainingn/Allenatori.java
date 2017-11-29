package com.example.utente.pooltrainingn;

/**
 * Created by Utente on 25/10/2017.
 */

public class Allenatori {
    private String nome;
    private String cognome;
    public Allenatori(){}

    public Allenatori(String nomeAllenatore,String cognomeAllenatore){
        this.nome=nomeAllenatore;
        this.cognome=cognomeAllenatore;
    }

    public String getNome(){
        return nome;
    }
    public String getCognome(){
        return cognome;
    }
    public void setNome(String nomeAllenatore) {
        this.nome = nomeAllenatore;
    }

    public void setCognome(String cognomeAllenatore) {
        this.cognome = cognomeAllenatore;
    }
}
