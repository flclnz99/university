package com.ium.unito.esempio1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void azione(View v){
        Log.d("MainActivity", "sono in azione");
        TextView t = findViewById(R.id.text);
        Date d = new Date();
        t.setText(d.toString());
        Intent inte = new Intent(this,Activity2.class);
        startActivity(inte);
    }
}
