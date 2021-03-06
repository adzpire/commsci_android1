package com.example.commsci_android1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

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

public class GallerylistActivity extends AppCompatActivity {

   // private static final String URL_DATA ="https://api.commsci.psu.ac.th/rest/www/album?page=1";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<DataGalleryItem> listItems;
    private LinearLayoutManager linearLayoutManager;
    TextView textView;
    String data = "bghgh";
    public String url;

    public int page = 1;
    public int totalItemCount;
    public int firstVisibleItem;
    public int visibleItemCount;
    public int previoustotal;
    private boolean load = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallerylist);


        textView = findViewById(R.id.title_head);
        recyclerView = findViewById(R.id.galleryRCV);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        listItems = new ArrayList<>();

        adapter = new AdapterGalleryList(listItems, getApplicationContext());
        recyclerView.setAdapter(adapter);
        loadRCVData();

        pagination();
    }

    private void loadRCVData(){

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Data...");
        progressDialog.show();
        url = "https://api.commsci.psu.ac.th/rest/www/album?page="+page;
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url,
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
//                            adapter = new AdapterGalleryList(listItems, getApplicationContext());
                            adapter.notifyDataSetChanged();
                            //adapter = new AdapterGalleryList(listItems, getApplicationContext());
//                            recyclerView.setAdapter(adapter);
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

//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);

//        pagination();
    }

    private void pagination() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
//                Toast.makeText(getApplicationContext(), "bgbgb", Toast.LENGTH_LONG).show();
                if(dy > 0){

                    visibleItemCount = linearLayoutManager.getChildCount();
                    totalItemCount = linearLayoutManager.getItemCount();
                    firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();

                    //Toast.makeText(getApplicationContext(), "bgbgb", Toast.LENGTH_LONG).show();
                }

                if(load){
                    if (totalItemCount > previoustotal) {
                        previoustotal = totalItemCount;
                        page++;
                        load = false;
                    }
                }

                if(!load && (firstVisibleItem + visibleItemCount) >= totalItemCount){
                    loadRCVData();
                    load = true;
                }
            }
        });
    }
}
