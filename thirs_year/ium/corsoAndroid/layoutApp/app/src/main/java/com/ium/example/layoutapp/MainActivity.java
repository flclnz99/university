package com.ium.example.layoutapp;

import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private ImageView immagine;
    private int counter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        immagine = findViewById(R.id.imageView);
        immagine.setImageResource(R.drawable.sardegna);
    }

    public void clickPulsante(View v){
        counter++;
        if(counter==1){
            immagine.setImageResource(R.drawable.sfondo2);
        } else if(counter==2){
            immagine.setImageResource(R.drawable.nono);
        } else {
            immagine.setImageResource(R.drawable.sardegna);
            counter=0;
        }
    }
}