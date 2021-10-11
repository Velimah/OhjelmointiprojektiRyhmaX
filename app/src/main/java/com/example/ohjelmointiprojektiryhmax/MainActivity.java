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

/** MainActivity Class gathers body measurements from editText boxes and RadioButton, calculates Body Mass Index,
 * Basal Metabolic Rate and Body Fat Percentage and sends them to Statistics Class.
 *
 * @author  Veli-Matti Heino
 * @version 1.1
 * @since   2021-10-11
 */
public class MainActivity extends AppCompatActivity {

    //Local variables
    private static final String SHARED_PREFS = "sharedPrefs";
    private static final String AGE = "age";
    private static final String HEIGHT = "height";
    private static final String WEIGHT = "weight";
    private static final String NECK = "neck";
    private static final String WAIST = "waist";
    private static final String HIP = "hip";
    private static final String GOAL = "goal";

    private String ageSharedPref;
    private String heightSharedPref;
    private String weightSharedPref;
    private String neckSharedPref;
    private String waistSharedPref;
    private String hipSharedPref;
    private String goalSharedPref;

    public static final String MESSAGE = "1";
    public static final String MESSAGE2 = "2";
    public static final String MESSAGE3 = "3";


    EditText age, height, weight, neck, waist, hip, goal;
    String BmiResult, BmrResult, BfpResult;
    Button resetButton;
    RadioGroup radioGroup;
    DecimalFormat df = new DecimalFormat("#.##");


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
        goal = findViewById(R.id.editTextTextGoal);
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

    /**
     * onPause() saves age, height, weight, neck, waist and hip values into SharedPreferences
     */
    @Override
    protected void onPause() {
        super.onPause();
        saveData();
    }

    /**
     * onResume() loads age, height, weight, neck, waist and hip values from SharedPreferences
     * and then updates all editText boxes with the SharedPreference values.
     */
    @Override
    protected void onResume() {
        super.onResume();
        loadData();
        updateUI();
    }

    /**
     * saveData() creates SharedPreferences for age, height, weight, neck, waist and hip values,
     * and then saves the values into SharedPreferences.
     */
    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(AGE, age.getText().toString());
        editor.putString(HEIGHT, height.getText().toString());
        editor.putString(WEIGHT, weight.getText().toString());
        editor.putString(NECK, neck.getText().toString());
        editor.putString(WAIST, waist.getText().toString());
        editor.putString(HIP, hip.getText().toString());
        editor.putString(GOAL, goal.getText().toString());

        editor.apply();
    }

    /**
     * loadData() gets SharedPreferences from storage and then saves them into Strings.
     */
    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);

        ageSharedPref = sharedPreferences.getString(AGE, "");
        heightSharedPref = sharedPreferences.getString(HEIGHT, "");
        weightSharedPref = sharedPreferences.getString(WEIGHT, "");
        neckSharedPref = sharedPreferences.getString(NECK, "");
        waistSharedPref = sharedPreferences.getString(WAIST, "");
        hipSharedPref = sharedPreferences.getString(HIP, "");
        goalSharedPref = sharedPreferences.getString(GOAL, "");
    }

    /**
     * resetData() sets sharedPref String values to null.
     */
    public void resetData() {
        ageSharedPref = null;
        heightSharedPref = null;
        weightSharedPref = null;
        neckSharedPref = null;
        waistSharedPref = null;
        hipSharedPref = null;
        goalSharedPref = null;

    }

    /**
     * updateUI() sets all editText values to the saved sharedPref String values.
     */
    public void updateUI() {
        age.setText(ageSharedPref);
        height.setText(heightSharedPref);
        weight.setText(weightSharedPref);
        neck.setText(neckSharedPref);
        waist.setText(waistSharedPref);
        hip.setText(hipSharedPref);
        goal.setText(goalSharedPref);
    }

    /**
     * openStatistics() reads all the editText values and radioButton value, Then calculates health data and
     * opens a new ViewGroup with the information.
     */
    public void openStatistics(View view) {

        Intent intent = new Intent(this, Statistics.class);

         // checks if the editText fields are empty and gives an error message if needed.
        if (age.getText().toString().isEmpty()) {
            age.setError("Enter age");
        }
        else if (height.getText().toString().isEmpty()) {
            height.setError("Enter height");
        }
        else if (weight.getText().toString().isEmpty()) {
            weight.setError("Enter weight");
        }
        else if (neck.getText().toString().isEmpty()) {
            neck.setError("Enter neck circumference");
        }
        else if (waist.getText().toString().isEmpty()) {
            waist.setError("Enter waist circumference");
        }
        else if (hip.getText().toString().isEmpty()) {
            hip.setError("Enter hip circumference");
        } else {

            //reads editText field editTexts and changes them to Strings
            String one = age.getText().toString();
            String two = height.getText().toString();
            String three = weight.getText().toString();
            String four = neck.getText().toString();
            String five = waist.getText().toString();
            String six = hip.getText().toString();

            //changes Strings to floats to be able to count
            float ageV = Float.parseFloat(one);
            float heightV = Float.parseFloat(two);
            float weightV = Float.parseFloat(three);
            float neckV = Float.parseFloat(four);
            float waistV = Float.parseFloat(five);
            float hipV = Float.parseFloat(six);

            //calculates Body Mass Index
            float bmi = weightV / ((heightV / 100) * (heightV / 100));

            //chooses the correct String based on bmi value.
            if (bmi < 15) {
                BmiResult = " Very severely underweight";
            } else if (bmi < 16) {
                BmiResult = " Severely underweight";
            } else if (bmi < 18.5) {
                BmiResult = " Underweight";
            } else if (bmi < 25) {
                BmiResult = " Normal";
            } else if (bmi < 30) {
                BmiResult = " Overweight";
            } else if (bmi < 35) {
                BmiResult = " Moderately obese";
            } else if (bmi < 40) {
                BmiResult = " Severely obese";
            } else if (bmi < 45) {
                BmiResult = " Very severely obese";
            } else if (bmi < 50) {
                BmiResult = " Morbidly obese";
            } else if (bmi < 60) {
                BmiResult = " Super obese";
            } else {
                BmiResult = " Hyper obese";
            }
            /* Changes bmi to .xx digit format and adds the correct BmiResult String then combines them
            into one String and puts it into MESSAGE. */
            String BMI = (df.format(bmi) + BmiResult);
            intent.putExtra(MESSAGE, BMI);

            // Checks the male/female Radiobutton and chooses the correct calculation.
            int checkedId = radioGroup.getCheckedRadioButtonId();
            if (checkedId == R.id.rButtonMale) {
                //Calculates Basal Metabolic Rate for males
                double bmr = 13.397 * weightV + 4.799 * heightV - 5.677 * ageV + 88.362;
                BmrResult = " Kcal";

                /*  Changes bmr to .xx digit format and adds BmrResult String then combines them
                into one String and puts it into MESSAGE2. */
                String BMR = (df.format(bmr) + BmrResult);
                intent.putExtra(MESSAGE2, BMR);

            } else if (checkedId == R.id.rButtonFemale) {
                // Calculates Basal Metabolic Rate for females
                double bmr = 9.247 * weightV + 3.098 * heightV - 4.330 * ageV + 447.593;
                BmrResult = " Kcal";

                /*  Changes bmr to .xx digit format and adds BmrResult String then combines them
                into one String and puts it into MESSAGE2. */
                String BMR = (df.format(bmr) + BmrResult);
                intent.putExtra(MESSAGE2, BMR);
            }
            // Checks the male/female Radiobutton and chooses the correct calculation.
            if (checkedId == R.id.rButtonMale) {
                //Calculates Body Fat Percentage for males
                double bfp = (495 / (1.0324 - (0.19077 * Math.log10((waistV - neckV))) + (0.15456 * Math.log10(heightV)))) - 450;
                BfpResult = " %";

                /*  Changes bfp to .xx digit format and adds BfpResult String then combines them
                into one String and puts it into MESSAGE3. */
                String BFP = (df.format(bfp) + BfpResult);
                intent.putExtra(MESSAGE3, BFP);

            } else if (checkedId == R.id.rButtonFemale) {
                //Calculates Body Fat Percentage for females
                double bfp = (495 / (1.29579 - (0.35004 * Math.log10((waistV + hipV - neckV))) + (0.22100 * Math.log10(heightV)))) - 450;
                BfpResult = " %";

                /*  Changes bfp to .xx digit format and adds BfpResult String then combines them
                into one String and puts it into MESSAGE3. */
                String BFP = (df.format(bfp) + BfpResult);
                intent.putExtra(MESSAGE3, BFP);
            }
            // Opens Statistics Activity ViewGroup containing results of the calculations
            startActivity(intent);
        }
    }

    /**
     * openWeightDiary() opens a new ViewGroup with a weight diary.
     */
    public void openWeightDiary(View view) {
        Intent intent = new Intent(this, WeightDiary.class);
        startActivity(intent);
    }
}
