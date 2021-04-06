package com.ebookfrenzy.jsonrestproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    Names names = new Names();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.namesRV);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);



        RequestQueue queue = Volley.newRequestQueue(this);

        String url ="http://198.199.80.235/cps276/cps276_examples/datasources/names_json_251v2.json";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(

                //WE ARE SENDING A GET REQUEST (WE ARE GETTING A FILE)
                Request.Method.GET,

                //THIS IS THE URL
                url,

                null,

                new Response.Listener<JSONObject>() {
                    //THIS LISTENS FOR THE RESPONSE
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONObject json = response;
                        try {
                            JSONArray jArray = json.getJSONArray("names");
                            Log.i("mine",jArray.toString());
                            Log.i("mine", "length: " + String.valueOf(jArray.length()));
                            for(int i=0; i < jArray.length(); i++){
                                JSONObject obj = jArray.getJSONObject(i);

                                Name tempName = new Name();

                                String tempAge = obj.getString("age");
                                tempName.setAge(Integer.parseInt(tempAge));
                                String tempEyeColor = obj.getString("eyeColor");
                                tempName.setEyeColor(tempEyeColor);
                                String tempNameName = obj.getString("name");
                                tempName.setName(tempNameName);
                                String tempGender = obj.getString("gender");
                                tempName.setGender(tempGender);
                                String tempCompany = obj.getString("company");
                                tempName.setCompany(tempCompany);
                                String tempEmail = obj.getString("email");
                                tempName.setEmail(tempEmail);
                                String tempPhone = obj.getString("phone");
                                tempName.setPhone(tempPhone);
                                String tempAddress = obj.getString("address");
                                tempName.setAddress(tempAddress);
                                Integer tempIndex = i+1;
                                tempName.setIndex(tempIndex);

                                names.addName(tempName);
//                                arrList.add(obj);

                                Log.i("mine", tempName.toString());
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        //GETS THE COUPON LIST FROM COUPONSWRAPPER AND PUTS IT INTO cpnlst AND ADDS IT AS A PARAMTER
                        //TO THE RecyclerViewAdaptor() CONSTRUCTOR

                        RecycleAdapter myAdapter = new RecycleAdapter(names.getNames());
                        recyclerView.setAdapter(myAdapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //DO SOMETHING HERE WITH ERROR MESSAGE
                        Log.i("mine","executed error Response");
                    }
                }
        );
//
//
//        //ADDS OUR REQUEST TO THE QUE FOR PROCESSING.
        queue.add(jsonObjectRequest);
    }
}
