package com.example.commsci_android1.ui.notifications;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.commsci_android1.DetailLocation;
import com.example.commsci_android1.R;
import com.example.commsci_android1.ui.notifications.DataIntercom;

import java.util.ArrayList;
import java.util.List;

public class AdapterIntercomFragment extends RecyclerView.Adapter<AdapterIntercomFragment.ViewHolder> {

    private List<DataIntercom> dataIntercoms;
    private List<DataIntercom> dataIntercomsFull;
    private Context context;

    public AdapterIntercomFragment(List<DataIntercom> dataIntercoms, Context context) {
        this.dataIntercoms = dataIntercoms;
        this.dataIntercomsFull = new ArrayList<>(dataIntercoms);
        this.context = context;
    }


    @NonNull
    @Override
    public AdapterIntercomFragment.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_li_location, parent, false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterIntercomFragment.ViewHolder holder, int position) {
        final DataIntercom dataIntercom = dataIntercoms.get(position);
        holder.headlocaAdpt.setText(dataIntercom.getHead());
//        holder.desclocaAdpt.setText(dataIntercom.getDesc());
        holder.desclocaAdpt.setText(dataIntercom.getDesc());

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailLocation.class);
                intent.putExtra("URL_DATA", "http://comm-sci.pn.psu.ac.th/office/intercom/rest/view?id=" + dataIntercom.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataIntercoms.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView headlocaAdpt;
        public TextView desclocaAdpt;
        public ConstraintLayout constraintLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            headlocaAdpt = (TextView) itemView.findViewById(R.id.frg_locaListHead);
            desclocaAdpt = (TextView) itemView.findViewById(R.id.frg_locaListDesc);
            constraintLayout = (ConstraintLayout) itemView.findViewById(R.id.frg_constlayout_location);
        }
    }
}
