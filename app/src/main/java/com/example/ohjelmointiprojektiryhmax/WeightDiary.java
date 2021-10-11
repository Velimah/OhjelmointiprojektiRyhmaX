package com.example.ohjelmointiprojektiryhmax;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class WeightDiary extends AppCompatActivity {
    private Button saveButton;
    private EditText editTextNewWeight;
    private ListView listView;

    ArrayAdapter<String> adapter;
    ArrayList<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_diary);

        saveButton = findViewById(R.id.saveButton);
        editTextNewWeight = findViewById(R.id.editTextNewWeight);
        listView = findViewById(R.id.listView);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);

    }

    public void saveData(View view) {
        if (editTextNewWeight.getText().toString().isEmpty()) {
            editTextNewWeight.setError("Enter weight");
        }
        else {
            list.add(editTextNewWeight.getText().toString());
            editTextNewWeight.setText("");
            adapter.notifyDataSetChanged();
        }

    }
}