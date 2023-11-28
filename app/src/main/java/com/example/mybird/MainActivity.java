package com.example.mybird;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.util.Log;
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
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SensorEventListener {

    private TextView tvPoints;
    private int points = 0;
    private Typeface ttf;
    private Random random;
    private Switch trilling;
    private Switch shakeing;
    private ImageView imgBird;
    private ImageView questionmark;
    private ImageView okkiedex;
    private SensorManager sensorManager;
    private boolean canShake = true;
    private MyDatabaseHelper myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvPoints = findViewById(R.id.tvPoints);
        ttf = Typeface.createFromAsset(getAssets(), "JandaManateeSolid.ttf");
        tvPoints.setTypeface(ttf);

        trilling = findViewById(R.id.trilling);
        trilling.setTypeface(ttf);

        okkiedex = findViewById(R.id.okkiedexquestionmark);
        questionmark = findViewById(R.id.questionmark);
        myDB = new MyDatabaseHelper(MainActivity.this);
        questionmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Pop.class));
            }
        });


        okkiedex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, OkkieDex.class));
            }
        });

        shakeing = findViewById(R.id.shakeSwitch);
        shakeing.setTypeface(ttf);
        tvPoints = findViewById(R.id.tvPoints);
        tvPoints.setTypeface(ttf);
        tvPoints.setTextColor(Color.BLACK);
        random = new Random();

        imgBird = findViewById(R.id.imgBird);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }

        // Set the color of the status bar to orange
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#FCB48D")); // Orange color
        }
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imgBird) {
            Animation a = AnimationUtils.loadAnimation(this, R.anim.bird_animation);
            a.setAnimationListener(new SimpleAnimationListener() {
                @Override
                public void onAnimationEnd(Animation animation) {
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
        imgBird = findViewById(R.id.imgBird);

        String imageOfTheOkkie;
        String titleOfTheOkkieImage;
        switch (points) {
            case 0:
                imgBird.setImageResource(R.drawable.bird0);
                titleOfTheOkkieImage = "" + R.drawable.bird0;
                imageOfTheOkkie = "bird0";
                addToMyDB(titleOfTheOkkieImage, imageOfTheOkkie);
                break;
            case 2:
                imgBird.setImageResource(R.drawable.bird1);
                titleOfTheOkkieImage = "" + R.drawable.bird1;
                imageOfTheOkkie = "bird1";
                addToMyDB(titleOfTheOkkieImage, imageOfTheOkkie);
                break;
            case 4:
                imgBird.setImageResource(R.drawable.bird3);
                titleOfTheOkkieImage = "" + R.drawable.bird3;
                imageOfTheOkkie = "bird3";
                addToMyDB(titleOfTheOkkieImage, imageOfTheOkkie);
                break;
            case 6:
                imgBird.setImageResource(R.drawable.bird4);
                titleOfTheOkkieImage = "" + R.drawable.bird4;
                imageOfTheOkkie = "bird4";
                addToMyDB(titleOfTheOkkieImage, imageOfTheOkkie);
                break;
            case 15:
                imgBird.setImageResource(R.drawable.bird5);
                titleOfTheOkkieImage = "" + R.drawable.bird5;
                imageOfTheOkkie = "bird5";
                addToMyDB(titleOfTheOkkieImage, imageOfTheOkkie);
                break;
            case 20:
                imgBird.setImageResource(R.drawable.bloemokkie);
                titleOfTheOkkieImage = "" + R.drawable.bloemokkie;
                imageOfTheOkkie = "bloemokkie";
                addToMyDB(titleOfTheOkkieImage, imageOfTheOkkie);
                break;
            case 600:
                imgBird.setImageResource(R.drawable.buisnessokkie);
                titleOfTheOkkieImage = "" + R.drawable.buisnessokkie;
                imageOfTheOkkie = "buisnessokkie";
                addToMyDB(titleOfTheOkkieImage, imageOfTheOkkie);
                break;
            case 700:
                imgBird.setImageResource(R.drawable.hippy);
                titleOfTheOkkieImage = "" + R.drawable.hippy;
                imageOfTheOkkie = "hippy";
                addToMyDB(titleOfTheOkkieImage, imageOfTheOkkie);
                break;
            case 800:
                imgBird.setImageResource(R.drawable.cowboyokkie);
                titleOfTheOkkieImage = "" + R.drawable.cowboyokkie;
                imageOfTheOkkie = "cowboyokkie";
                addToMyDB(titleOfTheOkkieImage, imageOfTheOkkie);
                break;
            case 900:
                imgBird.setImageResource(R.drawable.kerstokkie);
                titleOfTheOkkieImage = "" + R.drawable.kerstokkie;
                imageOfTheOkkie = "kerstokkie";
                addToMyDB(titleOfTheOkkieImage, imageOfTheOkkie);
                break;
            case 1000:
                imgBird.setImageResource(R.drawable.sinterklaasokkie);
                titleOfTheOkkieImage = "" + R.drawable.sinterklaasokkie;
                imageOfTheOkkie = "sinterklaasokkie";
                addToMyDB(titleOfTheOkkieImage, imageOfTheOkkie);
                break;
            case 1100:
                imgBird.setImageResource(R.drawable.okkiepiettwee);
                titleOfTheOkkieImage = "" + R.drawable.okkiepiettwee;
                imageOfTheOkkie = "okkiepiettwee";
                addToMyDB(titleOfTheOkkieImage, imageOfTheOkkie);
                break;
            case 1200:
                imgBird.setImageResource(R.drawable.summerokkie);
                titleOfTheOkkieImage = "" + R.drawable.summerokkie;
                imageOfTheOkkie = "summerokkie";
                addToMyDB(titleOfTheOkkieImage, imageOfTheOkkie);
                break;
            case 1300:
                imgBird.setImageResource(R.drawable.bird6);
                titleOfTheOkkieImage = "" + R.drawable.bird6;
                imageOfTheOkkie = "bird6";
                addToMyDB(titleOfTheOkkieImage, imageOfTheOkkie);
                break;
            case 1400:
                imgBird.setImageResource(R.drawable.bird7);
                titleOfTheOkkieImage = "" + R.drawable.bird7;
                imageOfTheOkkie = "bird7";
                addToMyDB(titleOfTheOkkieImage, imageOfTheOkkie);
                break;
            case 1500:
                imgBird.setImageResource(R.drawable.bird8);
                titleOfTheOkkieImage = "" + R.drawable.bird8;
                imageOfTheOkkie = "bird8";
                addToMyDB(titleOfTheOkkieImage, imageOfTheOkkie);
                break;
            case 1600:
                imgBird.setImageResource(R.drawable.bird9);
                titleOfTheOkkieImage = "" + R.drawable.bird9;
                imageOfTheOkkie = "bird9";
                addToMyDB(titleOfTheOkkieImage, imageOfTheOkkie);
                break;
            case 2000:
                imgBird.setImageResource(R.drawable.bird10);
                titleOfTheOkkieImage = "" + R.drawable.bird10;
                imageOfTheOkkie = "bird10";
                addToMyDB(titleOfTheOkkieImage, imageOfTheOkkie);
            default:
                break;
        }
    }

    public void addToMyDB(String okkieImageTitle, String okkieImage){
        Log.d("test", "test ADDTOMYDB!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        MyDatabaseHelper myDB = new MyDatabaseHelper(MainActivity.this);
        myDB.addBird(okkieImageTitle.trim(), okkieImage.trim());
        Log.d("tagg", myDB.getAllBirdTitles().toString());
    }
    @Override
    public void onSensorChanged(SensorEvent event) { //wordt uitgevoed altijd als die wat detecteerd
        if (shakeing.isChecked()){
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];
                double acceleration = Math.sqrt(x * x + y * y + z * z);

                if (canShake && acceleration > 50) {
//                    points++;
                    birdClick();
//                canShake = false; dan kan die maar 1 keer
                }
            }
        }
    }
    private void addTrilling() {
        if (trilling.isChecked()) {
            Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            if (vibrator != null) {
                vibrator.vibrate(100);
            }
        }
    }

    private void showToast(int stringID) {
        final Toast toast = new Toast(this);
        toast.setGravity(Gravity.CENTER | Gravity.LEFT, random.nextInt(600) + 100, random.nextInt(600) - 300);
        toast.setDuration(Toast.LENGTH_SHORT);
        TextView textView = new TextView(this);
        textView.setText(stringID);
        textView.setTextSize(40f);
        textView.setTextColor(Color.BLACK);
        textView.setTypeface(ttf);
        toast.setView(textView);
        CountDownTimer toastCountDown;
        toastCountDown = new CountDownTimer(500, 100) {
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
        toastCountDown.start();
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}
