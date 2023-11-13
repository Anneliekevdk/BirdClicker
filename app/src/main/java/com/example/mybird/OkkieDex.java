package com.example.mybird;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class OkkieDex extends Activity {

    private Typeface ttf;
    private TextView start;
    private TextView mid;
    private TextView end;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.okkiedexwindow);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        getWindow().setLayout((int)(width*.95), (int)(height*.95));

        ImageView xrossImageView = findViewById(R.id.xross);
        xrossImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // Close the activity when the ImageView is clicked
            }
        });


        ttf = Typeface.createFromAsset(getAssets(), "JandaManateeSolid.ttf");

        start = findViewById(R.id.startPart);
        start.setTypeface(ttf);

        mid = findViewById(R.id.midPart);
        mid.setTypeface(ttf);

        end = findViewById(R.id.lastPart);
        end.setTypeface(ttf);
    }
}
