package com.example.chani.loginejemplo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Multimedia extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multimedia);
        findViewById(R.id.btnFoto).setOnClickListener(this);
        findViewById(R.id.btnVideo).setOnClickListener(this);
        findViewById(R.id.btnAudio).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnFoto:
                tomarFoto();
                break;
                
            case R.id.btnVideo:
                    tomarVideo();
                break;
            case R.id.btnAudio:
                tomarAudio();
                break;
                
        }
        
    }

    private void tomarAudio() {
        Intent intent = new Intent(getApplicationContext(), Audio.class);
        startActivity(intent);
    }

    private void tomarVideo() {
        Intent intent = new Intent(getApplicationContext(), Video.class);
        startActivity(intent);
    }

    private void tomarFoto() {
        Intent intent = new Intent(getApplicationContext(), Camara.class);
        startActivity(intent);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
