package com.example.foodquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class QuizActivityDiet2 extends AppCompatActivity {

    /* radio buttons on pg 2 for diary*/
    RadioButton onetoTwo2;
    RadioButton twotoThree2;
    RadioButton morethanThree2;
    RadioButton neverVegan;
    RadioButton neverLactoseInt;
    RadioButton neverDontLike2;

    /* next button */
    Button btnNext2;

    /* back button */
    Button btnBack2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_quiz_pg2);
        //mAuth = FirebaseAuth.getInstance();

        /* radio buttons on pg 2 for diary*/
        RadioButton onetoTwo2 = findViewById(R.id.oneToTwoTimes2);
        RadioButton twotoThree2 = findViewById(R.id.twoToThreeTimes2);
        RadioButton morethanThree2 = findViewById(R.id.moreThanThreeTimes2);
        RadioButton neverVegan = findViewById(R.id.neverDairyVegan);
        RadioButton neverLactoseInt = findViewById(R.id.neverDairyLactoseInt);
        RadioButton neverDontLike2 = findViewById(R.id.neverDairyDontLike);

        /* next button */
        Button btnNext2 = findViewById(R.id.nextButton);

        /* back button */
        Button btnBack2 = findViewById(R.id.backButton2);


        btnNext2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizActivityDiet2.this, QuizActivityDiet3.class);
                startActivity(intent);
                finish();
            }
        });

        btnBack2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizActivityDiet2.this,QuizActivityDiet.class);
                startActivity(intent);
                finish();
            }
        });




    }
}
