package com.example.commsci_android1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DashboardModern extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_modern);

        CardView staffcard = (CardView) findViewById(R.id.cardstafflist);

        staffcard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.cardstafflist) {
            Intent intent = new Intent(this, DisplayMessageActivity2.class);

            startActivity(intent);
//        }else if(v.getId() == R.id.gointercom){
//            Intent intent = new Intent(getActivity(), Intercom.class);
//
//            startActivity(intent);
//        }else if(v.getId() == R.id.gomoderndash){
//            Intent intent = new Intent(getActivity(), DashboardModern.class);
//
//            startActivity(intent);
        }
    }
}
