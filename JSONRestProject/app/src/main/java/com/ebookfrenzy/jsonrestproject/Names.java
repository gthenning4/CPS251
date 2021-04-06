package com.ebookfrenzy.jsonrestproject;

import java.util.ArrayList;

public class Names {
    ArrayList<Name> nameList= new ArrayList<Name>();

    public ArrayList<Name> getNames(){
        return nameList;
    }
    public void setNames(ArrayList<Name> nl){
        nameList = nl;
    }
    public int nameSize(){
        return nameList.size();
    }
    public void addName(Name name){
        nameList.add(name);
    }

    @Override
    public String toString() {
        return "Names{" +
                "nameList=" + nameList +
                '}';
    }
}
