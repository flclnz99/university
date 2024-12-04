package com.ium.example.intentapp;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = SecondActivity.class.getName();
    private static final String KEY_VALUE = TAG + ".extra.value";
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textView = findViewById(R.id.textView);

        String name = getIntent().getStringExtra(KEY_VALUE);
        textView.setText(name);
    }

    public static Intent getInstanceIntent(Context context, String value){
        Intent intent = new Intent(context, SecondActivity.class);
        intent.putExtra(KEY_VALUE, value);
        return intent;
    }
}