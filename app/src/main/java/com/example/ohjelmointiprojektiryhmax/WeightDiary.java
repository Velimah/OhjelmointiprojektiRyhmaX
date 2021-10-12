package com.example.ohjelmointiprojektiryhmax;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * WeightDiary class contains a ListView of weights.
 *
 * @author  Veli-Matti Heino
 * @version 1.1
 * @since   2021-10-11
 */
public class WeightDiary extends AppCompatActivity {
    private Button saveButton;
    private EditText editTextNewWeight;
    private ListView listView;

    // creates an arraylist to have weight updates
    ArrayAdapter<String> adapter;
    ArrayList<String> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_diary);



        saveButton = findViewById(R.id.saveButton);
        editTextNewWeight = findViewById(R.id.editTextNewWeight);listView = findViewById(R.id.listView);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);

        // gets weight goal from MainActivity and shows it
        Intent intent = getIntent();
        String message4 = intent.getStringExtra(MainActivity.MESSAGE4);
        TextView tv5 = findViewById(R.id.textView5);
        tv5.setText(message4 + " Kg");

    }

    /**
     * saveData() saves new weight into arraylist and shows it in ListView . Not Complete.
     */
    public void saveData2(View view) {
        if (editTextNewWeight.getText().toString().isEmpty()) {
            editTextNewWeight.setError("Enter weight");
        }
        else {

            String one = editTextNewWeight.getText().toString();
            list.add(one);
            adapter.notifyDataSetChanged();

            if (list != null && !list.isEmpty()) {

            }
            editTextNewWeight.setText("");

        }
    }

}