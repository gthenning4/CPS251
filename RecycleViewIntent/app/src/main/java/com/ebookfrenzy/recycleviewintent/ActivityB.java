package com.ebookfrenzy.recycleviewintent;

import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ActivityB extends AppCompatActivity {
    String title="no title found";
    String detail="no detail found";
    Integer index=0;
    Data myData = new Data();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            title = extras.getString("title");
            detail = extras.getString("detail");
            String indexStr =extras.getString("index");
            index = Integer.parseInt(indexStr);
        }


        TextView text1=findViewById(R.id.text1);
        TextView text3=findViewById(R.id.text3);
        ImageView img= findViewById(R.id.imageView);

        text1.setText(title);
        text3.setText(detail);
        img.setImageResource(myData.getImgByIndex(index));

    }
}