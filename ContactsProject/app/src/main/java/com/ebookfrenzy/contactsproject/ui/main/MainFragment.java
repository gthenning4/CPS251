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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import com.ebookfrenzy.contactsproject.Contact;
import com.ebookfrenzy.contactsproject.R;

import java.util.List;
import java.util.Locale;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private ContactListAdapter adapter;

    private TextView contactId;
    private EditText contactName;
    private EditText contactNumber;

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
//        contactId = getView().findViewById(R.id.contactId);
        contactName = getActivity().findViewById(R.id.contactNameMain);
        contactNumber = getActivity().findViewById(R.id.contactNumberMain);
        listenerSetup();
        observerSetup();
        recyclerSetup();
    }
    private void clearFields() {
//        contactId.setText("");
        contactName.setText("");
        contactNumber.setText("");
    }
    private void listenerSetup() {

//        Button addButton = getView().findViewById(R.id.addBtn);
//        Button findButton = getView().findViewById(R.id.findBtn);
//        Button deleteButton = getView().findViewById(R.id.deleteBtn);
//        addButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String name = contactName.getText().toString();
//                String number = contactNumber.getText().toString();
//                if (!name.equals("") && !number.equals("")) {
//                    Contact contact = new Contact(name, number);
//                    mViewModel.insertContact(contact);
//                    clearFields();
//                } else {
////                    contactId.setText("Incomplete information");
//                    Context context = getActivity().getApplicationContext();
//                    CharSequence text = "Enter a name and a number";
//                    int duration = Toast.LENGTH_SHORT;
//
//                    Toast toast = Toast.makeText(context, text, duration);
//                    toast.show();
//                }
//            }
//        });
//
//        findButton.setOnClickListener(new View.OnClickListener() {
//            @Override public void onClick(View view) {
//                contactName = getView().findViewById(R.id.contactNameMain);
//                mViewModel.findContact(contactName.getText().toString());
//            }
//        });
//
//        deleteButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mViewModel.deleteContact(contactId.getText().toString());
//                clearFields();
//            }
//        });
    }
    private void observerSetup() {
        mViewModel.getAllContacts().observe(getViewLifecycleOwner(), new Observer<List<Contact>>(){
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
    }
    private void recyclerSetup() {
        RecyclerView recyclerView;
        adapter = new ContactListAdapter(R.layout.contact_card, new ContactListAdapter.AdapterClickListener() {
            @Override
            public void AdapterClickListener(View v, int cardId) {

            }
        });
        recyclerView = getView().findViewById(R.id.contact_recycler);
        adapter.getItemId(1);
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
}