package com.example.foodquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodquiz.QuizActivityDiet;
import com.example.foodquiz.QuizWelcomeDisplay;
import com.example.foodquiz.R;

public class QuizActivityPreferences extends AppCompatActivity {

    /* checkboxes for preferences pg1 */
    CheckBox low_sodium;
    CheckBox low_carbs;
    CheckBox low_sugar;
    CheckBox high_protein;
    CheckBox no_preference;

    /* next button */
    Button btnNext;

    /* return to section1 button */
    Button returnToSec1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_pg4);

        /* checkboxes for preferences pg1 */
        CheckBox low_sodium = findViewById(R.id.lowSodium);
        CheckBox low_carbs = findViewById(R.id.lowCarb);
        CheckBox low_sugar = findViewById(R.id.lowSugar);
        CheckBox high_protein = findViewById(R.id.highProtein);
        CheckBox no_preference = findViewById(R.id.noPreference);

        /* next button */
        Button btnNext = findViewById(R.id.nextButton);

        /* return to section1 button */
        Button returnToSec1 = findViewById(R.id.returnToDietSection);


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizActivityPreferences.this, QuizAvoidsDisplay.class);
                startActivity(intent);
                finish();
            }
        });



        returnToSec1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizActivityPreferences.this, QuizActivityDiet.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
