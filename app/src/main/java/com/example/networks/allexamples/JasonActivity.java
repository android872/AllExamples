package com.example.networks.allexamples;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class JasonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jason);

        File f = getFilesDir();
        String path = f.getAbsolutePath();
        TextView tv = (TextView) findViewById(R.id.tvJsonText);
        tv.setText(path);

    }


    public void createJasonFileHandler (View view) throws JSONException {
        try {
            JSONArray data = new JSONArray();
            JSONObject tour;

            tour = new JSONObject();
            tour.put("tour", "Salton Sea");
            tour.put("price", 900);
            data.put(tour);

            tour = new JSONObject();
            tour.put("tour", "Death Valley");
            tour.put("price", 600);
            data.put(tour);

            tour = new JSONObject();
            tour.put("tour", "San Francisco");
            tour.put("price", 1_200);
            data.put(tour);

            String text = data.toString();

            FileOutputStream fos = openFileOutput("tours",MODE_PRIVATE);
            fos.write(text.getBytes());
            fos.close();

            TextView tv = (TextView) findViewById(R.id.tvFilePath);
            tv.setText("File written to disk:\n" + data.toString());
        } catch (IOException e) {
            TextView tv = (TextView) findViewById(R.id.tvJsonText);
            tv.setText("Unable to create file :"+e.getMessage());
        }


    }

    public void readJsonFileHandler(View view) throws JSONException {

        try {
            FileInputStream fis = openFileInput("tours");
            BufferedInputStream bis = new BufferedInputStream(fis);
            StringBuffer sb = new StringBuffer();
            while (bis.available() != 0){
                char c = (char) bis.read();
                sb.append(c);
            }
            TextView tv = (TextView) findViewById(R.id.tvJsonText);
            tv.setText(sb);
            bis.close();
            fis.close();


            JSONArray data = new JSONArray(sb.toString());
            StringBuffer toursBuffer = new StringBuffer();
            for (int i = 0; i < data.length(); i++) {
                String tour = data.getJSONObject(i).getString("tour");
                toursBuffer.append(tour + "\n");
            }


        } catch (IOException e) {
            TextView tv = (TextView) findViewById(R.id.tvFilePath);
            tv.setText("Unable to read file");
        }

    }
}
