package com.example.commsci_android1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterLocation extends RecyclerView.Adapter<AdapterLocation.ViewHolder> {

    private List<DataLocationItem> dataLocationItems;
    private Context context;

    public AdapterLocation(List<DataLocationItem> dataLocationItems, Context context) {
        this.dataLocationItems = dataLocationItems;
        this.context = context;
    }


    @NonNull
    @Override
    public AdapterLocation.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.li_location, parent, false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterLocation.ViewHolder holder, int position) {
        final DataLocationItem dataLocationItem = dataLocationItems.get(position);
        holder.headlocaAdpt.setText(dataLocationItem.getHead());
        holder.desclocaAdpt.setText(dataLocationItem.getDesc());

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, dataLocationItem.getHead(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataLocationItems.size();
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
