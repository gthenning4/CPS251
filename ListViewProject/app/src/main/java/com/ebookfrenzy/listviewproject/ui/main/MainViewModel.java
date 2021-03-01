package com.ebookfrenzy.listviewproject.ui.main;

import androidx.lifecycle.ViewModel;
import java.util.ArrayList;

public class MainViewModel extends ViewModel {
    private ArrayList nameArr= new ArrayList();
    private String myText ="";


    public void setMyText(String input){
        this.myText = input;
    }
    public CharSequence getMyText(){
        return myText;
    }
    public void addName(CharSequence name){
        this.nameArr.add(name);
    }
    public ArrayList<String> getNames(){
        return this.nameArr;
    }
}