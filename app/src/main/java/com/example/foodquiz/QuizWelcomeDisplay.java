package com.example.foodquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class QuizWelcomeDisplay extends AppCompatActivity {

    Button display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizwelcomedisplay);

        display = findViewById(R.id.tapHereToStart);

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizWelcomeDisplay.this, QuizActivityDiet.class);
                startActivity(intent);
                finish();
            }
        });

    }

}

