package com.example.commsci_android1.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.commsci_android1.AdapterLocation;
import com.example.commsci_android1.DataLocationItem;
import com.example.commsci_android1.R;
import com.example.commsci_android1.adapter.GalleryAdapter;
import com.example.commsci_android1.app.AppController;
import com.example.commsci_android1.model.Image;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GalleryActivity extends AppCompatActivity {

    private String TAG = GalleryActivity.class.getSimpleName();
    private static final String endpoint = "http://192.168.0.110/test.php";
//    <?php
//
//    $myArr = [
//            [
//            "name" => "New York",
//            "url" => [
//            "small" => "https://www.simplifiedcoding.net/demos/marvel/captainamerica.jpg",
//            "medium" => "https://www.simplifiedcoding.net/demos/marvel/captainamerica.jpg",
//            "large" => "https://www.simplifiedcoding.net/demos/marvel/captainamerica.jpg",
//            ],
//            "timestamp" => "February 12, 2016"
//            ],
//            [
//            "name" => "Paris",
//            "url" => [
//            "small" => "https://www.simplifiedcoding.net/demos/marvel/ironman.jpg",
//            "medium" => "https://www.simplifiedcoding.net/demos/marvel/ironman.jpg",
//            "large" => "https://www.simplifiedcoding.net/demos/marvel/ironman.jpg",
//            ],
//            "timestamp" => "March 25, 2016"
//            ],
//            [
//            "name" => "Brussels",
//            "url" => [
//            "small" => "https://image.ibb.co/jv2dgS/App_07.jpg",
//            "medium" => "https://image.ibb.co/jv2dgS/App_07.jpg",
//            "large" => "https://image.ibb.co/jv2dgS/App_07.jpg",
//            ],
//            "timestamp" => "April 15, 2016"
//            ],
//            [
//            "name" => "New York",
//            "url" => [
//            "small" => "https://www.simplifiedcoding.net/demos/marvel/captainamerica.jpg",
//            "medium" => "https://www.simplifiedcoding.net/demos/marvel/captainamerica.jpg",
//            "large" => "https://www.simplifiedcoding.net/demos/marvel/captainamerica.jpg",
//            ],
//            "timestamp" => "February 12, 2016"
//            ],
//            [
//            "name" => "Paris",
//            "url" => [
//            "small" => "https://www.simplifiedcoding.net/demos/marvel/ironman.jpg",
//            "medium" => "https://www.simplifiedcoding.net/demos/marvel/ironman.jpg",
//            "large" => "https://www.simplifiedcoding.net/demos/marvel/ironman.jpg",
//            ],
//            "timestamp" => "March 25, 2016"
//            ],
//            [
//            "name" => "Brussels",
//            "url" => [
//            "small" => "https://image.ibb.co/jv2dgS/App_07.jpg",
//            "medium" => "https://image.ibb.co/jv2dgS/App_07.jpg",
//            "large" => "https://image.ibb.co/jv2dgS/App_07.jpg",
//            ],
//            "timestamp" => "April 15, 2016"
//            ],
//            [
//            "name" => "New York",
//            "url" => [
//            "small" => "https://www.simplifiedcoding.net/demos/marvel/captainamerica.jpg",
//            "medium" => "https://www.simplifiedcoding.net/demos/marvel/captainamerica.jpg",
//            "large" => "https://www.simplifiedcoding.net/demos/marvel/captainamerica.jpg",
//            ],
//            "timestamp" => "February 12, 2016"
//            ],
//            [
//            "name" => "Paris",
//            "url" => [
//            "small" => "https://www.simplifiedcoding.net/demos/marvel/ironman.jpg",
//            "medium" => "https://www.simplifiedcoding.net/demos/marvel/ironman.jpg",
//            "large" => "https://www.simplifiedcoding.net/demos/marvel/ironman.jpg",
//            ],
//            "timestamp" => "March 25, 2016"
//            ],
//            [
//            "name" => "Brussels",
//            "url" => [
//            "small" => "https://image.ibb.co/jv2dgS/App_07.jpg",
//            "medium" => "https://image.ibb.co/jv2dgS/App_07.jpg",
//            "large" => "https://image.ibb.co/jv2dgS/App_07.jpg",
//            ],
//            "timestamp" => "April 15, 2016"
//            ],
//            ];
//
//    $myJSON = json_encode($myArr);
//
//    echo $myJSON;
//?>

    private ArrayList<Image> images;
    private ProgressDialog pDialog;
    private GalleryAdapter mAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        pDialog = new ProgressDialog(this);
        images = new ArrayList<>();
        mAdapter = new GalleryAdapter(getApplicationContext(), images);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new GalleryAdapter.RecyclerTouchListener(getApplicationContext(), recyclerView, new GalleryAdapter.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("images", images);
                bundle.putInt("position", position);

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                SlideshowDialogFragment newFragment = SlideshowDialogFragment.newInstance();
                newFragment.setArguments(bundle);
                newFragment.show(ft, "slideshow");
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        fetchImages();
    }

    private void fetchImages() {

        pDialog.setMessage("Downloading json...");
        pDialog.show();

        JsonArrayRequest req = new JsonArrayRequest(
                endpoint,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        pDialog.hide();

                        images.clear();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject object = response.getJSONObject(i);
                                Image image = new Image();
                                image.setName(object.getString("name"));

                                JSONObject url = object.getJSONObject("url");
                                image.setSmall(url.getString("small"));
                                image.setMedium(url.getString("medium"));
                                image.setLarge(url.getString("large"));
                                image.setTimestamp(object.getString("timestamp"));

                                images.add(image);

                            } catch (JSONException e) {
                                Log.e(TAG, "Json parsing error: " + e.getMessage());
                            }
                        }

                        mAdapter.notifyDataSetChanged();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "Error: " + error.getMessage());
                        pDialog.hide();
//                        progressDialog.dismiss();
//                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req);
    }
}
