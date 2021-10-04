package com.example.ohjelmointiprojektiryhmax;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class Statistics extends AppCompatActivity {

    TextView BMI;
    String BMIresult,messageHeight, messageWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        String message2 = intent.getStringExtra(MainActivity.EXTRA_MESSAGE2);
        String message3 = intent.getStringExtra(MainActivity.EXTRA_MESSAGE3);

        TextView tv = findViewById(R.id.BMI);
        tv.setText(message);
        TextView tv2 = findViewById(R.id.BMR);
        tv2.setText(message2);
        TextView tv3 = findViewById(R.id.BFP);
        tv3.setText(message3);



    }
}

