package com.example.networks.allexamples;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Iterator;
import java.util.List;

public class CourseCatelogActivity extends AppCompatActivity {
   protected List<Course> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_catelog);


        data = DataProvider.getData();
        ArrayAdapter<Course> courseArrayAdapter = new ArrayAdapter<Course>(this,android.R.layout.simple_list_item_1,data);
        ListView listView = (ListView) findViewById(android.R.id.list);
        listView.setAdapter(courseArrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Course course = data.get(position);
                courseDetail(course);
            }
        });

//        Iterator<Course> iterator = data.iterator();
//        StringBuilder sb = new StringBuilder();
//        while(iterator.hasNext()){
//            Course course = iterator.next();
//            sb.append(course.getCourseTitle()).append("\n");
//
//        }
//        TextView tv = (TextView) findViewById(R.id.tvCatelog);
//        tv.setText(sb.toString());


        //ADDING UP ARROW TO THIS ACTIVITY
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }

    private void courseDetail(Course course) {

       // Log.d("CourseSelected","Course:" + course.getCourseTitle());
        Intent intent  = new Intent(this,CourseActivity.class);
        intent.putExtra("courseTitle",course.getCourseTitle());

        intent.putExtra("courseDesc",course.getDescription());
        startActivityForResult(intent,1001);
    }

    //GETT AND SET MENU OPTIONS
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    // actionGoToCourseHandler
    public void actionGoToABAHandler(MenuItem item){
        Intent intent = new Intent(this,ActionBarActivity.class);
        startActivity(intent);}

}
