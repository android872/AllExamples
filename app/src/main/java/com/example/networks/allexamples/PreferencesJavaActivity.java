package com.example.networks.allexamples;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class PreferencesJavaActivity extends AppCompatActivity {

//    private  static  final String  USERNAME ="username";
private  static  final String  USERNAME ="pref_username";
    private  static  final String  VIEWIMAGES ="pref_viewimages";
    private SharedPreferences settings;
    private SharedPreferences.OnSharedPreferenceChangeListener listener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences_java);


//        settings = getPreferences(MODE_PRIVATE);
        settings = PreferenceManager.getDefaultSharedPreferences(this);

        listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

                PreferencesJavaActivity.this.btnShowPrefHandler(null);
                settings.registerOnSharedPreferenceChangeListener(listener);

            }
        };
    }

    public void btnShowPrefHandler(View view) {

        String preValue = settings.getString(USERNAME,"Not Found!");
        Toast.makeText(this,preValue,Toast.LENGTH_LONG).show();

    }

    public void btnSavePrefHandler(View view) {

    Intent intent = new Intent(this,SettingsActivity.class);
        startActivity(intent);


//        SharedPreferences.Editor editor= settings.edit();
//        TextView tv = (TextView) findViewById(R.id.tvUserNamePref);
//
//        editor.putString(USERNAME,  tv.getText().toString());
//        editor.commit();
//
//        Toast.makeText(this,"Pref Saved",Toast.LENGTH_LONG).show();
    }
}
