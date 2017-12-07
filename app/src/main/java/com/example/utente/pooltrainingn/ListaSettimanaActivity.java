package com.example.utente.pooltrainingn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.google.firebase.auth.FirebaseAuth;

public class ListaSettimanaActivity extends AppCompatActivity {

    private ListView list;
    private String[] nome = {"Lunedì", "Martedì", "Mercoledì", "Giovedì", "Venerdì", "Sabato"};
    private String giorno;
    private String idNuotatore;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_settimana);

        mAuth = FirebaseAuth.getInstance();
        list = (ListView) findViewById(R.id.listaSettimana);
        idNuotatore = mAuth.getCurrentUser().getUid();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nome);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent esercizi = new Intent(getApplicationContext(), ListaEserciziActivity.class);
                giorno = list.getItemAtPosition(position).toString();
                esercizi.putExtra("giorno", giorno);
                esercizi.putExtra("idNuotatore", idNuotatore);
                startActivity(esercizi);

            }
        });


    }
}


