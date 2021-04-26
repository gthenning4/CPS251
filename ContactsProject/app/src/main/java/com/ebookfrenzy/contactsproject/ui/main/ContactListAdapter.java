package com.ebookfrenzy.contactsproject.ui.main;

import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.ebookfrenzy.contactsproject.R;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;
import com.ebookfrenzy.contactsproject.Contact;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;



public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ViewHolder> {

    private final int contactItemLayout;
    private MainViewModel mViewModel;
    private static AdapterClickListener clickListener;

    private List<Contact> contactList;
    public interface AdapterClickListener{
        void AdapterClickListener(View v, int cardId);
    }
    public void setAdapterClickListener(AdapterClickListener acl){
        clickListener = acl;
    }

    public ContactListAdapter(int layoutId, AdapterClickListener listener ) {
        contactItemLayout = layoutId;
        clickListener = listener;
    }

    public void setContactList(List<Contact> contacts) {
        contactList = contacts;
        notifyDataSetChanged();
    }
    public void sortContactsAZ(){
        Log.i("mine", "sortContactsAZ: ");
        Collections.sort(contactList, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Contact c1 = (Contact) o1;
                Contact c2 = (Contact) o2;
                return c1.getName().compareToIgnoreCase(c2.getName());
            }
        });
//        notifyDataSetChanged();
    }
    public void sortContactsZA(){
        Log.i("mine", "sortContactsAZ: ");
        Collections.sort(contactList, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Contact c1 = (Contact) o1;
                Contact c2 = (Contact) o2;
                return c2.getName().compareToIgnoreCase(c1.getName());
            }
        });
//        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return contactList == null ? 0 : contactList.size();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(
                parent.getContext()).inflate(contactItemLayout, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int listPosition) {
        TextView name = holder.name;
        TextView number = holder.number;
        TextView cardId = holder.cardId;
        ImageView image = holder.image;

        name.setText(contactList.get(listPosition).getName());
        number.setText(contactList.get(listPosition).getNumber());
        cardId.setText(String.valueOf(contactList.get(listPosition).getId()));

//        holder.bind(contactList.get(listPosition), listener);
//        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
//        mViewModel.deleteContact(Integer.parseInt(cardId.getText().toString()));
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        TextView number;
        TextView cardId;
        ImageView image;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.cardName);
            number = itemView.findViewById(R.id.cardNumber);
            image = itemView.findViewById(R.id.imageView);
            cardId = itemView.findViewById(R.id.cardId);
            image.setOnClickListener(this);
        }
        @Override
        public void onClick(View v){
            if(clickListener != null){
                clickListener.AdapterClickListener(v, Integer.parseInt(cardId.getText().toString()));
            }
        }
//        public void bind(final Contact contact, final OnItemClickListener listener) {
//
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override public void onClick(View v) {
//                    listener.onItemClick(contact);
//                }
//            });
//        }
    }

}
