package com.ebookfrenzy.contactsproject.ui.main;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import com.ebookfrenzy.contactsproject.Contact;
import com.ebookfrenzy.contactsproject.R;

import java.util.List;

public class AddName extends Fragment {

    private MainViewModel mViewModel;
    private ContactListAdapter adapter;

    private TextView contactId;
    private EditText contactName;
    private EditText contactNumber;

    public static AddName newInstance(String name, String number) {
        AddName s = new AddName();
        Bundle args = new Bundle();
        args.putString("name", name);
        args.putString("number", number);
        s.setArguments(args);

        return s;
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
//        contactId = getView().findViewById(R.id.contactId);
        contactName = getActivity().findViewById(R.id.contactNameMain);
        contactNumber = getActivity().findViewById(R.id.contactNumberMain);

        String nameCheck = contactName.getText().toString();
        String numberCheck = contactNumber.getText().toString();

        observerSetup();
        recyclerSetup();

        Bundle args =getArguments();
        String nameStr = args.getString("name");
        String numberStr = args.getString("number");

        Contact c = new Contact(nameStr,numberStr);

        Log.i("mine", "contactName: "+contactName.getText().toString());
        Log.i("mine", "contactName: "+contactNumber.getText().toString());

        if (!nameCheck.equals("")){
            Log.i("mine", "name created check failed ");
            mViewModel.insertContact(c);
            clearFields();
        }

//        mViewModel.getFindResults(nameStr);
    }

    private void clearFields() {
//        contactId.setText("");
        contactName.setText("");
        contactNumber.setText("");
    }

    private void observerSetup() {
        mViewModel.getAllContacts().observe(getViewLifecycleOwner(), new Observer<List<Contact>>() {
            @Override
            public void onChanged(@Nullable final List<Contact> contacts) {
                adapter.setContactList(contacts);
            }
        });

//        mViewModel.getSearchResults().observe(getViewLifecycleOwner(), new Observer<List<Contact>>() {
//            @Override
//            public void onChanged(@Nullable final List<Contact> contacts) {
//                if (contacts.size() > 0) {
//                    contactId.setText(String.format(Locale.US, "%d",
//                            contacts.get(0).getId()));
//                    contactName.setText(contacts.get(0).getName());
//                    contactNumber.setText(String.format(Locale.US, "%d", contacts.get(0).getNumber()));
//                } else {
//                    contactId.setText("No Match");
//                }
//            }
//        });
//    }
    }
    private void recyclerSetup() {
        RecyclerView recyclerView;
        adapter = new ContactListAdapter(R.layout.contact_card, new ContactListAdapter.AdapterClickListener() {
            @Override
            public void AdapterClickListener(View v, int cardId) {

            }
        });
        recyclerView = getView().findViewById(R.id.contact_recycler);

        adapter.setAdapterClickListener(new ContactListAdapter.AdapterClickListener() {
            @Override
            public void AdapterClickListener(View v, int cardId) {
                String idStr = Integer.toString(cardId);
                String msg = "AdapterClickListenerFired:   " +idStr;
                Log.i("mine", msg);

                mViewModel.deleteContact(cardId);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }
//    private void recyclerSetup() {
//        RecyclerView recyclerView;
//        adapter = new ContactListAdapter(R.layout.contact_card, new ContactListAdapter.AdapterClickListener() {
//            @Override
//            public void AdapterClickListener(View v, int cardId) {
//
//            }
//        });
//
//        recyclerView = getView().findViewById(R.id.contactRecycler);
//
//
////        ImageView image  = getView().findViewById(R)
//
//
//        adapter.setAdapterClickListener(new ContactListAdapter.AdapterClickListener() {
//            @Override
//            public void AdapterClickListener(View v, int cardId) {
//                String idStr = Integer.toString(cardId);
//                String msg = "AdapterClickListenerFired:   " +idStr;
//                Log.i("mine", msg);
//
//                mViewModel.deleteContact(cardId);
////                mViewModel.getSearchResults();
////                Log.i("mine", mViewModel.getAllContacts().toString());
////                adapter.notifyDataSetChanged();
//
//            }
//        });
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.setAdapter(adapter);
//
//    }
}