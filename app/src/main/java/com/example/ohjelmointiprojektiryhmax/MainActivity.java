package com.example.ohjelmointiprojektiryhmax;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private static final String SHARED_PREFS = "sharedPrefs";
    private static final String AGE = "age";
    private static final String HEIGHT = "height";
    private static final String WEIGHT = "weight";
    private static final String NECK = "neck";
    private static final String WAIST = "waist";
    private static final String HIP = "hip";

    private String ageSp;
    private String heightSp;
    private String weightSp;
    private String neckSp;
    private String waistSp;
    private String hipSp;

    public static final String MESSAGE = "1";
    public static final String MESSAGE2 = "2";
    public static final String MESSAGE3 = "3";

    RadioGroup radioGroup;
    EditText age, height, weight, neck, waist, hip;
    String BmiResult, BmrResult, BfpResult;
    DecimalFormat df = new DecimalFormat("#.##");
    Button saveButton, resetButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        age = findViewById(R.id.editTextAge);
        height = findViewById(R.id.editTextHeight);
        weight = findViewById(R.id.editTextWeight);
        neck = findViewById(R.id.editTextNeck);
        waist = findViewById(R.id.editTextWaist);
        hip = findViewById(R.id.editTextHip);
        radioGroup = findViewById(R.id.radioGroup);
        resetButton = findViewById(R.id.resetButton);

        loadData();
        updateUI();

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetData();
                updateUI();
                saveData();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
        updateUI();
    }

    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(AGE, age.getText().toString());
        editor.putString(HEIGHT, height.getText().toString());
        editor.putString(WEIGHT, weight.getText().toString());
        editor.putString(NECK, neck.getText().toString());
        editor.putString(WAIST, waist.getText().toString());
        editor.putString(HIP, hip.getText().toString());

        editor.apply();
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);

        ageSp = sharedPreferences.getString(AGE, "");
        heightSp = sharedPreferences.getString(HEIGHT, "");
        weightSp = sharedPreferences.getString(WEIGHT, "");
        neckSp = sharedPreferences.getString(NECK, "");
        waistSp = sharedPreferences.getString(WAIST, "");
        hipSp = sharedPreferences.getString(HIP, "");
    }

    public void resetData() {
        ageSp = null;
        heightSp = null;
        weightSp = null;
        neckSp = null;
        waistSp = null;
        hipSp = null;

    }

    public void updateUI() {
        age.setText(ageSp);
        height.setText(heightSp);
        weight.setText(weightSp);
        neck.setText(neckSp);
        waist.setText(waistSp);
        hip.setText(hipSp);
    }

    public void openStatistics(View view) {

        Intent intent = new Intent(this, Statistics.class);

        String one = age.getText().toString();
        String two = height.getText().toString();
        String three = weight.getText().toString();
        String four = neck.getText().toString();
        String five = waist.getText().toString();
        String six = hip.getText().toString();

        float ageV = Float.parseFloat(one);
        float heightV = Float.parseFloat(two);
        float weightV = Float.parseFloat(three);
        float neckV = Float.parseFloat(four);
        float waistV = Float.parseFloat(five);
        float hipV = Float.parseFloat(six);

        float bmi = weightV / ((heightV / 100) * (heightV / 100));

        if (bmi < 15){
            BmiResult = " Very severely underweight";
        }else if (bmi < 16){
            BmiResult = " Severely underweight";
        }else if (bmi < 18.5){
            BmiResult = " Underweight";
        }else if (bmi < 25){
            BmiResult = " Normal";
        }else if (bmi < 30){
            BmiResult = " Overweight";
        }else if (bmi < 35){
            BmiResult = " Moderately obese";
        }else if (bmi < 40){
            BmiResult = " Severely obese";
        }else if (bmi < 45){
            BmiResult = " Very severely obese";
        }else if (bmi < 50){
            BmiResult = " Morbidly obese";
        }else if (bmi < 60){
            BmiResult = " Super obese";
        }else{
            BmiResult = " Hyper obese";
        }
        String BMI = (df.format(bmi) + BmiResult);
        intent.putExtra(MESSAGE, BMI);

        int checkedId = radioGroup.getCheckedRadioButtonId();
        if (checkedId == R.id.rButtonMale) {
            double bmr = 13.397 * weightV + 4.799 * heightV - 5.677 * ageV + 88.362;
            BmrResult = " Kcal";
            String BMR = (df.format(bmr)+ BmrResult);
            intent.putExtra(MESSAGE2, BMR);
        } else if (checkedId == R.id.rButtonFemale) {
            double bmr = 9.247 * weightV + 3.098 * heightV- 4.330 * ageV + 447.593;
            BmrResult = " Kcal";
            String BMR = (df.format(bmr) + BmrResult);
            intent.putExtra(MESSAGE2, BMR);
        }

        if (checkedId == R.id.rButtonMale) {
            double bfp = (495 / (1.0324 - (0.19077 * Math.log10((waistV - neckV))) + (0.15456 * Math.log10(heightV)))) - 450;
            BfpResult = " %";
            String BFP = (df.format(bfp) + BfpResult);
            intent.putExtra(MESSAGE3, BFP);
        } else if (checkedId == R.id.rButtonFemale) {
            double bfp = (495 / (1.29579 - (0.35004 * Math.log10((waistV + hipV - neckV))) + (0.22100 * Math.log10(heightV)))) - 450;
            BfpResult = " %";
            String BFP = (df.format(bfp) + BfpResult);
            intent.putExtra(MESSAGE3, BFP);
        }
        startActivity(intent);
    }

}
