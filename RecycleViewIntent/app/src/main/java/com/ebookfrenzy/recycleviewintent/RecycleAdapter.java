package com.ebookfrenzy.recycleviewintent;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder>{

    Data myData = new Data();

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView itemTitle;
        TextView itemDetail;
        TextView itemImgIndex;

        ViewHolder(View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.item_image);
            itemTitle = itemView.findViewById(R.id.item_title);
            itemDetail = itemView.findViewById(R.id.item_detail);
            itemImgIndex = itemView.findViewById(R.id.item_imgIndex);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myI=new Intent(v.getContext(), ActivityB.class);
                myI.putExtra("title", viewHolder.itemTitle.getText());
                myI.putExtra("detail",viewHolder.itemDetail.getText());
                myI.putExtra("index",viewHolder.itemImgIndex.getText());

                v.getContext().startActivity(myI);
            }
        });

        return viewHolder;
    }
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.itemTitle.setText(myData.getRandomTitle());
        viewHolder.itemDetail.setText(myData.getRandomDetails());
        viewHolder.itemImage.setImageResource(myData.getRandomImage());
        viewHolder.itemImgIndex.setText(myData.getImgIndex());
    }
    @Override
    public int getItemCount() {
        return myData.getTitleCt();
    }

}
