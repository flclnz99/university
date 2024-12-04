package com.ium.unito.esempio1app2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ium.unito.libsample.Serializzata;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    EditText testo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testo = findViewById(R.id.contenuto);
        checkWhoStarted();
    }

    private void checkWhoStarted() {
        // metodo che viene invocato per vedere se siamo stati invocati
        // da un'altra applicazione (nel nostro corso sara' l'esempio "Esempio1"
        Intent intent = getIntent();
        Log.d("onCreate", "intent action: " + intent.getAction());
        if (intent.getAction().equals("com.unito.ium.esempio1.INTENTO")){
            String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
            if (sharedText != null) {
                testo.setText(sharedText);
            }
        }
    }

    public void azione(View v){
        setResult(444); // se fosse un risultato semplice, un numero intero
        // se chiamassi finish(), l'App terminerebbe subito!!!
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent result = new Intent();
        // setta valore di ritorno
        result.putExtra(Intent.EXTRA_TEXT, testo.getText().toString());

        // per il passo 7, scommentare le prossime linee

//        Serializzata ser = new Serializzata();
//        ser.setId("ID dell'oggetto serializzato");
//        result.putExtra("com.example.RESULT_ACTION", ser);

        setResult(Activity.RESULT_OK, result);
        super.onBackPressed();
    }

}
