package com.example.utente.pooltrainingn;

import android.util.Log;

import com.example.utente.pooltrainingn.Allenatori;
import com.example.utente.pooltrainingn.Esercizi;
import com.example.utente.pooltrainingn.Nuotatori;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.*;

import static android.content.ContentValues.TAG;

/**
 * Created by Sony on 20/10/2017.
 */

public class nuotoDatabase {

    private  final static String allenatori="Allenatori";
    private  final static String nuotatori="Nuotatori";
    private  final static String esercizi="Esercizi";
    private  final  static String vasche="vasche";

    private ArrayList<Nuotatori> listaNuotatori;
    private ArrayList<Esercizi> listaEsercizi;
    private ArrayList<Nuotatori> listaNuotatoriLiberi;
    ArrayList<String> idNuotatoriLiberi;
    ArrayList<String> idNuotatori;
    ArrayList<String> nomiNuotatoriLiberi;
    ArrayList<String> cognomiNuotatoriLiberi;
    ArrayList<String> idEsercizi;


    private ValueEventListener listenerNuotatori;
    private ValueEventListener listenerNuotatoriLiberi;
    private ValueEventListener listenerEsercizi;

    private FirebaseDatabase database=FirebaseDatabase.getInstance();
    private DatabaseReference mDatabase;

    private final static String KEY_COGNOME = "Cognome";
    private final static String KEY_NOME = "Nome";
    private final static String KEY_ID_ALLENATORE = "Id_Allenatore";
    private final String KEY_idNuotatore="idNuotatore";
    private final String Key_giornoSettimana="giornoSettimana";
    private String idNuotatore;
    private String weekday;


    public nuotoDatabase() {

        listaNuotatori = new ArrayList<>();
        listaEsercizi=new ArrayList<>();
        listaNuotatoriLiberi=new ArrayList<>();
        idNuotatoriLiberi=new ArrayList<>();
        idNuotatori=new ArrayList<>();
        idEsercizi=new ArrayList<>();
        nomiNuotatoriLiberi=new ArrayList<>();
        cognomiNuotatoriLiberi=new ArrayList<>();
    }

    public interface UpdateListenerN {
        void nuotatoriAggiornati();
    }
    public interface UpdateListenerE {
        void eserciziAggiornati();

    }
    public interface UpdateListenerNL {
        void nuotatoriLiberiAggiornati();
    }




    public  String addAllenatoreDB(Allenatori a) { //restituisce id allenatore

        String key = mDatabase.child(allenatori).push().getKey();
        mDatabase.child(allenatori).child(key).setValue(a);
        return key;
    }


    public String addNuotatoreDB(Nuotatori n) {

        String key = mDatabase.child(nuotatori).push().getKey();
        mDatabase.child(nuotatori).child(key).setValue(n);
        return key;
    }

    public  void addNuotatoreLista(Nuotatori n, String idAllenatore, String idNuotatore) {
//aggiungere direttamente l intero oggetto
        mDatabase = database.getReference(allenatori).child(idAllenatore).child(nuotatori);
        mDatabase.child(idNuotatore).child(KEY_NOME).setValue(n.getNome());
        mDatabase.child(idNuotatore).child(KEY_COGNOME).setValue(n.getCognome());
        mDatabase=database.getReference(nuotatori);
        mDatabase.child(idNuotatore).child(KEY_ID_ALLENATORE).setValue(idAllenatore);

    }

    public  void addEsercizio( String idNuotatore, String giorno, String nomeEsercizio, int numeroVasche) {

        mDatabase = database.getReference(nuotatori).child(idNuotatore).child(esercizi).child(giorno);
        String key = mDatabase.push().getKey();
        mDatabase.child(key).child("nomeEsercizio").setValue(nomeEsercizio);
        mDatabase.child(key).child("numeroVasche").setValue(numeroVasche);
    }
    public void rimuoviEsercizio (String idNuotatore, String giorno,String idEsercizio){

        mDatabase = database.getReference(nuotatori);
        mDatabase.child(idNuotatore).child(esercizi).child(giorno).child(idEsercizio).removeValue();
    }
    public void rimuoviNuotatore(String idNuotatore, String idAllenatore){

        mDatabase = database.getReference(allenatori);
        mDatabase.child(idAllenatore).child(nuotatori).child(idNuotatore).removeValue();
        mDatabase=database.getReference(nuotatori);
        mDatabase.child(idNuotatore).child(KEY_ID_ALLENATORE).setValue("null");



    }
    public void modificaEsercizio(String idNuotatore, String idEsercizio, String giorno, int nVasche){

        mDatabase=database.getReference(nuotatori);
        mDatabase.child(idNuotatore).child(esercizi).child(giorno).child(idEsercizio).child(vasche).setValue(nVasche);

    }


    public void leggiNuotatori(String idAllenatore, final UpdateListenerN notifica) {
        DatabaseReference  ref = database.getReference(allenatori).child(idAllenatore).child(nuotatori);

        listenerNuotatori = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listaNuotatori.clear();
                for (DataSnapshot elemento : dataSnapshot.getChildren()) {
                    Nuotatori nuotatore = new Nuotatori();

                    nuotatore.setCognome(elemento.child(KEY_COGNOME).getValue(String.class));
                    nuotatore.setNome(elemento.child(KEY_NOME).getValue(String.class));
                    listaNuotatori.add(nuotatore);
                    idNuotatori.add(elemento.getKey());
                }
                notifica.nuotatoriAggiornati();
            }


            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());

            }


        };
        ref.addValueEventListener(listenerNuotatori);


    }
    public void terminaOsservazioneNuotatori(String idAllenatore) {

        if (listenerNuotatori != null)
            database.getReference(allenatori).child(idAllenatore).child(nuotatori).removeEventListener(listenerNuotatori);
    }
    public List<Nuotatori> elencoNuotatori () {
        return listaNuotatori;
    }

    public void leggiEsercizi(String idNuotatore,  String weekday, final UpdateListenerE notifica) {

        DatabaseReference ref = database.getReference(nuotatori).child(idNuotatore).child(esercizi).child(weekday);
        listenerEsercizi=new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listaEsercizi.clear();

                for (DataSnapshot elemento : dataSnapshot.getChildren()) {

                    Esercizi e = new Esercizi();
                    e.setNomeEsercizio(elemento.child("nomeEsercizio").getValue(String.class));
                    e.setNumeroVascheEsercizio(elemento.child("numeroVasche").getValue(Integer.class));
                    idEsercizi.add(elemento.getKey());

                    listaEsercizi.add(e);


                }

                notifica.eserciziAggiornati();
            }


            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());

            }


        };
        ref.addValueEventListener(listenerEsercizi);

    }

    public void terminaOsservazioneEsercizi  (String idNuotatore) {
        if (listenerNuotatori != null)
            database.getReference(esercizi).removeEventListener(listenerEsercizi);
    }
    public List<Esercizi> elencoEsercizi () {
        return listaEsercizi;
    }






    public void leggiNuotatoriLiberi(final UpdateListenerNL notifica) {

        DatabaseReference ref = database.getReference(nuotatori);
        listenerNuotatoriLiberi= new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listaNuotatoriLiberi.clear();
                for (DataSnapshot elemento : dataSnapshot.getChildren()) {
                    if(elemento.child(KEY_ID_ALLENATORE).getValue(String.class).equals("null")) {

                        Nuotatori nuotatore = new Nuotatori();
                        nuotatore.setCognome(elemento.child(KEY_COGNOME).getValue(String.class));
                        nuotatore.setNome(elemento.child(KEY_NOME).getValue(String.class));
                        listaNuotatoriLiberi.add(nuotatore);
                        idNuotatoriLiberi.add(elemento.getKey());
                        nomiNuotatoriLiberi.add(elemento.child(KEY_NOME).getValue(String.class));
                        cognomiNuotatoriLiberi.add(elemento.child(KEY_COGNOME).getValue(String.class));

                    }
                }
                notifica.nuotatoriLiberiAggiornati();
            }


            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());

            }


        };
        ref.addValueEventListener(listenerNuotatoriLiberi);


    }
    public void terminaOsservazioneNuotatoriLiberi() {

        if (listenerNuotatoriLiberi != null)
            database.getReference(nuotatori).removeEventListener(listenerNuotatoriLiberi);
    }
    public List<Nuotatori> elencoNuotatoriLiberi () {
        return listaNuotatoriLiberi;
    }
}






