package com.example.commsci_android1.ui.generalinfo;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.commsci_android1.DataLocationItem;
import com.example.commsci_android1.R;
import com.example.commsci_android1.ui.dashboard.DashboardViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GeneralinfoFragment extends Fragment {

    private GeneralinfoViewModel generalinfoViewModel;
    private static final String URL_DATA ="http://comm-sci.pn.psu.ac.th/office/inventory/default/indexjson";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<DataLocation> listItems;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        generalinfoViewModel =
                ViewModelProviders.of(this).get(GeneralinfoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_generalinfo, container, false);
        final TextView textView = root.findViewById(R.id.text_frgGeneralinfo);
        generalinfoViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        recyclerView = (RecyclerView) root.findViewById(R.id.FrgGeneralinfoRCV);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        listItems = new ArrayList<>();
        loadRCVData();
        return root;
    }

    private void loadRCVData() {
        final ProgressDialog progressDialog = new ProgressDialog(this.getContext());
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
                                DataLocation item = new DataLocation(
                                        jo.optString("loc_name"),
                                        jo.optString("loc_name_eng"),
                                        jo.optString("floor")
                                );
                                listItems.add(item);
                            }

//                            adapter = new AdapterLocation(listItems, getContext());
                            adapter = new AdapterLocationFragment(listItems, getContext());
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
