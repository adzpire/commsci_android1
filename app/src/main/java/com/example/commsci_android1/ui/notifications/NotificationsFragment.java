package com.example.commsci_android1.ui.notifications;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.commsci_android1.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    private static String URL_DATA = "https://commsci.psu.ac.th/office/intercom/rest/index";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<DataIntercom> listItems;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        SearchView searchView = (SearchView) root.findViewById(R.id.Notisearch);

        recyclerView = (RecyclerView) root.findViewById(R.id.FrgnotificationRCV);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                URL_DATA ="https://commsci.psu.ac.th/office/intercom/rest/index?MainIntercomSearch[searchstring]="+query;
                listItems = new ArrayList<>();
                loadRCVData(URL_DATA);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                    if (newText.length() == 0) {
                        URL_DATA ="https://commsci.psu.ac.th/office/intercom/rest/index";
                        listItems = new ArrayList<>();
                        loadRCVData(URL_DATA);
                    }
//                URL_DATA ="https://commsci.psu.ac.th/office/intercom/rest/index?MainIntercomSearch[number]="+newText;
//                listItems = new ArrayList<>();
//                loadRCVData(URL_DATA);
//                Toast.makeText(getContext(), URL_DATA, Toast.LENGTH_SHORT).show();
                return false;
            }
        });


        listItems = new ArrayList<>();
        loadRCVData(URL_DATA);

        return root;
    }

    private void loadRCVData(String urlData) {
        final ProgressDialog progressDialog = new ProgressDialog(this.getContext());
        progressDialog.setMessage("Loading Data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                urlData,
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
                                    DataIntercom item = new DataIntercom(
                                            jo.optString("number"),
                                            jo.optString("occupy"),
                                            jo.optString("locth"),
                                            jo.optString("id")
                                    );
                                    listItems.add(item);
                            }

//                            adapter = new AdapterLocation(listItems, getContext());
                            adapter = new AdapterIntercomFragment(listItems, getContext());
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
                        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        );

        RequestQueue requestQueue = Volley.newRequestQueue(this.getContext());
        requestQueue.add(stringRequest);

    }
}
