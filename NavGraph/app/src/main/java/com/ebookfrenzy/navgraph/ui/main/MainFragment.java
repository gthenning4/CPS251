package com.ebookfrenzy.navgraph.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.ebookfrenzy.navgraph.R;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;

    public int getDrawableId(ImageView iv){
        return (Integer) iv.getTag();
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        // TODO: Use the ViewModel

        Button btn = getView().findViewById(R.id.btnToFragA);
        ImageView imgA = getView().findViewById(R.id.imgA);
        imgA.setTag(R.drawable.um1);

        ImageView imgB = getView().findViewById(R.id.imgB);
        imgB.setTag(R.drawable.um2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainFragmentDirections.ActionMainFragmentToFragA action = MainFragmentDirections.actionMainFragmentToFragA();
                int imgId = getDrawableId(imgA);
                action.setImg(imgId);
                Navigation.findNavController(v).navigate(action);
            }
        });

        Button btnB = getView().findViewById(R.id.btnToFragB);
        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainFragmentDirections.ActionMainFragmentToFragB action = MainFragmentDirections.actionMainFragmentToFragB();
                int imgId = getDrawableId(imgB);
                action.setImg(imgId);
                action.setMessage("this too");
                Navigation.findNavController(v).navigate(action);
            }
        });

        ImageView imgC = getView().findViewById(R.id.imgC);
        imgC.setTag(R.drawable.psu);

        Button btnC = getView().findViewById(R.id.btnToFragC);
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainFragmentDirections.ActionMainFragmentToFragC action = MainFragmentDirections.actionMainFragmentToFragC();
                int imgId = getDrawableId(imgC);
                action.setImg(imgId);
                Navigation.findNavController(v).navigate(action);
            }
        });

    }

}