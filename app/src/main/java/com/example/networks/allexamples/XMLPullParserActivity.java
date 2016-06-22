package com.example.networks.allexamples;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.networks.allexamples.models.Tour;

import java.util.ArrayList;
import java.util.List;

public class XMLPullParserActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xmlpull_parser);

      //  ToursPullParser parser = new ToursPullParser();
        ToursJDOMParser parser = new ToursJDOMParser();
        List<Tour> tours = parser.parseXML(this);
        ArrayAdapter<Tour> adapter = new ArrayAdapter<Tour>(this, android.R.layout.simple_list_item_1, tours);
        setListAdapter(adapter);

    }
}
