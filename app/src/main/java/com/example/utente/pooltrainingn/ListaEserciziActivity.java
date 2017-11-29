package com.example.utente.pooltrainingn;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListaEserciziActivity extends AppCompatActivity  {

    private nuotoDatabase archivio = new nuotoDatabase();
    private String idNuotatore;
    private String weekday;
    private ListView listaEsercizi;
    private EserciziAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_esercizi);

        idNuotatore = getIntent().getStringExtra("idNuotatore");
        weekday = getIntent().getStringExtra("giorno");

        listaEsercizi = (ListView) findViewById(R.id.listaEsercizi);



        adapter = new EserciziAdapter(this);

        archivio.leggiEsercizi(idNuotatore, weekday, new nuotoDatabase.UpdateListenerE() {
            @Override
            public void eserciziAggiornati() {
                adapter.update(archivio.elencoEsercizi());
            }
        });


        listaEsercizi.setAdapter(adapter);
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        archivio.terminaOsservazioneEsercizi(idNuotatore);
    }

}




