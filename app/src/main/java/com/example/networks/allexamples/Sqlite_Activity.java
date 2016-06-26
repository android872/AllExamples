package com.example.networks.allexamples;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.networks.allexamples.db.ToursDataSource;
import com.example.networks.allexamples.db.TourseDbOpenHelper;
import com.example.networks.allexamples.models.Tour;

import java.util.List;

import javax.sql.DataSource;

public class Sqlite_Activity extends AppCompatActivity {

    ToursDataSource dataSource;
    public static final String LOGTAG = "SQLITE";
    private List<Tour> tours;


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
        tours = dataSource.findAll();
        if (tours.size() == 0) {
            createData();
            tours = dataSource.findAll();
        }

        refreshDisplay();

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
         tours = parser.parseXML(this);

        for (Tour tour : tours) {
            dataSource.create(tour);
        }

    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sqlite, menu);
        return true;
    }

    //FILTER AND SORT EXAMPLE
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_all:
                tours = dataSource.findAll();
                refreshDisplay();
                break;

            case R.id.menu_cheap:
                tours = dataSource.findFiltered("price <= 300", "price ASC");
                refreshDisplay();
                break;

            case R.id.menu_fancy:
                tours = dataSource.findFiltered("price >= 1000", "price DESC");
                refreshDisplay();
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void refreshDisplay() {
        ArrayAdapter<Tour> adapter = new ArrayAdapter<Tour>(this,
                android.R.layout.simple_list_item_1, tours);
       // setListAdapter(adapter);


//        ArrayAdapter<Course> courseArrayAdapter =
//                new CourseListAdapter(this,0,data);
        ListView listView = (ListView) findViewById(android.R.id.list);
        listView.setAdapter(adapter);

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Course course = data.get(position);
//                courseDetail(course);
//            }
//        });
    }



}
