package com.ium.example.timerapp;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.QuickContactBadge;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private SeekBar seekBar;
    private Button startButton;
    private TextView timerText;
    private CountDownTimer countDownTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        seekBar = findViewById(R.id.seekBar);
        startButton = findViewById(R.id.startButton);
        timerText = findViewById(R.id.timerText);

        seekBar.setMax(600);
        seekBar.setProgress(30);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateTimerText(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seekBar.setEnabled(false);
                countDownTimer = new CountDownTimer(seekBar.getProgress()*1000+100, 1000) {
                    @Override
                    public void onTick(long l) {
                        updateTimerText((int)l/1000);
                    }

                    @Override
                    public void onFinish() {
                        resetTimer();
                    }
                };
                countDownTimer.start();
            }
        });
    }

    private void resetTimer() {
        timerText.setText("0.30");
        seekBar.setProgress(30);
        countDownTimer.cancel();
        seekBar.setEnabled(true);
    }

    private void updateTimerText(int secondLeft) {
        int minute = secondLeft/60;
        int seconds = secondLeft-minute*60;

        String secondsString = String.valueOf(seconds);

        if(seconds<=9){
            secondsString = "0"+secondsString;
        }

        timerText.setText(String.valueOf(minute) + ":" + secondsString);
    }
}