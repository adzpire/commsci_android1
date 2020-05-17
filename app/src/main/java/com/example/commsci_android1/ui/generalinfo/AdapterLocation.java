package com.example.commsci_android1.ui.generalinfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.commsci_android1.R;

import java.util.List;

public class AdapterLocation extends RecyclerView.Adapter<AdapterLocation.ViewHolder> {

    private List<DataLocation> dataLocations;
    private Context context;

    public AdapterLocation(List<DataLocation> dataLocations, Context context) {
        this.dataLocations = dataLocations;
        this.context = context;
    }


    @NonNull
    @Override
    public AdapterLocation.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_li_location, parent, false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterLocation.ViewHolder holder, int position) {
        final DataLocation dataLocation = dataLocations.get(position);
        holder.headlocaAdpt.setText(dataLocation.getHead());
        holder.desclocaAdpt.setText(dataLocation.getDesc());

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, dataLocation.getHead(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataLocations.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView headlocaAdpt;
        public TextView desclocaAdpt;
        public ConstraintLayout constraintLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            headlocaAdpt = (TextView) itemView.findViewById(R.id.locaListHead);
            desclocaAdpt = (TextView) itemView.findViewById(R.id.locaListDesc);
            constraintLayout = (ConstraintLayout) itemView.findViewById(R.id.constlayout);
        }
    }
}
