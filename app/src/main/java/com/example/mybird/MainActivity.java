package com.example.mybird;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
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


import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvPoints;
    private int points = 0;
    private Typeface ttf;
    private Random random;
    private Switch trilling;
    private Switch shakeing;
    private ImageView imgBird;// heb ik gedaan
    private ImageView questionmark;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvPoints = findViewById(R.id.tvPoints);
        tvPoints.setTypeface(ttf );
        ttf = Typeface.createFromAsset(getAssets(), "JandaManateeSolid.ttf");

        trilling = findViewById(R.id.trilling);
        trilling.setTypeface(ttf);

        questionmark = findViewById(R.id.questionmark);
        questionmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Pop.class));
            }
        });

        shakeing = findViewById(R.id.shakeSwitch);
        shakeing.setTypeface(ttf);

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
        addTrilling();
        tvPoints.setText(Integer.toString(points));
        showToast(R.string.clicked);
        imgBird = (ImageView) findViewById(R.id.imgBird);
        switch (points){ //als de punten ...
            case 10: //10 is switch naar die image
                imgBird.setImageResource(R.drawable.bird1);
                break;
            case 20:
                imgBird.setImageResource(R.drawable.bird2);
                break;
            case 30:
                imgBird.setImageResource(R.drawable.bird3);
                break;
            case 40:
                imgBird.setImageResource(R.drawable.bird4);
                break;
            case 50:
                imgBird.setImageResource(R.drawable.bird5);
                break;
            case 60:
                imgBird.setImageResource(R.drawable.bird6);
                break;
            case 70:
                imgBird.setImageResource(R.drawable.bird7);
                break;
            case 80:
                imgBird.setImageResource(R.drawable.bird8);
                break;
            case 90:
                imgBird.setImageResource(R.drawable.bird9);
                break;
            case 100:
                imgBird.setImageResource(R.drawable.bird10);
                break;
            default:
//                    imgBird.setImageResource(R.drawable.bird0); geen defalt anders reset die hem na 10 dus bij 10 goeie img dan bij 11 niet meer  (zelfde met andere getallen)
        }
    }

    private void addTrilling() {
        //if the trilling switch is on the phone needs to fibrate by every point ++
        if (trilling.isChecked()) { // Check if the trilling switch is on
            Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            if (vibrator != null) {
                // Vibrate for 100 milliseconds when points increase
                vibrator.vibrate(100);
            }
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