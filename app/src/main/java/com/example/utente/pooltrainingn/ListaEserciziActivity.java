package com.example.utente.pooltrainingn;



import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.view.Menu;


import android.view.MenuItem;
import android.widget.ListView;


import com.google.firebase.auth.FirebaseAuth;



public class ListaEserciziActivity extends AppCompatActivity {

    private nuotoDatabase archivio = new nuotoDatabase();
    private String idNuotatore;
    private String weekday;
    private ListView listaEsercizi;
    private EserciziAdapter adapter;
    private Toolbar logout;
    private Menu menu;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_esercizi);
        weekday=getIntent().getStringExtra("giorno");
        idNuotatore=getIntent().getStringExtra("idNuotatore");
        logout = (Toolbar) findViewById(R.id.toolbarLogout);

        listaEsercizi = (ListView) findViewById(R.id.listaEsercizi);

        adapter = new EserciziAdapter(this);

        archivio.leggiEsercizi(weekday, idNuotatore ,new nuotoDatabase.UpdateListenerE() {
            @Override
            public void eserciziAggiornati() {
                adapter.update(archivio.elencoEsercizi());
            }
        });


        listaEsercizi.setAdapter(adapter);
        logout.setTitle("I miei esercizi");
        logout.inflateMenu(R.menu.menu_logout);
        menu = logout.getMenu();







        logout.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.Logout:
                        mAuth.signOut();
                        finish();
                        Intent loginActivity = new Intent(ListaEserciziActivity.this, MainActivity.class);
                        startActivity(loginActivity);

                        return true;
                }
                return false;
            }
        });
    }
    public void onStart()
    {
        super.onStart();

    }

    public void onRestart()
    {
        super.onRestart();

    }

    public void onResume()

    {
        super.onResume();
        archivio.leggiEsercizi(weekday, idNuotatore ,new nuotoDatabase.UpdateListenerE() {
            @Override
            public void eserciziAggiornati() {
                adapter.update(archivio.elencoEsercizi());
            }
        });


    }

    public void onPause()
    {
        super.onPause();

    }

    public void onStop()
    {
        super.onStop();

    }

    public void onDestroy()
    {
        super.onDestroy();

    }
}



/*
    @Override
    protected void onDestroy() {
        super.onDestroy();
        archivio.terminaOsservazioneEsercizi();
    }*/










