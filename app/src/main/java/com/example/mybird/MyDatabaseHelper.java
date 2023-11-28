package com.example.mybird;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "OkkieDex.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "birds";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "bird_img_title";
    private static final String COLUMN_IMAGE = "img";


    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("MyDatabaseHelper DB", "After super!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("onCreate DB", "Before CREATE TABLE if not exsists!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        String query =
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_TITLE + " TEXT, " +
                        COLUMN_IMAGE + " BLOB);";

        db.execSQL(query);
        Log.d("onCreate DB", "CREATE TABLE if not exsists!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("onUpgrade DB", "DIT MOET NOOIT GEBEUREN!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addBird(String title, String image){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_IMAGE, image);

        long result = db.insert(TABLE_NAME, null, cv);

        if (result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Added Succes", Toast.LENGTH_SHORT).show();
        }
    }

    public List<String> getAllBirdTitles() {
        List<String> birdTitles = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT " + COLUMN_TITLE + " FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null && cursor.moveToFirst()) {
            int titleIndex = cursor.getColumnIndex(COLUMN_TITLE);

            do {
                if (titleIndex != -1) {
                    String title = cursor.getString(titleIndex);
                    birdTitles.add(title);
                } else {
                    throw new RuntimeException("Doet niet");
                }
            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }
        db.close();

        return birdTitles;
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

}
