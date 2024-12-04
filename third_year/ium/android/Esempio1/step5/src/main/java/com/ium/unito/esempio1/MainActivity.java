package com.ium.unito.esempio1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void azione(View v){
        Intent inte = new Intent(this,Activity2.class);
        startActivity(inte);
    }

    @Override
    // se non facessi override  il bottone e' inattivo
    // sulla finestra principale

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
// bottone del toolbar, quello del telefono chiama onBackPressed()
              //  finish(); // di default non fa nulla
              //  super.onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
      //  super.onBackPressed(); // se non richiamo, disattivato!!!
        Log.d("   ", "Back pressed");
    }
}
