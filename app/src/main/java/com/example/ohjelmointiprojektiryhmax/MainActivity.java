package com.example.ohjelmointiprojektiryhmax;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    public static final String EXTRA_MESSAGE2 = "com.example.myfirstapp.MESSAGE2";
    public static final String EXTRA_MESSAGE3 = "com.example.myfirstapp.MESSAGE3";
    RadioGroup radioGroup;
    EditText age, height, weight, neck, waist, hip;
    String BMIresult, BMRresult, BFPresult;
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

        radioGroup = findViewById(R.id.radioGroup);


    }


    public void sendMessage(View view) {

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
            BMIresult = " Sairaalloinen alipaino";
        }else if (bmi < 18){
            BMIresult = " Merkittävä alipaino";
        }else if (bmi < 19){
            BMIresult = " Lievä alipaino";
        }else if (bmi < 25){
            BMIresult = " Normaali paino";
        }else if (bmi < 30){
            BMIresult = " Lievä ylipaino";
        }else if (bmi < 35){
            BMIresult = " Merkittävä ylipaino";
        }else if (bmi < 40){
            BMIresult = " Vaikea ylipaino";
        }else{
            BMIresult = " Sairaalloinen ylipaino";
        }
        String BMI = (df.format(bmi) + BMIresult);
        intent.putExtra(EXTRA_MESSAGE, BMI);


        int checkedId = radioGroup.getCheckedRadioButtonId();
        if (checkedId == R.id.rButtonMale) {
            double bmr = 13.397 * weightV + 4.799 * heightV - 5.677 * ageV + 88.362;
            BMRresult = " Kcal";
            String BMR = (df.format(bmr)+ BMRresult);
            intent.putExtra(EXTRA_MESSAGE2, BMR);
        } else if (checkedId == R.id.rButtonFemale) {
            double bmr = 9.247 * weightV + 3.098 * heightV- 4.330 * ageV + 447.593;
            BMRresult = " Kcal";
            String BMR = (df.format(bmr) + BMRresult);
            intent.putExtra(EXTRA_MESSAGE2, BMR);
        }

        if (checkedId == R.id.rButtonMale) {
            double bfp = (495 / (1.0324 - (0.19077 * Math.log10((waistV - neckV))) + (0.15456 * Math.log10(heightV)))) - 450;
            BFPresult = " %";
            String BFP = (df.format(bfp) + BFPresult);
            intent.putExtra(EXTRA_MESSAGE3, BFP);
        } else if (checkedId == R.id.rButtonFemale) {
            double bfp = (495 / (1.29579 - (0.35004 * Math.log10((waistV + hipV - neckV))) + (0.22100 * Math.log10(heightV)))) - 450;
            BFPresult = " %";
            String BFP = (df.format(bfp) + BFPresult);
            intent.putExtra(EXTRA_MESSAGE3, BFP);
        }
        startActivity(intent);
    }
}
