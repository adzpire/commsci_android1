package com.example.commsci_android1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListAdapter extends RecyclerView.Adapter {
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listview, parent, false);
        return  new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ListViewHolder) holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return Ourdata.title.length;
    }

    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView itemTxt;
        private ImageView itemImg;

        //View v = inflater.inflate(R.layout.fragment_complaints, container, false);

        private ListViewHolder(View itemView) {
            super(itemView);
            itemTxt = (TextView) itemView.findViewById(R.id.textView2);
            itemImg = (ImageView) itemView.findViewById(R.id.imageView2);
        }

        public void bindView(int position){
            itemTxt.setText(Ourdata.title[position]);
            itemImg.setImageResource(Ourdata.picturePath[position]);
        }
        @Override
        public void onClick(View v) {

        }
    }
}
