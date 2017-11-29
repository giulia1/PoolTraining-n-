package com.example.utente.pooltrainingn;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private Button registrazione;
    private EditText nome;
    private EditText cognome;
    private EditText mail;
    private EditText password;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();
        createAuthStateListener();

        registrazione = (Button) findViewById(R.id.buttonRegistrazione);
        cognome = (EditText) findViewById(R.id.editTextCognome);
        nome = (EditText) findViewById(R.id.editTextNome);
        mail = (EditText) findViewById(R.id.editTextMail);
        password = (EditText) findViewById(R.id.editTextPassword2);
        registrazione.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String password2 = password.getText().toString();
                String mail2 = mail.getText().toString();
                CreateAccount(password2, mail2);


                Log.v("Log", "onclick");

            }
        });
    }
    private void createAuthStateListener() {
        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
    }
    @Override
    public void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            firebaseAuth.removeAuthStateListener(mAuthListener);
        }
    }
    private void CreateAccount(String mail,String password)
    {
        firebaseAuth.createUserWithEmailAndPassword(mail,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if(task.isSuccessful()) {
                    //Nuotatori n = new Nuotatori(nome.getText().toString(), cognome.getText().toString(), "null");
                    //FirebaseDatabase database = FirebaseDatabase.getInstance();
                    //DatabaseReference myRef = database.getReference("Nuotatori");
                    //myRef.child(task.getResult().getUser().getUid()).setValue(n);
                    Log.d("log", "createUserWithEmail:success");
                    Toast.makeText(RegisterActivity.this, "Registrazione completata con successo", Toast.LENGTH_SHORT).show();
                    Intent login = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(login);

                }
                else {
                    Log.e("errore", task.getException().toString());
                    Toast.makeText(RegisterActivity.this, "Registrazione non è andata a buon fine!", Toast.LENGTH_SHORT).show();


                }
            }
        });
    }
}




