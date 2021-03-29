package com.ebookfrenzy.asyncproject;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder>{

    Data myData = new Data();

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemName;
        TextView itemMsg;
        ViewHolder(View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName);
            itemMsg = itemView.findViewById(R.id.ItemMsg);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.itemName.setText("The name is "+myData.getName(i)+".");
        viewHolder.itemMsg.setText("This thread slept for " +myData.getRandAt(i)+" seconds.");
    }
    @Override
    public int getItemCount() {
        return myData.getNamesLen();
    }
}
