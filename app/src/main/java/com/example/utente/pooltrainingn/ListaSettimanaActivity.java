package com.example.utente.pooltrainingn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListaSettimanaActivity extends AppCompatActivity {

    private ListView list;
    private String []nome={"Lunedì", "Martedì", "Mercoledì", "Giovedì", "Venerdì", "Sabato","Domenica"};
private String giorno;
    private String idNuotatore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_settimana);

        list=(ListView)findViewById(R.id.listaSettimana);
        idNuotatore=getIntent().getStringExtra("idNuotatore");
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nome);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent esercizi=new Intent(getApplicationContext(), ListaEserciziActivity.class);
                giorno=list.getItemAtPosition(position).toString();
                esercizi.putExtra("giorno", giorno);
                esercizi.putExtra("idNuotatore", idNuotatore);
                startActivity(esercizi);

            }
        });

    }
    }

