package com.ium.example.intentapp;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private Button sendToSecondActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendToSecondActivity = findViewById(R.id.sendToSecondActivity);

        sendToSecondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startIntent();
            }
        });
    }

    private void startIntent(){
        Intent intent = new Intent();
        intent.setAction("com.ium.example.intentap.startsecondactivity");
        startActivity(intent);
    }
}