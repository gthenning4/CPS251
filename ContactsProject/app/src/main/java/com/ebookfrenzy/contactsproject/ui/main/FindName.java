package com.ebookfrenzy.contactsproject.ui.main;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ebookfrenzy.contactsproject.Contact;
import com.ebookfrenzy.contactsproject.R;

import java.util.List;
import java.util.Locale;


public class FindName extends Fragment{

    private MainViewModel mViewModel;
    private ContactListAdapter adapter;

    TextView text;
    private String nameStr;


    public static FindName newInstance(String name) {
        FindName f= new FindName();
        Bundle args = new Bundle();
        args.putString("name", name);
        f.setArguments(args);

        return f;
    }

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
        observerSetup();
        recyclerSetup();
        Bundle args =getArguments();
        nameStr = args.getString("name");
        Log.i("mine", "onActivityCreated: "+nameStr);

        mViewModel.searchContacts(nameStr);
//        mViewModel.getFindResults(nameStr);
    }
    private void observerSetup() {
//        mViewModel.getAllContacts().observe(getViewLifecycleOwner(), new Observer<List<Contact>>(){
//            @Override
//            public void onChanged(@Nullable final List<Contact> contacts) {
////                adapter.setContactList(contacts);
////                adapter.notifyDataSetChanged();
//            }
//        });
        mViewModel.getSearchResults().observe(getViewLifecycleOwner(), new Observer<List<Contact>>(){
            @Override
            public void onChanged(@Nullable final List<Contact> contacts){
                adapter.setContactList(contacts);
            }
        });

//        mViewModel.getSearchResults().observe(getViewLifecycleOwner(), new Observer<List<Contact>>() {
//            @Override
//            public void onChanged(@Nullable final List<Contact> contacts) {
//                EditText contactName = getView().findViewById(R.id.contactName);
//                EditText contactNumber = getView().findViewById(R.id.contactNumber);
//
//                if (contacts.size() > 0) {
////                    contactId.setText(String.format(Locale.US, "%d",
////                            contacts.get(0).getId()));
//                    contactName.setText(contacts.get(0).getName());
//                    contactNumber.setText(String.format(Locale.US, "%d", contacts.get(0).getNumber()));
//                } else {
////                    contactId.setText("No Match");
//                }
//            }
//        });
    }
    private void recyclerSetup() {
        RecyclerView recyclerView;
        adapter = new ContactListAdapter(R.layout.contact_card, new ContactListAdapter.AdapterClickListener() {
            @Override
            public void AdapterClickListener(View v, int cardId) {

            }
        });

        recyclerView = getView().findViewById(R.id.contact_recycler);


//        ImageView image  = getView().findViewById(R)


        adapter.setAdapterClickListener(new ContactListAdapter.AdapterClickListener() {
            @Override
            public void AdapterClickListener(View v, int cardId) {
                String idStr = Integer.toString(cardId);
                String msg = "AdapterClickListenerFired:   " +idStr;
                Log.i("mine", msg);

                mViewModel.deleteContact(cardId);
                FindName fn = new FindName();
                Bundle args = new Bundle();
                args.putString("name", nameStr);
                fn.setArguments(args);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, fn)
                        .commitNow();

            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

    }

}