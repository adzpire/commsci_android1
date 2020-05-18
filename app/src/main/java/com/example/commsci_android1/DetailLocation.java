package com.example.commsci_android1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DetailLocation extends AppCompatActivity {
    TextView passedurl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_location);
        String url_data = null;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            url_data = (String) extras.getString("URL_DATA");
        }
        passedurl = findViewById(R.id.urltext);

        passedurl.setText(url_data);
    }

    public void closepage(View view) {
        finish();
    }
}
