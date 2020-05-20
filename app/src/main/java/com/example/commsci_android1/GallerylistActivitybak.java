package com.example.commsci_android1;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.commsci_android1.model.DataGalleryItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GallerylistActivitybak extends AppCompatActivity {

    private static final String URL_DATA ="https://api.commsci.psu.ac.th/rest/www/album?page=1";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<DataGalleryItem> listItems;
    TextView textView;
    String data = "bghgh";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallerylist);
        textView = findViewById(R.id.title_head);
        recyclerView = findViewById(R.id.galleryRCV);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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
                            JSONObject jsonObject = new JSONObject(response);
//                            JSONArray json2 = jsonObject.getJSONArray("items");
                            JSONArray array =jsonObject.getJSONArray("items");
//                            Log.println(jsonObject);
//                            JSONArray array = json2.getJSONArray("items");
                            for(int i = 0; i < array.length(); i++){
                                JSONObject jo = array.getJSONObject(i);
//                                data = data + jo.optString("title");
//                                JSONObject jo = (JSONObject) array.getJSONObject(i);
//                                Log.e(TAG, "JSON Object is = " + jsonObject);
                                //Toast.makeText(this, jo.getString("Name"), Toast.LENGTH_LONG).show();
                                DataGalleryItem item = new DataGalleryItem(
                                        jo.optString("title"),
                                        jo.optString("content"),
                                        jo.optString("image")
                                );
                                listItems.add(item);
                            }
                            //textView.setText(data);
//                            adapter = new AdapterGalleryList(listItems, getApplicationContext());
                            adapter = new AdapterGalleryList(listItems, getApplicationContext());
                            //adapter = new AdapterGalleryList(listItems, getApplicationContext());
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
