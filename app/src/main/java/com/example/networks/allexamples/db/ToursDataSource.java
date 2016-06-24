package com.example.networks.allexamples.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Networks on 6/24/2016.
 */
public class ToursDataSource {
    public static final String LOGTAG ="EXPLORECA";

    SQLiteOpenHelper dbOpenHelper;
    SQLiteDatabase db;

    public  ToursDataSource(Context context){
        dbOpenHelper = new TourseDbOpenHelper(context);

    }

    public void open(){
        Log.i(LOGTAG,"DB Opened");
        db = dbOpenHelper.getWritableDatabase();
    }

    public void close(){
        Log.i(LOGTAG,"DB Closed");
        dbOpenHelper.close();
    }

}
