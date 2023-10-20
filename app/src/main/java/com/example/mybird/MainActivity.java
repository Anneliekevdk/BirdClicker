package com.example.mybird;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvPoints;
    private int points = 0;
    private int birdsPerSecond = 100;
    private BirdCounter birdCounter = new BirdCounter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvPoints = findViewById(R.id.tvPoints);
    }

    @Override 
    public void onClick(View v){
        if(v.getId() == R.id.imgBird){
            Animation a = AnimationUtils.loadAnimation(this, R.anim.bird_animation);
            a.setAnimationListener(new SimpleAnimationListerner(){
                @Override
                public void onAnimationEnd(Animation animation){
                    birdClick();
                }
            });
            v.startAnimation(a);
        }
    }

    private void birdClick() {
        points++;
        tvPoints.setText(Integer.toString(points));
        showToast(R.string.clicked);

    }

    private void showToast(int stringID) {
        Toast toast = new Toast(this);
        toast.setGravity(Gravity.CENTER, 0 , 0);
        toast.setDuration(Toast.LENGTH_SHORT);

        TextView textView = new TextView(this);
        textView.setText(stringID);

        textView.setTextSize(40f);
        textView.setTextColor(Color.BLACK);

        toast.setView(textView);
        toast.show();
    }

    private void update (){
        points += birdsPerSecond/100;
        tvPoints.setText(Integer.toString(points));
    }
    public class BirdCounter{
        private Timer timer;

        public BirdCounter(){
            timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            update();
                        }
                    });
                }
            }, 1000, 10);
        }


    }
}