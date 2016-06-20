package com.example.networks.allexamples;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class PreferencesJavaActivity extends AppCompatActivity {

    private  static  final String  USERNAME ="username";
    private SharedPreferences settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences_java);


        settings = getPreferences(MODE_PRIVATE);
    }

    public void btnShowPrefHandler(View view) {

        String preValue = settings.getString(USERNAME,"Not Found!");

        Toast.makeText(this,preValue,Toast.LENGTH_LONG).show();
    }

    public void btnSavePrefHandler(View view) {




        SharedPreferences.Editor editor= settings.edit();
        TextView tv = (TextView) findViewById(R.id.tvUserNamePref);
//String val = (String) tv.getText();
        editor.putString(USERNAME,  tv.getText().toString());
        editor.commit();

        Toast.makeText(this,"Pref Saved",Toast.LENGTH_LONG).show();
    }
}
