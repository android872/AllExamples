package com.example.networks.allexamples;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.networks.allexamples.models.Tour;

import java.util.List;

/**
 * Created by Nasir on 6/29/2016.
 */
class TourListAdapter extends ArrayAdapter<Tour> {
    Context context;
    List<Tour> objects;

    public TourListAdapter(Context context, int resource, List<Tour> objects) {
        super(context, resource, objects);

        this.context = context;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Tour course = objects.get(position);
        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.course_item,null);
        TextView tv = (TextView) view.findViewById(R.id.courseText);
        tv.setText(course.getTitle());

        ImageView iv = (ImageView) view.findViewById(R.id.imageCourse);
        int res = context.getResources().getIdentifier( course.getImage(),
                "drawable",
                context.getPackageName()
        );

        if(res!=0){
            iv.setImageResource(res);
        }


        return view;
    }
}