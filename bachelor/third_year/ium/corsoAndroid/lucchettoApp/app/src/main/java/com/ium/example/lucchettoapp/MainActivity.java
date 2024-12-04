package com.ium.example.lucchettoapp;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        password = findViewById(R.id.pin);

    }

    public void onClick(View v){
        Intent intent = new Intent();
        intent.setAction("com.ium.example.intentap.startsecondactivity");
        String input = password.getText().toString().trim();
        if(input.equals("1234") && !input.isEmpty() ){
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "password non corretta" ,Toast.LENGTH_LONG).show();
        }
    }
}