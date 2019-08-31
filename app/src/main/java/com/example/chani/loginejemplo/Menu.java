package com.example.chani.loginejemplo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Menu extends AppCompatActivity implements View.OnClickListener {

    String usuarioRecuperado;
    String passRecuperada;
    TextView lblCorreo;
    Intent intentRecuperado;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        intentRecuperado = getIntent();
        usuarioRecuperado = intentRecuperado.getStringExtra("miUsuario");
        passRecuperada = intentRecuperado.getStringExtra("miPassword");

        Toast.makeText(getApplicationContext(), usuarioRecuperado, Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), passRecuperada, Toast.LENGTH_SHORT).show();
        String email = user.getEmail();
        mAuth = FirebaseAuth.getInstance();
        lblCorreo = findViewById(R.id.lblCorreo);
        lblCorreo.setText(email);
        findViewById(R.id.btnMultimedia).setOnClickListener(this);


    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

    }

    public void signOut(View view) {
        mAuth.signOut();
    Intent intent= new Intent(this, MainActivity.class);
    startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnMultimedia:
                goToMultimedia();
                break;
        }
    }

    private void goToMultimedia() {
        Intent intent = new Intent(this, Multimedia.class);
        startActivity(intent);
    }
}