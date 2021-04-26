package com.ebookfrenzy.contactsproject;

import android.app.Application;

import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.LiveData;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ContactRepository {
    private final MutableLiveData<List<Contact>> searchResults =
            new MutableLiveData<>();

    private LiveData<List<Contact>> allContacts;
//    private final List<Contact> findResults;

    private final ContactDao contactDao;

    public ContactRepository(Application application) {
        ContactRoomDatabase db;
        db = ContactRoomDatabase.getDatabase(application);
        contactDao = db.contactDao();
        allContacts = contactDao.getAllContacts();
//        findResults =contactDao.searchContacts();
    }

    private void asyncFinished(List<Contact> results) {
        searchResults.setValue(results);
    }

    private static class QueryAsyncTask extends
            AsyncTask<String, Void, List<Contact>>{
        private final ContactDao asyncTaskDao;
        private ContactRepository delegate = null;
        QueryAsyncTask(ContactDao dao) {
            asyncTaskDao = dao;
        }
        @Override
        protected List<Contact> doInBackground(final String... params) {
            return asyncTaskDao.searchContacts(params[0]);
        }
        @Override
        protected void onPostExecute(List<Contact> result) {
            delegate.asyncFinished(result);
        }
    }


    private static class InsertAsyncTask extends AsyncTask<Contact, Void, Void> {
        private final ContactDao asyncTaskDao;
        InsertAsyncTask(ContactDao dao) {
            asyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(final Contact... params) {
            asyncTaskDao.insertContact(params[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<String, Void, Void> {
        private final ContactDao asyncTaskDao;
        DeleteAsyncTask(ContactDao dao) {
            asyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(final String... params) {
            asyncTaskDao.deleteContact(Integer.parseInt(params[0]));
            return null;
        }
    }
    private static class SearchAsyncTask extends AsyncTask<String, Void, Void> {

        private final ContactDao asyncTaskDao;

        private ContactRepository delegate = null;
         SearchAsyncTask(ContactDao dao) {
            asyncTaskDao = dao;
        }
        @Override
        protected  Void doInBackground(final String... params) {
             asyncTaskDao.searchContacts(params[0]);
             return null;
        }
//        @Override
////        protected void onPostExecute(LiveData<List<Contact>> result) {
////            delegate.asyncFinished(result);
////        }
    }
    private static class SortAsyncTask extends AsyncTask <LiveData<List<Contact>>, Void, Void>{
        private final ContactDao asyncTaskDao;
        private ContactRepository delegate = null;
        SortAsyncTask (ContactDao dao){asyncTaskDao =dao;}

        @Override
        protected Void doInBackground(LiveData<List<Contact>>... liveData) {
            return null;
        }
    }

    public void insertContact(Contact newcontact) {
        InsertAsyncTask task = new InsertAsyncTask(contactDao);
        task.execute(newcontact);
    }
    public void deleteContact(String id) {
        DeleteAsyncTask task = new DeleteAsyncTask(contactDao);
        task.execute(id);
    }
    public void findContact(String name) {
        SearchAsyncTask task = new SearchAsyncTask(contactDao);
        task.execute(name);
    }
    public void searchContact(String name){
        QueryAsyncTask task = new QueryAsyncTask(contactDao);
        task.delegate = this;
        task.execute(name);
    }
    public void sortContactsAZ(LiveData<List<Contact>> allContacts){
        SortAsyncTask task = new SortAsyncTask(contactDao);
        task.delegate = this;
        task.execute(allContacts);
    }
    public LiveData<List<Contact>> getAllContacts() {
        return allContacts;
    }
    public MutableLiveData<List<Contact>> getSearchResults() {
        return searchResults;
    }
    public void setAllContacts(LiveData<List<Contact>> contacts){
        allContacts = contacts;
    }

}
