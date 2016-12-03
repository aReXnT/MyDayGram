package com.example.arexnt.mydaygram;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by arexnt on 2016/9/27.
 */

public class NoteDB extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "note";
    public static final String CONTENT = "content";
    public static final String ID = "_id";
    public static final String YEAR = "year";
    public static final String MONTH = "month";
    public static final String WEEK = "week";
    public static final String DAY = "day";



    public NoteDB(Context context){
        super(context,"note", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "
                + TABLE_NAME + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CONTENT + " TEXT, "
                + YEAR + " TEXT, "
                + MONTH + " TEXT, "
                + WEEK + " TEXT, "
                + DAY + " TEXT)"
                );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
