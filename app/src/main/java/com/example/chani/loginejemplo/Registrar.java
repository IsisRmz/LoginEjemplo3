package com.example.chani.loginejemplo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Registrar extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;

    EditText txtMail, txtPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        txtMail=findViewById(R.id.txtMail);
        txtPass=findViewById(R.id.txtPass);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.btnRegistrarse).setOnClickListener(this);
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

    }

    public void goToMainMenu(){
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);

    }

    public boolean validarDatos(String correo, String pass){
        if(correo.isEmpty()){
            txtMail.setError("Falta correo");
            return false;
        }
        if(pass.isEmpty()){
            txtPass.setError("Falta contraseña");
            return false;
        }
        return true;
    }

    public void crearCuenta(){
        String correo=txtMail.getText().toString();
        String pass=txtPass.getText().toString();
        if(validarDatos(correo, pass)){
            createUserWithEmailAndPassword(correo, pass);
        }
    }

    public void createUserWithEmailAndPassword(String correo, String password){
        mAuth.createUserWithEmailAndPassword(correo, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            goToMainMenu();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(), "Ha ocurrido un error, intente más tarde.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    public void onClick(View view){
        int r=view.getId();

        if (r==R.id.btnRegistrarse){
            crearCuenta();
        }
    }


}
