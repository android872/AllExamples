package com.example.networks.allexamples;

import java.text.NumberFormat;

import com.example.networks.allexamples.db.ToursDataSource;
import com.example.networks.allexamples.models.Tour;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class TourDetailActivity extends AppCompatActivity {

	Tour tour;
	ToursDataSource dataSource;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tour_detail);
		
//		tour = new Tour();
//		tour.setId(1);
//		tour.setTitle("Tour title");
//		tour.setDescription("Tour description");
//		tour.setPrice(999);
//		tour.setImage("map_winecountry");
		
		Bundle b = getIntent().getExtras();
		tour = b.getParcelable(".model.Tour");
		
		refreshDisplay();
		dataSource = new ToursDataSource(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.tour_detail, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.menu_add:
				if (dataSource.addToMyTours(tour)) {
					Log.i("TourDetail", "Tour added");
				} else {
					Log.i("TourDetail", "Tour not added");
				}
				break;
		}
		return super.onOptionsItemSelected(item);
	}


	private void refreshDisplay() {
		
		TextView tv = (TextView) findViewById(R.id.titleText);
		tv.setText(tour.getTitle());
		
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		tv = (TextView) findViewById(R.id.priceText);
		tv.setText(nf.format(tour.getPrice()));
		
		tv = (TextView) findViewById(R.id.descText);
		tv.setText(tour.getDescription());
		
		ImageView iv = (ImageView) findViewById(R.id.imageView1);
        int imageResource = getResources().getIdentifier(
        		tour.getImage(), "drawable", getPackageName());
        if (imageResource != 0) {
        	iv.setImageResource(imageResource);
        }
		
	}

}
