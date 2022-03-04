package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
     TextView timerText;
    SeekBar timerSeekBar;
    Button Go;
    CountDownTimer countDownTimer;
    boolean counterIsActive = false;

    public void resetTimer() {
        timerText.setText("00:30");
        timerSeekBar.setProgress(30);
        timerSeekBar.setEnabled(true);
        countDownTimer.cancel();
        Go.setText("Go!");
        counterIsActive = false;
    }


     public void buttonClicked(View view) {
         if (counterIsActive) {
             resetTimer();
         


         } else {
             counterIsActive = true;
             timerSeekBar.setEnabled(false);
             Go.setText("Stop");
//
             countDownTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100, 1000) {
                 @Override
                 public void onTick(long l) {
                     updateTimer((int) l / 1000);
                 }

                 @Override
                 public void onFinish() {
                     MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.dog);
//                     timerText.setTextSize(100);

                     mediaPlayer.start();
                     timerText.setTextSize(35);

                     resetTimer();

                 }
             }.start();
         }
     }

    public void updateTimer(int secondsLeft){
        int min = secondsLeft / 60;
        int sec = secondsLeft - (min *60);
        String secondString = Integer.toString(sec);
        if(sec <= 9){
            secondString = "0" + secondString;
        }
        timerText.setText(Integer.toString(min) + ":" + secondString );
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         timerSeekBar = (SeekBar) findViewById(R.id.timerSeekBar);
         timerText = (TextView) findViewById(R.id.timerText);
        Go = (Button) findViewById(R.id.Go);
        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);
        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    updateTimer(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

}