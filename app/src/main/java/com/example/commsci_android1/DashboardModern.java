package com.example.commsci_android1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

//public class DashboardModern extends AppCompatActivity implements View.OnClickListener {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_dashboard_modern);
//
//        CardView staffcard = (CardView) findViewById(R.id.cardstafflist);
//
//        staffcard.setOnClickListener(this);
//    }
//
//    @Override
//    public void onClick(View v) {
//        if (v.getId() == R.id.cardstafflist) {
//            Intent intent = new Intent(this, DisplayMessageActivity2.class);
//
//            startActivity(intent);
////        }else if(v.getId() == R.id.gointercom){
////            Intent intent = new Intent(getActivity(), Intercom.class);
////
////            startActivity(intent);
////        }else if(v.getId() == R.id.gomoderndash){
////            Intent intent = new Intent(getActivity(), DashboardModern.class);
////
////            startActivity(intent);
//        }
//    }
//}
public class DashboardModern extends AppCompatActivity implements AdapterModerngrid.ItemClickListener {

    AdapterModerngrid adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_modern);

        // data to populate the RecyclerView with
        String[] data = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48"};

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rvNumbers);
        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        adapter = new AdapterModerngrid(this);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Log.i("TAG", "You clicked number " + adapter.getItem(position)[0] + ", which is at cell position " + adapter.getItem(position)[1]);
    }
}
