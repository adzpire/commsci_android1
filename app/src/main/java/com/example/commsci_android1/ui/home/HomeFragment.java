package com.example.commsci_android1.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.commsci_android1.DashboardModern;
import com.example.commsci_android1.DisplayMessageActivity2;
import com.example.commsci_android1.GallerylistActivity;
import com.example.commsci_android1.Intercom;
import com.example.commsci_android1.LoginActivity;
import com.example.commsci_android1.R;
import com.example.commsci_android1.ScannerActivity;
import com.example.commsci_android1.activity.GalleryActivity;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        Button b = (Button) root.findViewById(R.id.goact2);
        Button c = (Button) root.findViewById(R.id.gointercom);
        Button d = (Button) root.findViewById(R.id.gomoderndash);
        Button e = (Button) root.findViewById(R.id.gogalbtn);
        Button f = (Button) root.findViewById(R.id.button_1);
        Button g = (Button) root.findViewById(R.id.go2scanner);
        Button h = (Button) root.findViewById(R.id.go2login);
        b.setOnClickListener(this);
        c.setOnClickListener(this);
        d.setOnClickListener(this);
        e.setOnClickListener(this);
        f.setOnClickListener(this);
        g.setOnClickListener(this);
        h.setOnClickListener(this);

        return root;
    }
    public void onClick(View v) {
        //v.getId(R.id.goact2);
        int id = v.getId();
//        if (v.getId() == R.id.goact2) {
        if (id == R.id.goact2) {
            Intent intent = new Intent(getActivity(), DisplayMessageActivity2.class);

            startActivity(intent);
        }else if(id == R.id.gointercom){
            Intent intent = new Intent(getActivity(), Intercom.class);
            intent.putExtra("URL_DATA", "http://comm-sci.pn.psu.ac.th/office/inventory/default/indexjson");
            startActivity(intent);
        }else if(id == R.id.gomoderndash){
            Intent intent = new Intent(getActivity(), DashboardModern.class);

            startActivity(intent);
        }else if(id == R.id.gogalbtn){
            Intent intent = new Intent(getActivity(), GalleryActivity.class);

            startActivity(intent);
        }else if(id == R.id.button_1){
            Intent intent = new Intent(getActivity(), GallerylistActivity.class);

            startActivity(intent);
        }else if(id == R.id.go2scanner){
            Intent intent = new Intent(getActivity(), ScannerActivity.class);

            startActivity(intent);
        }else if(id == R.id.go2login){
            Intent intent = new Intent(getActivity(), LoginActivity.class);

            startActivity(intent);
        }
    }
}
