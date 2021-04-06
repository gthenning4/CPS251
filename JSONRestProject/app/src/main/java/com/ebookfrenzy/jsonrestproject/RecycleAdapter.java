package com.ebookfrenzy.jsonrestproject;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecycleAdapter  extends RecyclerView.Adapter<RecycleAdapter.ViewHolder>{

    private ArrayList<Name> names;

    public RecycleAdapter(ArrayList<Name> nl){
            names = nl;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView age;
        public TextView gender;
        public TextView company;
        public TextView email;
        public TextView phone;
        public TextView address;
        public TextView index;
        public TextView eyeColor;
        public ImageView image;

        ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            age = view.findViewById(R.id.age);
//            gender = view.findViewById(R.id.gender);
            company = view.findViewById(R.id.company);
            email = view.findViewById(R.id.email);
            phone = view.findViewById(R.id.phone);
            address = view.findViewById(R.id.address);
            index = view.findViewById(R.id.index);
            eyeColor = view.findViewById(R.id.eyeColor);
            image=view.findViewById(R.id.imageView);
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
        Name name = names.get(i);
        viewHolder.name.setText(name.getName());
         viewHolder.address.setText(name.getAddress());
        viewHolder.age.setText(Integer.toString(name.getAge()));
        viewHolder.eyeColor.setText(name.getEyeColor());
        viewHolder.company.setText(name.getCompany());
        viewHolder.email.setText(name.getEmail());
        viewHolder.phone.setText(name.getPhone());
        viewHolder.index.setText(Integer.toString(name.getIndex()));

        String tempGender = name.getGender();
        Log.i("mine", tempGender)
        ;
        if(tempGender.matches("male")){
            viewHolder.image.setImageResource(R.drawable.male);
        }
        else{
            viewHolder.image.setImageResource(R.drawable.female);
        }

    }

    @Override
    public int getItemCount() {
        return names.size();
    }
}
