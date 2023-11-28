package com.example.mybird;

import android.app.Activity;
import android.database.Cursor;
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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class OkkieDex extends AppCompatActivity {

    private Typeface ttf;
    private TextView okkiedexText;
    private TextView vooralleOkkies;

    RecyclerView recyclerView;

    MyDatabaseHelper myDB;
    ArrayList<String>  bird_id, bird_img_title, img;

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

        recyclerView = findViewById(R.id.okkiesRecyclerView);


        myDB = new MyDatabaseHelper(OkkieDex.this);
        bird_id = new ArrayList<>();
        bird_img_title = new ArrayList<>();
        img = new ArrayList<>();

        storeDataInArray();
    }

    void storeDataInArray(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                bird_id.add(cursor.getString(0));
                bird_img_title.add(cursor.getString(1));
                img.add(cursor.getString(2));
            }
        }
    }

}
