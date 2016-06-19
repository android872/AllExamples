package com.example.networks.allexamples;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;

public class CourseActivity extends AppCompatActivity {
 protected  String coursTitle ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        //scrollable text example
        StringBuffer sb = new StringBuffer();
        sb.append(getText(R.string.lorem_ipsumm));
        sb.append(getText(R.string.lorem_ipsumm));
        sb.append(getText(R.string.lorem_ipsumm));
        sb.append(getText(R.string.lorem_ipsumm));
        sb.append(getText(R.string.lorem_ipsumm));

        TextView tv = (TextView) findViewById(R.id.txtLong);
        tv.setText(sb.toString());

        //ADDING UP ARROW TO THIS ACTIVITY
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //GETTING DATA FROM INTENT
        coursTitle = getIntent().getStringExtra(MainActivity.COURSE_TITLE);
        String desc = getIntent().getStringExtra("courseDesc");

        TextView tvTitle = (TextView) findViewById(R.id.txtHead);
        tvTitle.setText(coursTitle);
        TextView tvDesc = (TextView) findViewById(R.id.txtLong);
        tvDesc.setText(desc);
    }


    //CHANGE IMAGE ON BUTTON CLICK
    public void btnChangeImageClickHandler(View view) {

        String imageName = "image_10102";
        int res = getResources().getIdentifier(imageName, "drawable", getPackageName());
        ImageView iv = (ImageView) findViewById(R.id.ivChange);
        iv.setImageResource(res);


    }

    public void btnChangeToAssetsClick(View view) {

        String imageName = "image_50101.png";
        ImageView iv = (ImageView) findViewById(R.id.ivChange);

        try {

            InputStream stream = getAssets().open(imageName);
            Drawable drawable = Drawable.createFromStream(stream,null);
            iv.setImageDrawable(drawable);

        } catch (Exception e) {
            //Log.e(LOG_TAQ, e.getMessage());

        }

    }

    //MENU ACTION HANDLER
    public void actionChangeImageAssetHandler(MenuItem item) {
        String imageName = "image_50101.png";
        ImageView iv = (ImageView) findViewById(R.id.ivChange);

        try {

            InputStream stream = getAssets().open(imageName);
            Drawable drawable = Drawable.createFromStream(stream,null);
            iv.setImageDrawable(drawable);

        } catch (Exception e) {
           // Log.e(LOG_TAQ, e.getMessage());

        }

    }







    //GETT AND SET MENU OPTIONS
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);


        //ADDINGG MENU ITEM DYANMICALLY
        MenuItem item = menu.add(Menu.NONE,Menu.NONE,103,"Dynamic Menu Item");
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);


        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(CourseActivity.this,"Thanks",Toast.LENGTH_LONG).show();

                return false;
            }
        });




       // Log.d(LOG_TAQ, "Logging from OnCreateOptionsMenu of MainActivity");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinsection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }else if(id == android.R.id.home){
            finish();
        }
        //Log.d(LOG_TAQ, "Logging from OnOptionsItemSelected of MainActivity");
        return super.onOptionsItemSelected(item);
    }


    public void courseRegisterHandler(View view) {
        getIntent().putExtra("resultMessage","Registered for "+coursTitle);
        setResult(RESULT_OK,getIntent());
        finish();
    }

    public void actionGoToABAHandler(MenuItem item){
        Intent intent = new Intent(this,ActionBarActivity.class);
        startActivity(intent);}
}
