package com.example.ohjelmointiprojektiryhmax;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Statistic class shows Body Mass Index, Basal Metabolic Rate and Body Fat Percentage
 * based on calculations in MainActivity Class
 *
 * @author  Veli-Matti Heino
 * @version 1.1
 * @since   2021-10-11
 */
public class Statistics extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        // gets BMI, BMR and BFP Strings from MESSAGES
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.MESSAGE);
        String message2 = intent.getStringExtra(MainActivity.MESSAGE2);
        String message3 = intent.getStringExtra(MainActivity.MESSAGE3);

        //sets textViews to show BMI, BMR, BFP
        TextView tv = findViewById(R.id.BMI);
        tv.setText(message);
        TextView tv2 = findViewById(R.id.BMR);
        tv2.setText(message2);
        TextView tv3 = findViewById(R.id.BFP);
        tv3.setText(message3);

    }
}

