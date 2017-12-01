package com.example.utente.pooltrainingn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;

public class ListaSettimanaActivity extends AppCompatActivity  {

    private ListView list;
    private String []nome={"Lunedì", "Martedì", "Mercoledì", "Giovedì", "Venerdì", "Sabato","Domenica"};
    private String giorno;
    private String idNuotatore;
    private Toolbar logout;
    private Menu menu;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_settimana);
        mAuth = FirebaseAuth.getInstance();



        list = (ListView) findViewById(R.id.listaSettimana);
        idNuotatore = mAuth.getCurrentUser().getUid();
        logout = (Toolbar) findViewById(R.id.toolbarLogout);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nome);
        list.setAdapter(adapter);
        logout.setTitle("La mia settimana");
        logout.inflateMenu(R.menu.menu_logout);
        menu = logout.getMenu();


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


        logout.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.Logout:
                        mAuth.signOut();
                        finish();
                        Intent loginActivity = new Intent(ListaSettimanaActivity.this, MainActivity.class);
                        startActivity(loginActivity);

                        return true;
                }
                return false;
            }
        });
    }
}


