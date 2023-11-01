package com.example.mybird;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvPoints;
    private int points = 0;
    private int birdsPerSecond = 0; //dit is de seconde omhoog per ding zoals normalen cookie clicker
    private BirdCounter birdCounter = new BirdCounter();
    private Typeface ttf;
    private Random random;

    private ImageView imgBird;// heb ik gedaan
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvPoints = findViewById(R.id.tvPoints);
        tvPoints.setTypeface(ttf );
        ttf = Typeface.createFromAsset(getAssets(), "JandaManateeSolid.ttf");

        TextView tvPoints = findViewById(R.id.tvPoints);
        tvPoints.setTypeface(ttf);
        tvPoints.setTextColor(Color.BLACK);
        random = new Random();

        imgBird = findViewById(R.id.imgBird);///----------------------------

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
        //hier wil ik een if doen dat als de points 100 zijn dat die veranderd van image


            if (points > 10) {
                // Verander de afbeelding naar "bird0"
                imgBird.setImageResource(R.drawable.bird1);
            }else if (points > 20) {
                imgBird.setImageResource(R.drawable.bird2);
            }else if (points > 30){
                imgBird.setImageResource(R.drawable.bird3);
            }else if (points > 40) {
                imgBird.setImageResource(R.drawable.bird4);
            }else if (points > 50) {
                imgBird.setImageResource(R.drawable.bird5);
            }else if (points > 60) {
                imgBird.setImageResource(R.drawable.bird6);
            }else if (points > 70) {
                imgBird.setImageResource(R.drawable.bird7);
            }else if (points > 80) {
                imgBird.setImageResource(R.drawable.bird8);
            }else if (points > 90) {
                imgBird.setImageResource(R.drawable.bird9);
            }else if (points > 100) {
                imgBird.setImageResource(R.drawable.bird10);
            }

    }

    private void showToast(int stringID) {
        final Toast toast = new Toast(this);
        toast.setGravity(Gravity.CENTER|Gravity.LEFT, random.nextInt(600)+100, random.nextInt(600)-300);
        toast.setDuration(Toast.LENGTH_SHORT);

        TextView textView = new TextView(this);
        textView.setText(stringID);

        textView.setTextSize(40f);
        textView.setTextColor(Color.BLACK);
        textView.setTypeface(ttf);
        toast.setView(textView);
        CountDownTimer toastCoundtDown;
        toastCoundtDown = new CountDownTimer(500, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                toast.show();
            }

            @Override
            public void onFinish() {
                toast.cancel();
            }
        };
        toast.show();
        toastCoundtDown.start();
    }

    private void update (){
        points += birdsPerSecond/10;
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
            }, 100, 10);
        }
    }
}