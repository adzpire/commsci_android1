package com.example.commsci_android1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Intercom extends AppCompatActivity {

    Button click;
    public static TextView data;
    TextView passedurl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intercom);

        String url_data = getIntent().getStringExtra("URL_DATA");

        click = findViewById(R.id.fetchIntercombuttn);
        data = findViewById(R.id.textViewfetch);
        passedurl = findViewById(R.id.testgeturl);

        passedurl.setText(url_data);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchIntercom process = new fetchIntercom();
                process.execute();
            }
        });
    }
}
