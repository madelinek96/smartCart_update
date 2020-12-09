package com.example.foodquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class QuizActivityDiet2 extends AppCompatActivity {

    /* radio buttons on pg 2 for diary*/
    RadioGroup radioGroup;
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

    QuizResponseActivity responses;

    FirebaseUser user;
    FirebaseAuth mAuth;

    String userDiet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_quiz_pg2);
        //mAuth = FirebaseAuth.getInstance();

        /* radio buttons on pg 2 for diary*/
        final RadioGroup radioGroup = findViewById(R.id.radioGroup2);
        final RadioButton onetoTwo2 = findViewById(R.id.oneToTwoTimes2);
        final RadioButton twotoThree2 = findViewById(R.id.twoToThreeTimes2);
        final RadioButton morethanThree2 = findViewById(R.id.moreThanThreeTimes2);
        final RadioButton neverVegan = findViewById(R.id.neverDairyVegan);
        final RadioButton neverLactoseInt = findViewById(R.id.neverDairyLactoseInt);
        final RadioButton neverDontLike2 = findViewById(R.id.neverDairyDontLike);

        /* next button */
        Button btnNext2 = findViewById(R.id.nextButton);

        /* back button */
        Button btnBack2 = findViewById(R.id.backButton2);

        responses = new QuizResponseActivity();


        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        btnNext2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(radioGroup.getCheckedRadioButtonId() == -1){
                    //user made no selections
                    Toast.makeText(getApplicationContext(),"Please make a selection.",Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    //adding responses to database
                    String r1 = onetoTwo2.getText().toString();
                    String r2 = twotoThree2.getText().toString();
                    String r3 = morethanThree2.getText().toString();
                    String r4 = neverVegan.getText().toString();
                    String r5 = neverLactoseInt.getText().toString();
                    String r6 = neverDontLike2.getText().toString();

                    if(onetoTwo2.isChecked()){
                        responses.setDietResponse2(r1);
                        userDiet = "No Restrictions";
                    }else if(twotoThree2.isChecked()){
                        responses.setDietResponse2(r2);
                        userDiet = "No Restrictions";
                    }else if(morethanThree2.isChecked()){
                        responses.setDietResponse2(r3);
                        userDiet = "No Restrictions";
                    }else if(neverVegan.isChecked()){
                        responses.setDietResponse2(r4);
                        userDiet = "Vegan";
                    }else if(neverLactoseInt.isChecked()) {
                        responses.setDietResponse2(r5);
                        userDiet = "No Dairy";
                    }else if(neverDontLike2.isChecked()){
                        responses.setDietResponse2(r6);
                        userDiet = "No Diary";
                    }


                    Map<String, Object> newInfo = new HashMap<>();
                    newInfo.put("Dairy Response", userDiet);

                    FirebaseFirestore.getInstance().collection("users").document(user.getUid()).update(newInfo);

                    Intent intent = new Intent(QuizActivityDiet2.this, QuizActivityDiet3.class);
                    startActivity(intent);
                    finish();

                }
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
