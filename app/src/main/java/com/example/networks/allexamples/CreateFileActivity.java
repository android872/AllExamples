package com.example.networks.allexamples;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CreateFileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_file);

        File f = getFilesDir();
        String path = f.getAbsolutePath();
        TextView tv = (TextView) findViewById(R.id.tvFilePath);
        tv.setText(path);
    }

    public void createFileHandler(View view) {
        try {
            EditText et = (EditText) findViewById(R.id.txtFileInput);
            String text = et.toString();

            FileOutputStream fos = openFileOutput("myFile.txt",MODE_PRIVATE);
            fos.write(text.getBytes());
            fos.close();

            TextView tv = (TextView) findViewById(R.id.tvFilePath);
            tv.setText("File created");
        } catch (IOException e) {
            TextView tv = (TextView) findViewById(R.id.tvFilePath);
            tv.setText("Unable to create file :"+e.getMessage());
        }


    }

    public void readFileHandler(View view) {

        try {
            FileInputStream fis = openFileInput("myFile.txt");
            BufferedInputStream bis = new BufferedInputStream(fis);
            StringBuffer sb = new StringBuffer();
            while (bis.available() != 0){
                char c = (char) bis.read();
                sb.append(c);
            }
            TextView tv = (TextView) findViewById(R.id.tvFilePath);
            tv.setText(sb);

            bis.close();
            fis.close();;
        } catch (IOException e) {
            TextView tv = (TextView) findViewById(R.id.tvFilePath);
            tv.setText("Unable to read file");
        }

    }

}
