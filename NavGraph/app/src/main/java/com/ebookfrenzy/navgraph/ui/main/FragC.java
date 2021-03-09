package com.ebookfrenzy.navgraph.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ebookfrenzy.navgraph.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragC#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragC extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragC() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragC.
     */
    // TODO: Rename and change types and number of parameters
    public static FragC newInstance(String param1, String param2) {
        FragC fragment = new FragC();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frag_c, container, false);
    }
    @Override public void onStart(){
        super.onStart();
        FragAArgs args= FragAArgs.fromBundle(getArguments());

        ImageView imageC = getView().findViewById(R.id.imageViewC);
        imageC.setImageResource(args.getImg());

//        TextView label = getView().findViewById(R.id.FragALabel);

//        String msg = args.getMessage();
//        label.setText(msg);
//        label.setText(args.getImg());



    }
}