package com.example.networks.allexamples;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Nasir on 6/29/2016.
 */
class CourseListAdapter extends ArrayAdapter<Course> {
    Context context;
    List<Course> objects;

    public CourseListAdapter(Context context, int resource, List<Course> objects) {
        super(context, resource, objects);

        this.context = context;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Course course = objects.get(position);
        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.course_item,null);
        TextView tv = (TextView) view.findViewById(R.id.courseText);
        tv.setText(course.getCourseTitle());

        ImageView iv = (ImageView) view.findViewById(R.id.imageCourse);
        int res = context.getResources().getIdentifier("image_"+ course.getCourseNumber(),
                "drawable",
                context.getPackageName()
        );


        return view;
    }
}