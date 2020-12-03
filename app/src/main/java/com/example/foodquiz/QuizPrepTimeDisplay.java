package com.example.foodquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class QuizPrepTimeDisplay extends AppCompatActivity {

    Button tapToBegin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizpreptimedisplay);

        tapToBegin = findViewById(R.id.tapHereToStart4);

        tapToBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizPrepTimeDisplay.this, QuizActivityPrepTime.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
