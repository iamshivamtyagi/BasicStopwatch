package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView timer;
    Button startBtn;
    Button pauseBtn;
    Button resetBtn;
    long millisecondTime, startTime, bufTime;
    long updateTime = 0;
    Handler handler;
    int Seconds, Minutes, MilliSeconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer = findViewById(R.id.txtTimer);
        startBtn = findViewById(R.id.btnStart);
        pauseBtn = findViewById(R.id.btnPause);
        resetBtn = findViewById(R.id.btnReset);
        handler = new Handler();

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTime = SystemClock.uptimeMillis();
                handler.postDelayed(runnable, 0);
                resetBtn.setEnabled(false);
                startBtn.setEnabled(false);
            }
        });

        pauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bufTime += millisecondTime;
                handler.removeCallbacks(runnable);
                resetBtn.setEnabled(true);
                startBtn.setEnabled(true);
            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                millisecondTime = 0L ;
                startTime = 0L ;
                bufTime= 0L ;
                updateTime = 0L ;
                Seconds = 0 ;
                Minutes = 0 ;
                MilliSeconds = 0 ;
                timer.setText("00:00:00");
            }
        });
    }
    public Runnable runnable = new Runnable() {

        public void run() {

            millisecondTime = SystemClock.uptimeMillis() - startTime;
            updateTime = bufTime + millisecondTime;
            Seconds = (int) (updateTime / 1000);
            Minutes = Seconds / 60;
            Seconds = Seconds % 60;
            MilliSeconds = (int) (updateTime % 1000);
            timer.setText("" + Minutes + ":"
                    + String.format("%02d", Seconds) + ":"
                    + String.format("%03d", MilliSeconds));
            handler.postDelayed(this, 0);        }

    };

}
