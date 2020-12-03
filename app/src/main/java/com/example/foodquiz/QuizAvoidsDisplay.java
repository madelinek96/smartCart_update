package com.example.foodquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class QuizAvoidsDisplay extends AppCompatActivity {

    Button tapToBegin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizavoidsdisplay);

        tapToBegin = findViewById(R.id.tapHereToStart3);

        tapToBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizAvoidsDisplay.this, QuizActivityPreferences.class);
                startActivity(intent);
                finish();
            }
        });

    }


}
