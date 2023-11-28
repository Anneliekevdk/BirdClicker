package com.example.mybird;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class OkkieDex extends Activity {

    private Typeface ttf;
    private TextView okkiedexText;
    private TextView vooralleOkkies;

    RecyclerView recyclerView;
    FloatingActionButton add_button;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.okkiedexwindow);

        ImageView xrossImageView = findViewById(R.id.okkieXross);
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

        okkiedexText = findViewById(R.id.okkiedexText);
        okkiedexText.setTypeface(ttf);

        vooralleOkkies = findViewById(R.id.vooralleOkkies);
        vooralleOkkies.setTypeface(ttf);


//        recyclerView = findViewById(R.id.okkiesRecyclerView);
//        add_button = findViewById(R.id.floatingActionButton);
//        add_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //update shit
//
//                Log.d("test", "test");
//                MyDatabaseHelper myDB = new MyDatabaseHelper(OkkieDex.this);
//                myDB.addBird()
//            }
//        });


    }
}
