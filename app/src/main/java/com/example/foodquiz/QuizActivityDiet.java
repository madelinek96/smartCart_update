package com.example.foodquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class QuizActivityDiet extends AppCompatActivity{

   /* radio buttons on pg 1 for red meat*/
    RadioButton onetoTwo;
    RadioButton twotoThree;
    RadioButton morethanThree;
    RadioButton neverVeganVegPesc;
    RadioButton neverDontLike;

    /* next button */
    Button btnNext;

    /* back button */
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_quiz_pg1);
        //mAuth = FirebaseAuth.getInstance();

        /* radio buttons on pg 1 for red meat*/
        RadioButton onetoTwo = findViewById(R.id.oneToTwoTimes);
        RadioButton twotoThree = findViewById(R.id.twoToThreeTimes);
        RadioButton morethanThree = findViewById(R.id.moreThanThreeTimes);
        RadioButton neverVeganVegPesc = findViewById(R.id.neverMeatImVegan);
        RadioButton neverDontLike = findViewById(R.id.neverMeatDontLike);


        /* next button */
        Button btnNext = findViewById(R.id.nextButton);

        /* back button */
        Button btnBack = findViewById(R.id.backButton);


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizActivityDiet.this, QuizActivityDiet2.class);
                startActivity(intent);
                finish();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizActivityDiet.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });



    }


}
