package ru.kev.ny2016.ny2016_yek;

import androidx.appcompat.app.AppCompatActivity;
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

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
