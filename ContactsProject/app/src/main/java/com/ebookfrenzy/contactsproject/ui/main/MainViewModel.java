package com.ebookfrenzy.contactsproject.ui.main;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel; import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.ebookfrenzy.contactsproject.Contact;
import com.ebookfrenzy.contactsproject.ContactRepository;
import java.util.List;

public class MainViewModel extends AndroidViewModel {
    // TODO: Implement the ViewModel
    private ContactRepository repository;
    private LiveData<List<Contact>> allContacts;
    private LiveData<List<Contact>> searchResults;
    public MainViewModel (Application application) {
        super(application);
        repository = new ContactRepository(application);
        allContacts = repository.getAllContacts();
        searchResults = repository.getSearchResults();
    }

    LiveData<List<Contact>> getSearchResults() {
        return searchResults;
    }
//    List<Contact> getFindResults(String name) {
//       return repository.searchContact(name);
//    }
    LiveData<List<Contact>> getAllContacts() {
        return allContacts;
    }
    public void insertContact(Contact contact) {
        repository.insertContact(contact);
    }
    public void findContact (String name) {
        repository.findContact(name);
    }
    public void searchContacts(String name){
        repository.searchContact(name);
    }
    public void deleteContact (int id) {
        repository.deleteContact(Integer.toString(id));
    }
    public void sortAZ(){
        repository.sortContactsAZ(allContacts);
        Log.i("mine", "sortAZ: ");
    }
}