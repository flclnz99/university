package com.ium.example.cicloactivity;

import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "OnCreate..");
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(TAG, "OnStart..");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG, "OnResume..");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(TAG, "OnPause..");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d(TAG, "OnStop..");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d(TAG, "OnDestroy..");
    }
}