package com.example.commsci_android1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.commsci_android1.model.DataGalleryItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterGalleryList extends RecyclerView.Adapter<AdapterGalleryList.ViewHolder> {

    private List<DataGalleryItem> dataGalleryItems;
    private Context context;

    public AdapterGalleryList(List<DataGalleryItem> dataGalleryItems, Context context) {
        this.dataGalleryItems = dataGalleryItems;
        this.context = context;
    }


    @NonNull
    @Override
    public AdapterGalleryList.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.li_gallery, parent,false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterGalleryList.ViewHolder holder, int position) {
        final DataGalleryItem dataItem = dataGalleryItems.get(position);
        holder.headlocaAdpt.setText(dataItem.getHead());
        holder.desclocaAdpt.setText(dataItem.getDesc());

        Picasso.get().load(dataItem.getImage()).into(holder.imageView);
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, dataItem.getHead(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataGalleryItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView headlocaAdpt;
        public TextView desclocaAdpt;
        public ImageView imageView;
        public ConstraintLayout constraintLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            headlocaAdpt = itemView.findViewById(R.id.galListHead);
            desclocaAdpt = itemView.findViewById(R.id.galListDesc);
            imageView = itemView.findViewById(R.id.galListIcon);
            constraintLayout = itemView.findViewById(R.id.constlayout_gal);
        }
    }
}
