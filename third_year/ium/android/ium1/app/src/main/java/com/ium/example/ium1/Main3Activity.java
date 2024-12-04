package com.ium.example.ium1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Main3Activity extends AppCompatActivity {

    EditText t;
    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intento = getIntent();
        String param = intento.getStringExtra(MainActivity2.PARAMETRO);
        Log.d("Main3Activity.onCreate()","parametro="+param);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        t = findViewById(R.id.editTextTextPersonName);
        t.setText("da activity 2:"+param);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public boolean opOptionsItemSelected(MenuItem item){
        Log.d("onOptionsItemSelected", item.getTitle().toString());
        switch (item.getItemId()){
            case android.R.id.home:
                //bottone del toolbar, quello del telefono chiama onBackPressed()
                //se restituisco true disattivo il bottone
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void ritorna(View v){
        Intent result = new Intent();
        String s = t.getText().toString();
        result.putExtra(MainActivity2.RESULT_MESSAGE, s);
        setResult(Activity.RESULT_OK, result);
        //se non richiamo finish(), rimane attiva
        finish();
    }
}