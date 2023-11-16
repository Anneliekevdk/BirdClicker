package com.example.mybird;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDatabaseHelper  extends SQLiteOpenHelper {

    private Context contect;
    private static final String DATABASE_NAME = "OkkieDex.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "birds";
    private static final String COLUM_ID = "_id";
    private static final String COLUM_TITLE = "bird_img_title";
    private static final String COLUM_IMAGE = "img";


    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.contect = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUM_ID + " INTERGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUM_TITLE + " TEXT, " +
                        COLUM_IMAGE + " BLOB);";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
