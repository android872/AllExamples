package com.example.networks.allexamples;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAQ = "MainActivity";
    public static final String COURSE_TITLE = "courseTitle";
    public static final int COURSE_REQUEST_CODE = 1001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // adding views/controls to layout/activity runtime
        LinearLayout layout = (LinearLayout) findViewById(R.id.helloLayout);

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                android.app.ActionBar.LayoutParams.WRAP_CONTENT,
                android.app.ActionBar.LayoutParams.WRAP_CONTENT

        );

        for (int i = 0; i < 3; i++) {
            Button button = new Button(this);
            button.setText(R.string.hi);
            layout.addView(button);

        }



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //logging into logcat examples
        Log.d(LOG_TAQ, "Logging from OnCreate of MainActivity");


    }

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
                Toast.makeText(MainActivity.this,"Thanks",Toast.LENGTH_LONG).show();

                return false;
            }
        });


        //ADDING DYNAMIC REGISTER MENU OPTION AND NAVIGATING TO IT THROUGH INTENT AND CLICK LISTNER IMPLIMENTAION
        MenuItem registerActivityItem = menu.add(Menu.NONE,Menu.NONE,107,"Register");
        registerActivityItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        registerActivityItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener(){
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(MainActivity.this,RegistrationActivity.class);
                startActivity(intent);

                return false;
            }

        });


        //ADDING DYNAMIC REGISTER MENU OPTION AND NAVIGATING TO IT THROUGH INTENT AND CLICK LISTNER IMPLIMENTAION
        MenuItem courseCatelogActivity = menu.add(Menu.NONE,Menu.NONE,108,"Course Catelog");
        courseCatelogActivity.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        courseCatelogActivity.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener(){
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(MainActivity.this,CourseCatelogActivity.class);
                startActivity(intent);

                return false;
            }

        });


//PREF MENU ITEM
        MenuItem prefActivity = menu.add(Menu.NONE,Menu.NONE,109,"Preferences");
        prefActivity.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        prefActivity.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener(){
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(MainActivity.this,PreferencesJavaActivity.class);
                startActivity(intent);

                return false;
            }

        });
        Log.d(LOG_TAQ, "Logging from OnCreateOptionsMenu of MainActivity");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        Log.d(LOG_TAQ, "Logging from OnOptionsItemSelected of MainActivity");
        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAQ, "onStart");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAQ, "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAQ, "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(LOG_TAQ, "onRestart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAQ, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAQ, "onDestory");
    }

    //  APP FRAMEWORK EVENT OVERRIDING
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Log.d(LOG_TAQ, "Orientation is Portrait");
        } else {
            Log.d(LOG_TAQ, "Orientation is Landscape");
        }
    }


    public void btnGotoCourseHandler(View view){
        Intent courseIntent = new Intent(this,CourseActivity.class);
        CourseSingle course = new CourseSingle();
        //PASSING DATA TO ACT
        courseIntent.putExtra(COURSE_TITLE,course.getCourseTitle());

        //START ACTIVITY
        //startActivity(courseIntent);

        //START ACTIVITY AND EXPECT RESULT FROM THAT ACTIVITY
        startActivityForResult(courseIntent, COURSE_REQUEST_CODE);


    }

    //RECIEVE RESULT FROM CALLED ACTIVITIES
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       // super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == COURSE_REQUEST_CODE){//CODE TAGGED BY THIS ACITIVY TO CALLED ACTIVITY
            if (resultCode == RESULT_OK){//RESULT IS OK FROM CALLED ACTIVITY
                String recievedMsg = data.getStringExtra("resultMessage");
                Toast.makeText(this,recievedMsg,Toast.LENGTH_LONG).show();
            }
        }
    }

    public void actionGoToCourseHandler(MenuItem item){

        Intent courseIntent = new Intent(this,CourseActivity.class);
        startActivity(courseIntent);
        Log.d(LOG_TAQ,"going to course activity");
    }



    public void btnWebsiteHandler(View view) {
        Uri webPage = Uri.parse("http://www.pakdestiny.com");
        Intent webIntent = new Intent(Intent.ACTION_VIEW,webPage);
        startActivity(webIntent);
    }


// actionGoToCourseHandler
public void actionGoToABAHandler(MenuItem item){
    Intent intent = new Intent(this,ActionBarActivity.class);
    startActivity(intent);}
}
