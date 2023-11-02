package com.example.mybird;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvPoints;
    private int points = 0;
    private Typeface ttf;
    private Random random;
    private Switch shake;

    private ImageView imgBird;// heb ik gedaan
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvPoints = findViewById(R.id.tvPoints);
        tvPoints.setTypeface(ttf );
        ttf = Typeface.createFromAsset(getAssets(), "JandaManateeSolid.ttf");

        shake = findViewById(R.id.shake);
        shake.setTypeface(ttf);

        TextView tvPoints = findViewById(R.id.tvPoints);
        tvPoints.setTypeface(ttf);
        tvPoints.setTextColor(Color.BLACK);
        random = new Random();

        imgBird = findViewById(R.id.imgBird);

        // Stel de kleur van de statusbalk in op oranje
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#FF9760")); // Oranje kleur
        }

    }



    @Override
    public void onClick(View v){
        if(v.getId() == R.id.imgBird){
            Animation a = AnimationUtils.loadAnimation(this, R.anim.bird_animation);
            a.setAnimationListener(new SimpleAnimationListener(){
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
        imgBird = (ImageView) findViewById(R.id.imgBird);

        switch (points){ //als de punten ...
            case 100: //10 is switch naar die image
                imgBird.setImageResource(R.drawable.bird1);
                break;
            case 200:
                imgBird.setImageResource(R.drawable.bird2);
                break;
            case 300:
                imgBird.setImageResource(R.drawable.bird3);
                break;
            case 400:
                imgBird.setImageResource(R.drawable.bird4);
                break;
            case 500:
                imgBird.setImageResource(R.drawable.bird5);
                break;
            case 600:
                imgBird.setImageResource(R.drawable.bird6);
                break;
            case 700:
                imgBird.setImageResource(R.drawable.bird7);
                break;
            case 800:
                imgBird.setImageResource(R.drawable.bird8);
                break;
            case 900:
                imgBird.setImageResource(R.drawable.bird9);
                break;
            case 1000:
                imgBird.setImageResource(R.drawable.bird10);
                break;
            default:
//                    imgBird.setImageResource(R.drawable.bird0); geen defalt anders reset die hem na 10 dus bij 10 goeie img dan bij 11 niet meer  (zelfde met andere getallen)
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
}