package com.ebookfrenzy.asyncproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Data myData = new Data();
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
//    private String nameStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecycleAdapter();
        recyclerView.setAdapter(adapter);

        Button addBtn = findViewById(R.id.addBtn);
        Button clearBtn = findViewById(R.id.ClearBtn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doNameTask();
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myData.clearNames();
                adapter.notifyDataSetChanged();
            }
        });

    }
    private class MyTask extends AsyncTask<String, Integer, String> {

        public String name;
        public Integer taskR;

        @Override
        protected void onPreExecute() {
        }
        @Override
        protected String doInBackground(String... params) {
                try {

                    Long r = Long.parseLong(params[1]);
                    taskR = r.intValue();
                    name = params[0];
                    Thread.sleep(r*1000);
                }
                catch (Exception e) {
                    return(e.getLocalizedMessage());
                }
            return "background finished";
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
        }

        @Override
        protected void onPostExecute(String result) {
            myData.addToRandList(taskR);
            myData.addName(name);
            adapter.notifyDataSetChanged();
        }
    }

    private Integer getRandInt(){
        int r = new Random().nextInt(10);
        return r;
    }

    private void doNameTask(){
        EditText name = findViewById(R.id.enterName);
        String nameStr = name.getText().toString();

        Integer rand= getRandInt();
        String randStr = rand.toString();

        String[] params ={nameStr,randStr};
        AsyncTask task = new
                MyTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, params);
        name.setText("");
    }

}