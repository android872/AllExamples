package com.example.networks.allexamples;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.networks.allexamples.db.ToursDataSource;

public class Sqlite_Activity extends AppCompatActivity {

    ToursDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);



        //INSTATIATE DATA SOURCE TOURS
        dataSource = new ToursDataSource(this);
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
}
