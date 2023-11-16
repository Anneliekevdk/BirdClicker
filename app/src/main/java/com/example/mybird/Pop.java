package com.example.mybird;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class Pop extends Activity {

    private Typeface ttf;
    private TextView start;
    private TextView mid;
    private TextView end;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popupwindow);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        getWindow().setLayout((int)(width*.95), (int)(height*1.0));

        ImageView xrossImageView = findViewById(R.id.xross);
        xrossImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // Close the activity when the ImageView is clicked
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#FCB48D")); // Orange color
        }

        ttf = Typeface.createFromAsset(getAssets(), "JandaManateeSolid.ttf");

        start = findViewById(R.id.startPart);
        start.setTypeface(ttf);

        mid = findViewById(R.id.midPart);
        mid.setTypeface(ttf);

        end = findViewById(R.id.lastPart);
        end.setTypeface(ttf);
    }
}
