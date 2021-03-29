package com.ebookfrenzy.asyncproject;

import android.util.Log;

import java.util.ArrayList;

public class Data {

    public static ArrayList names =new ArrayList();
    public static ArrayList randList = new ArrayList();

    public String  getName(int i){
        return (String) names.get(i);
    }
    public int getNamesLen (){
        return names.size();
    }
    public void addName(String newName){
        names.add(newName);
    }
    public void clearNames(){
        names.clear();;
    }
    public void addToRandList(Integer num){
        randList.add(num);
        Log.i("i", "addToRandList: "+num);
    }
    public String getRandAt(Integer index){
        Integer rand = (Integer) randList.get(index);
        String randStr = rand.toString();
        return randStr;
    }
}
