package com.example.foodquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.internal.$Gson$Preconditions;

public class QuizActivityAvoids extends AppCompatActivity {
    /* checkboxes for preferences pg1 */
    CheckBox eggs;
    CheckBox fish;
    CheckBox shellfish;
    CheckBox treeNuts;
    CheckBox peanuts;
    CheckBox wheat;
    CheckBox soybeans;

    /* next button */
    Button btnNext;

    /* return to section1 button */
    Button returnToSec2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_pg4);

        /* checkboxes for preferences pg1 */
        CheckBox eggs = findViewById(R.id.avoidEggs);
        CheckBox fish = findViewById(R.id.avoidFish);
        CheckBox shellfish = findViewById(R.id.avoidShellfish);
        CheckBox treeNuts = findViewById(R.id.avoidTreeNuts);
        CheckBox peanuts = findViewById(R.id.avoidPeanuts);
        CheckBox wheat = findViewById(R.id.avoidWheat);
        CheckBox soybeans = findViewById(R.id.avoidSoybeans);


        /* next button */
        Button btnNext = findViewById(R.id.nextButton);

        /* return to section1 button */
        Button returnToSec2 = findViewById(R.id.returnToPreferenceSection);



        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizActivityAvoids.this, QuizPrepTimeDisplay.class);
                startActivity(intent);
                finish();
            }
        });


        returnToSec2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizActivityAvoids.this, QuizActivityPreferences.class);
                startActivity(intent);
                finish();
            }
        });


    }


}
