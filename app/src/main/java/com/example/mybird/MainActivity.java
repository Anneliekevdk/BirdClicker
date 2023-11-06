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
    private SensorManager sensorManager;
    private boolean canShake = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvPoints = findViewById(R.id.tvPoints);
        ttf = Typeface.createFromAsset(getAssets(), "JandaManateeSolid.ttf");
        tvPoints.setTypeface(ttf);

        trilling = findViewById(R.id.trilling);
        trilling.setTypeface(ttf);

        questionmark = findViewById(R.id.questionmark);
        questionmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Pop.class));
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
            window.setStatusBarColor(Color.parseColor("#FF9760")); // Orange color
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
        switch (points) {
            case 10:
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
                break;
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) { //wordt uitgevoed altijd als die wat detecteerd
        if (shakeing.isChecked()){
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];
                double acceleration = Math.sqrt(x * x + y * y + z * z);

                if (canShake && acceleration > 60) {
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
