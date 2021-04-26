package com.ebookfrenzy.contactsproject;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "contacts")
public class Contact {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="contactId")
    private int id;

    @ColumnInfo(name="contactName")
    private String name;

    @ColumnInfo(name="contactNumber")
    private String number;


    public Contact(String name, String number){
        this.id=id;
        this.name = name;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(@NonNull  int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
