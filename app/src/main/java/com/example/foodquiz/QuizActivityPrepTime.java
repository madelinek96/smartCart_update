package com.example.foodquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

public class QuizActivityPrepTime extends AppCompatActivity {
    RadioButton zerototen;
    RadioButton tentothirty;
    RadioButton thirtytofifty;

    RadioButton zerototen2;
    RadioButton tentothirty2;
    RadioButton thirtytofifty2;

    RadioButton zerototen3;
    RadioButton tentothirty3;
    RadioButton thirtytofifty3;

    Button returntoSec2;
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_pg6);

        RadioButton zerototen = findViewById(R.id.zeroToTen);
        RadioButton tentothirty = findViewById(R.id.tenToThirty);
        RadioButton thirtytofifty = findViewById(R.id.thirtyToFifty);

        RadioButton zerototen2 = findViewById(R.id.zeroToTen2);
        RadioButton tentothirty2 = findViewById(R.id.tenToThirty2);
        RadioButton thirtytofifty2 = findViewById(R.id.thirtyToFifty2);

        RadioButton zerototen3 = findViewById(R.id.zeroToTen3);
        RadioButton tentothirty3 = findViewById(R.id.tenToThirty3);
        RadioButton thirtytofifty3 = findViewById(R.id.thirtyToFifty3);

        Button returntoSec2 = findViewById(R.id.returntoAvoidsSection);
        Button btnNext = findViewById(R.id.nextButton);


        returntoSec2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizActivityPrepTime.this, QuizActivityAvoids.class);
                startActivity(intent);
                finish();
            }
        });
        /*

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizActivityPrepTime.this, )
            }
        });*/


    }
}