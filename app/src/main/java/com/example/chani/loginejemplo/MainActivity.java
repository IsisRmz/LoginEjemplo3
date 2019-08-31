package com.example.chani.loginejemplo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;

    EditText txtEmail, txtContra;
    GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 9001;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnEntrar).setOnClickListener(this);
        findViewById(R.id.btnRegistrar).setOnClickListener(this);
        txtEmail=findViewById(R.id.txtEmail);
        txtContra=findViewById(R.id.txtContra);
        mAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
                .build();
        findViewById(R.id.btnGoogle).setOnClickListener(this);
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

}

    @Override
    public void onClick(View view) {
        int i=view.getId();

        switch (i){
            case R.id.btnEntrar:
                signIn();
                break;

            case R.id.btnRegistrar:
                goToRegister();
                break;

            case R.id.btnGoogle:
                signIn();
                break;

            default:
                break;
        }
    }

    public void goToRegister(){
        Intent intent= new Intent(getApplicationContext(), Registrar.class);
        startActivity(intent);
    }

    public void signIn(){
        String correo=txtEmail.getText().toString();
        String contra=txtContra.getText().toString();
        if(validarDatos(correo, contra)){
            signInWithEmailAndPassword(correo, contra);
        }
    }

    public void signInWithEmailAndPassword(String correo, String password){
        mAuth.signInWithEmailAndPassword(correo, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            goToMainMenu();
                        } else {
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    public void goToMainMenu(){
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);

    }
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();

    }

    public boolean validarDatos(String correo, String pass){
        if(correo.isEmpty()){
            txtEmail.setError("Falta correo");
            return false;
        }
        if(pass.isEmpty()){
            txtContra.setError("Falta contraseña");
            return false;
        }
        return true;
    }

    private void signInGoogle(){
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


}
