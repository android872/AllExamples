package com.example.networks.allexamples;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExternalFileActivity extends AppCompatActivity {

    private  File file;
    private static final String FILENAME = "jsonfile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_file);


        File extDir = getExternalFilesDir(null);
        String path = extDir.getAbsolutePath();
        TextView tv = (TextView) findViewById(R.id.tvEFilePath);
        tv.setText(path);

        file = new File(extDir,FILENAME);
    }

    public void createExternalFileHandler(View view) throws JSONException {
        JSONArray data = getNewJSONData();
        if (!checkExternalStorage()){
            return;
        }

        try {


            String text = data.toString();

           // FileOutputStream fos = openFileOutput("tours",MODE_PRIVATE);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(text.getBytes());
            fos.close();

            TextView tv = (TextView) findViewById(R.id.tvEFilePath);
            tv.setText("File written to disk:\n" + data.toString());
        } catch (IOException e) {
            TextView tv = (TextView) findViewById(R.id.tvEFilePath);
            tv.setText("Unable to create file :"+e.getMessage());
        }
    }

    private JSONArray getNewJSONData() throws JSONException {
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
        tour.put("price", 1200);
        data.put(tour);
        return data;
    }



    public void readExternalFileHandler(View view) throws JSONException {

        try {
           // FileInputStream fis = openFileInput("tours");
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            StringBuffer sb = new StringBuffer();
            while (bis.available() != 0){
                char c = (char) bis.read();
                sb.append(c);
            }
            TextView tv = (TextView) findViewById(R.id.tvEFilePath);
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
            TextView tv = (TextView) findViewById(R.id.tvEFilePath);
            tv.setText("Unable to read file");
        }

    }

    public boolean checkExternalStorage() {
        String state = Environment.getExternalStorageState();

        if (state.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else if (state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
            //UIHelper.displayText(this, R.id.textView1, "External storage is read-only");
        } else {
            //UIHelper.displayText(this, R.id.textView1, "External storage is unavailable");
        }
        return false;
    }

}
