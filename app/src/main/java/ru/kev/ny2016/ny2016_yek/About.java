package ru.kev.ny2016.ny2016_yek;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class About extends AppCompatActivity {
    private  TextView tvVersion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        String versionName = BuildConfig.VERSION_NAME;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }
}
