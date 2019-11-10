package ru.kev.ny2016.ny2016_yek;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class About extends AppCompatActivity {
    TextView tvVersion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        String versionName ="Версия: " +  BuildConfig.VERSION_NAME;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        tvVersion = (TextView) findViewById(R.id.idVersion);
        tvVersion.setText(versionName);

    }
}
