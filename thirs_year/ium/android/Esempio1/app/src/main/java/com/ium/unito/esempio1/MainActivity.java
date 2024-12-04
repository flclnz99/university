package com.ium.unito.esempio1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // popola la UI col file di Layout
        setContentView(R.layout.lineare);
    //    setContentView(R.layout.lineare);
    }
}
