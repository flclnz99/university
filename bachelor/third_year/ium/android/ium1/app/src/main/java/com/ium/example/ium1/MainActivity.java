package com.ium.example.ium1;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    Button b3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("mainactivity.onclick()", "inizio");
        setContentView(R.layout.activity_main);
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

    public void clic1(View v){
        Intent intento = new Intent(this, MainActivity2.class);
        startActivity(intento);
    }

    public void clic1Orig(View v) {
        //v è la view su cui è avvenuto l'evento
        Button b = (Button) v;
        Log.d("mainactivity1.clic1()", b.getText().toString());
        b.setText("CLICCATO1");
        //setContentView(R.layout.layout2);
        Log.d("mainactivity.onclick()", "click");
        Context context = getApplicationContext();
        CharSequence text = "Hello Toast!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void clic2(View v) {
        if(b3 == null){
            b3 = findViewById(R.id.button3);
        }
        b3.setText("CLICCATO");
        setContentView(R.layout.activity_main);
        Log.d("mainactivity.onclick()", "click");
    }

    public void operazione(View v){
        EditText t1 = findViewById(R.id.op1);
        EditText t2 = findViewById(R.id.op2);
        TextView ris = findViewById(R.id.ris);
        String s1 = t1.getText().toString();
        String s2 = t2.getText().toString();
        int n1 = Integer.parseInt(s1);
        int n2 = Integer.parseInt(s2);
        int n3 = n1*n2;
        ris.setText(""+n3);
    }
}