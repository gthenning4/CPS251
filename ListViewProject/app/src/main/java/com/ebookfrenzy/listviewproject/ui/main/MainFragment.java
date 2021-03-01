package com.ebookfrenzy.listviewproject.ui.main;

import androidx.lifecycle.ViewModelProvider;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.ebookfrenzy.listviewproject.R;

public class MainFragment extends Fragment {
    private MainViewModel mViewModel;
    private Button btn;
    private EditText name;
    private TextView msg;
    private ArrayAdapter<String> adapter;
    private ListView myList;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.main_fragment, container, false);
            return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        btn = getView().findViewById(R.id.button);
        msg = getView().findViewById(R.id.msg);
        name = getView().findViewById(R.id.Name);
        myList = getView().findViewById(R.id.myList);


        msg.setText(mViewModel.getMyText());        //Set message with view model
        msg.setTextColor(Color.parseColor("#FF0000"));         //set message color to red

        //Make a new adapter to handle array data from view model
        adapter = new ArrayAdapter<String>(getView().getContext(), android.R.layout.simple_list_item_1,mViewModel.getNames());


        myList.setAdapter(adapter); //Set list view to use newly created adapter

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(!name.getText().toString().isEmpty()){ // If name is not empty
                    //update view model with new name
                    mViewModel.addName(name.getText());
                    adapter.notifyDataSetChanged();

                    name.setText(""); //clear name text view
                    mViewModel.setMyText(""); //clear message in view model and ui
                    msg.setText(mViewModel.getMyText());
                }
                else{
                    mViewModel.setMyText("You must enter a name.");
                    msg.setText(mViewModel.getMyText());
                }
            }
        });
    }
}