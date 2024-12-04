package com.ium.unito.esempio1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ium.unito.libsample.Serializzata;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    EditText testo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testo = findViewById(R.id.text);
    }

    public void azione(View v){
        Log.d("MainActivity", "sono in azione");
        Intent sendIntent = new Intent();
        sendIntent.setAction("com.unito.ium.esempio1.INTENTO");
        sendIntent.putExtra(Intent.EXTRA_TEXT, testo.getText().toString());
        sendIntent.setType("text/plain");
        startActivityForResult(sendIntent, 2345);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (resultCode != Activity.RESULT_OK) {
            String msg;
            if (resultCode == Activity.RESULT_CANCELED) {
                msg = "RESULT_CANCELED";
            } else {
                msg = "RESULT_CODE=" + resultCode;
            }
            testo.setText(msg);
            return;
        }
        switch (requestCode) {
            case 2345: {
                String ret = data.getStringExtra(Intent.EXTRA_TEXT);
                Serializzata s = (Serializzata) data.getSerializableExtra("com.example.RESULT_ACTION");
                String val  = s.getId();
                testo.setText("ritorno stringa:" + ret +" serializzato: " +val);
                break;
            }
            default:
                testo.setText("codice richiesta ignoto:" + requestCode);
        }
    }
}
