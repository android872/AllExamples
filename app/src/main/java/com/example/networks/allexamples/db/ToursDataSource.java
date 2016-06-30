package com.example.networks.allexamples.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.networks.allexamples.models.Tour;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Networks on 6/24/2016.
 */
public class ToursDataSource {
    public static final String LOGTAG ="EXPLORECA";

    SQLiteOpenHelper dbOpenHelper;
    SQLiteDatabase db;
    private static final String[] allColumns = {
            TourseDbOpenHelper.COLUMN_ID,
            TourseDbOpenHelper.COLUMN_TITLE,
            TourseDbOpenHelper.COLUMN_DESC,
            TourseDbOpenHelper.COLUMN_PRICE,
            TourseDbOpenHelper.COLUMN_IMAGE};


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

    public Tour create(Tour tour) {
        ContentValues values = new ContentValues();
        values.put(TourseDbOpenHelper.COLUMN_TITLE, tour.getTitle());
        values.put(TourseDbOpenHelper.COLUMN_DESC, tour.getDescription());
        values.put(TourseDbOpenHelper.COLUMN_PRICE, tour.getPrice());
        values.put(TourseDbOpenHelper.COLUMN_IMAGE, tour.getImage());
        long insertid = db.insert(TourseDbOpenHelper.TABLE_TOURS, null, values);
        tour.setId(insertid);
        return tour;
    }


    public List<Tour> findAll() {
        Cursor cursor = db.query(TourseDbOpenHelper.TABLE_TOURS, allColumns,
                null, null, null, null, null);

        Log.i(LOGTAG, "Returned " + cursor.getCount() + " rows");
        List<Tour> tours = cursorToList(cursor);
        return tours;

    }

    public List<Tour> findFiltered(String selection, String orderBy) {

        Cursor cursor = db.query(TourseDbOpenHelper.TABLE_TOURS, allColumns,
                selection, null, null, null, orderBy);

        Log.i(LOGTAG, "Returned " + cursor.getCount() + " rows");
        List<Tour> tours = cursorToList(cursor);
        return tours;
    }

    private List<Tour> cursorToList(Cursor cursor) {
        List<Tour> tours = new ArrayList<Tour>();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Tour tour = new Tour();
                tour.setId(cursor.getLong(cursor.getColumnIndex(TourseDbOpenHelper.COLUMN_ID)));
                tour.setTitle(cursor.getString(cursor.getColumnIndex(TourseDbOpenHelper.COLUMN_TITLE)));
                tour.setDescription(cursor.getString(cursor.getColumnIndex(TourseDbOpenHelper.COLUMN_DESC)));
                tour.setImage(cursor.getString(cursor.getColumnIndex(TourseDbOpenHelper.COLUMN_IMAGE)));
                tour.setPrice(cursor.getDouble(cursor.getColumnIndex(TourseDbOpenHelper.COLUMN_PRICE)));
                tours.add(tour);
            }
        }
        return tours;
    }

    public boolean addToMyTours(Tour tour) {
        ContentValues values = new ContentValues();
        values.put(TourseDbOpenHelper.COLUMN_ID, tour.getId());
        long result = db.insert(TourseDbOpenHelper.TABLE_MYTOURS, null, values);
        return (result != -1);
    }
    public boolean removeFromMyTours(Tour tour) {
        String where = TourseDbOpenHelper.COLUMN_ID + "=" + tour.getId();
        int result = db.delete(TourseDbOpenHelper.TABLE_MYTOURS, where, null);
        return (result == 1);
    }


    public List<Tour> findMyTours() {

        String query = "SELECT tours.* FROM " +
                "tours JOIN mytours ON " +
                "tours.tourId = mytours.tourId";
        Cursor cursor = db.rawQuery(query, null);

        Log.i(LOGTAG, "Returned " + cursor.getCount() + " rows");

        List<Tour> tours = cursorToList(cursor);
        return tours;
    }





}
