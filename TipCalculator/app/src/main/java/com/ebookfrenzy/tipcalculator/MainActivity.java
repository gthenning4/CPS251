package com.ebookfrenzy.tipcalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        TextView TenPer = findViewById(R.id.TenPerView);
        CharSequence TenChar = TenPer.getText();
        TextView FiftPer = findViewById(R.id.FifteenPerView);
        CharSequence FiftChar = FiftPer.getText();
        TextView TwentyPer = findViewById(R.id.TwentyPerView);
        CharSequence TwentyChar = TwentyPer.getText();

        outState.putCharSequence("TenChar", TenChar);
        outState.putCharSequence("FiftChar", FiftChar);
        outState.putCharSequence("TwentyChar", TwentyChar);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        TextView TenPer = findViewById(R.id.TenPerView);
        TextView FiftPer = findViewById(R.id.FifteenPerView);
        TextView TwentyPer = findViewById(R.id.TwentyPerView);

        CharSequence TenChar = savedInstanceState.getCharSequence("TenChar");
        CharSequence FiftChar = savedInstanceState.getCharSequence("FiftChar");
        CharSequence TwentyChar = savedInstanceState.getCharSequence("TwentyChar");

        TenPer.setText(TenChar);
        FiftPer.setText(FiftChar);
        TwentyPer.setText(TwentyChar);
    }

    public void calcTip(View v){
        TextView TenPer = findViewById(R.id.TenPerView);
        TextView FifteenPer= findViewById(R.id.FifteenPerView);
        TextView TwentyPer= findViewById(R.id.TwentyPerView);
        EditText AmountTotal = findViewById(R.id.AmountTotal);

        String AmtStr = AmountTotal.getText().toString();

        if(AmtStr.matches("")){
            TenPer.setText("You must enter an amount.");
            FifteenPer.setText("");
            TwentyPer.setText(String.valueOf(""));
        }
        else{
            double amount = Double.parseDouble(String.valueOf(AmountTotal.getText()));
            double TenDouble = amount * .1;
            double FifteenDouble = amount * .15;
            double TwentyDouble = amount * .20;

            TenDouble = new BigDecimal(TenDouble).setScale(2, RoundingMode.HALF_UP).doubleValue();

            TenPer.setText("A 10% tip equals "+"$"+String.format("%1.2f", TenDouble));
            FifteenPer.setText("A 15% tip equals "+"$"+String.format("%1.2f", FifteenDouble));
            TwentyPer.setText("A 20% tip equals "+"$"+String.format("%1.2f", TwentyDouble));
        }
    }

}