package com.example.foodquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

public class QuizActivityDiet3 extends AppCompatActivity {
    /* checkboxes on pg 3 for diet*/
    CheckBox watchingWeight;
    CheckBox amVegan;
    CheckBox amVegetarian;
    CheckBox amPescatarian;
    CheckBox followSpecificDiet;
    CheckBox healthIssueDiet;
    CheckBox noRestrictions;

    /* next button */
    Button btnNext3;

    /* back button */
    Button btnBack3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_pg3);

        /* checkboxes on pg 3 for diet*/
        CheckBox watchingWeight = findViewById(R.id.watchingMyWeight);
        CheckBox amVegan = findViewById(R.id.imVegan);
        CheckBox amVegetarian = findViewById(R.id.imVegetarian);
        CheckBox amPescatarian = findViewById(R.id.imPescatarian);
        CheckBox followSpecificDiet = findViewById(R.id.followDiet);
        CheckBox healthIssueDiet = findViewById(R.id.healthIssues);
        CheckBox noRestrictions = findViewById(R.id.noDiet);

        /* next button */
        Button btnNext3 = findViewById(R.id.nextButton3);

        /* back button */
        Button btnBack3 = findViewById(R.id.backButton3);


        btnNext3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizActivityDiet3.this, QuizPreferencesDisplay.class);
                startActivity(intent);
                finish();
            }
        });



        btnBack3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizActivityDiet3.this,QuizActivityDiet2.class);
                startActivity(intent);
                finish();
            }
        });


    }

}
