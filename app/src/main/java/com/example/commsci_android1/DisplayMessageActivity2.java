package com.example.commsci_android1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class DisplayMessageActivity2 extends AppCompatActivity {

    private static final String URL_DATA ="https://api.commsci.psu.ac.th/rest/www/album?page=1";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<DataLocationItem> listItems;

    public DisplayMessageActivity2() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message2);
//View v = null;
        recyclerView = (RecyclerView) findViewById(R.id.locaRCV);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        ListAdapter listAdapter = new ListAdapter();
//        recyclerView.setAdapter(listAdapter);

        listItems = new ArrayList<>();
        loadRCVData();
    }

    private void loadRCVData(){

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            //JSONObject jsonObject = new JSONObject(response);
                            JSONArray ja = new JSONArray(response);
                            for(int i = 0; i < ja.length(); i++){
                                JSONObject jo = (JSONObject) ja.get(i);
                                DataLocationItem item = new DataLocationItem(
                                        jo.optString("loc_name"),
                                        jo.optString("loc_name_eng"),
                                        jo.optString("floor")
                                );
                                listItems.add(item);
                            }

                            adapter = new AdapterLocation(listItems, getApplicationContext());
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        );

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
