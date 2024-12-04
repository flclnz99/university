package com.ium.example.convertitore;

import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner;
    private EditText valore;
    private TextView euro, dollari, sterline;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.spinner);
        valore = findViewById(R.id.valore);
        euro = findViewById(R.id.euro);
        dollari = findViewById(R.id.dollari);
        sterline = findViewById(R.id.sterline);
    }

    public void calcola(View v){
        String selezione = String.valueOf(spinner.getSelectedItem());
        String stringaValore = valore.getText().toString().trim();
        double intValore = Integer.parseInt(stringaValore);
        switch(selezione){
            case "GBP":
                euro.setText(String.valueOf(intValore*1.17752));
                dollari.setText(String.valueOf(intValore*1.36158));
                sterline.setText(String.valueOf(intValore));
                break;
            case "EUR":
                euro.setText(String.valueOf(intValore));
                dollari.setText(String.valueOf(intValore*0.85));
                sterline.setText(String.valueOf(intValore*1.16));
                break;
            case "USD":
                euro.setText(String.valueOf(intValore*0.86));
                dollari.setText(String.valueOf(intValore));
                sterline.setText(String.valueOf(intValore*0.73));
                 break;
        }
     }
}