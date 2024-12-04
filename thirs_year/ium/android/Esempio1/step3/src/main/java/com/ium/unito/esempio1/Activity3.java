package com.ium.unito.esempio1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Activity3 extends AppCompatActivity {

    private EditText testo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        Intent intent = getIntent();
        // recuper del parametro
        String message = intent.getStringExtra(Activity2.EXTRA_MESSAGE);
        testo = findViewById(R.id.editText);
        testo.setText(message);
    }

    public void ritorna(View v){
        Intent result = new Intent();
        String s = testo.getText().toString();
        result.putExtra(Activity2.RESULT_MESSAGE, s);
        setResult(Activity.RESULT_OK, result);
        // se non richiamo finish() rimane Attiva
       // finish();
    }
}
