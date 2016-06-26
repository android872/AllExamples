package com.example.networks.allexamples;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.networks.allexamples.db.ToursDataSource;
import com.example.networks.allexamples.db.TourseDbOpenHelper;
import com.example.networks.allexamples.models.Tour;

import java.util.List;

import javax.sql.DataSource;

public class Sqlite_Activity extends AppCompatActivity {

    ToursDataSource dataSource;
    public static final String LOGTAG = "SQLITE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);



        //INSTATIATE DATA SOURCE TOURS
        dataSource = new ToursDataSource(this);

        //		ToursPullParser parser = new ToursPullParser();
//		List<Tour> tours = parser.parseXML(this);


        dataSource.open();

        //IF DB NOT CREATED THEN CREATE AND GET ALL INSTANCES
        List<Tour> tours = dataSource.findAll();
        if (tours.size() == 0) {
            createData();
            tours = dataSource.findAll();
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        dataSource.open();
    }

    @Override
    protected void onPause() {
        super.onPause();
        dataSource.close();
    }

    private void createData() {
//        Tour tour = new Tour();
//        tour.setTitle("Salton Sea");
//        tour.setDescription("A tour to Salton Sea");
//        tour.setPrice(600);
//        tour.setImage("salton_sea");
//        tour = dataSource.create(tour);
//        Log.i(LOGTAG, "Tour created with id " + tour.getId());
//
//        tour = new Tour();
//        tour.setTitle("Death Valley");
//        tour.setDescription("A tour to Death Valley");
//        tour.setPrice(900);
//        tour.setImage("death_valley");
//        tour = dataSource.create(tour);
//        Log.i(LOGTAG, "Tour created with id " + tour.getId());
//
//        tour = new Tour();
//        tour.setTitle("San Francisco");
//        tour.setDescription("A tour to San Francisco");
//        tour.setPrice(1200);
//        tour.setImage("san_francisco");
//        tour = dataSource.create(tour);
//        Log.i(LOGTAG, "Tour created with id " + tour.getId());

        ToursPullParser parser = new ToursPullParser();
        List<Tour> tours = parser.parseXML(this);

        for (Tour tour : tours) {
            dataSource.create(tour);
        }

    }

}
