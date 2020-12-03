package com.example.foodquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class QuizPreferencesDisplay extends AppCompatActivity {

    Button goToSection2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizpreferencesdisplay);

        goToSection2 = findViewById(R.id.tapHereToStart2);

        goToSection2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizPreferencesDisplay.this, QuizActivityPreferences.class);
                startActivity(intent);
                finish();
            }
        });

    }

}
