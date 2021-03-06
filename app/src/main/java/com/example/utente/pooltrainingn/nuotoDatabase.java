package com.example.utente.pooltrainingn;

import android.util.Log;
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


    private  final static String nuotatori="Nuotatori";
    private  final static String esercizi="Esercizi";
    private  final  static String vasche="numeroVasche";
    private  final  static String nomeEsercizio="nomeEsercizio";

    private ArrayList<Esercizi> listaEsercizi;
    private ValueEventListener listenerEsercizi;
    private FirebaseDatabase database=FirebaseDatabase.getInstance();

    public nuotoDatabase() {

        listaEsercizi=new ArrayList<>();
    }


    public interface UpdateListenerE {
        void eserciziAggiornati();
    }

    public void leggiEsercizi(  String weekday, String id ,final UpdateListenerE notifica) {

    DatabaseReference ref = database.getReference(nuotatori).child(id).child(esercizi).child(weekday);
    listenerEsercizi=new ValueEventListener() {

        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            listaEsercizi.clear();

            for (DataSnapshot elemento : dataSnapshot.getChildren()) {

                Esercizi e = new Esercizi();
                e.setNomeEsercizio(elemento.child(nomeEsercizio).getValue(String.class));
                e.setNumeroVasche(elemento.child(vasche).getValue(Integer.class));
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

    public void terminaOsservazioneEsercizi  () {
        if (listenerEsercizi != null)
            database.getReference(esercizi).removeEventListener(listenerEsercizi);
    }
    public List<Esercizi> elencoEsercizi () {
        return listaEsercizi;
    }
}






