package com.example.networks.allexamples;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by Networks on 6/21/2016.
 */
public class SettingsActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }
}
