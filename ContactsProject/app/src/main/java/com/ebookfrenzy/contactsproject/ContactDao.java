package com.ebookfrenzy.contactsproject;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface ContactDao {

    @Insert
    void insertContact(Contact contact);

    @Query("SELECT * FROM contacts WHERE contactName LIKE '%' || :name ||'%'")
    List<Contact> findContact(String name);

    @Query("DELETE FROM contacts WHERE contactId = :id")
    void deleteContact(int id);

    @Query("SELECT * FROM contacts")
    LiveData<List<Contact>> getAllContacts();

    @Query("SELECT * FROM contacts WHERE contactName LIKE '%' || :name ||'%'")
    List<Contact> searchContacts(String name);

//    @Query("SELECT * FROM contacts ORDER BY contactName COLLATE NOCASE ASC")
//    List<Contact> getContactsAZ();


}
