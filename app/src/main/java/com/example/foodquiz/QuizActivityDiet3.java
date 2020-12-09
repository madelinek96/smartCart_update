package com.example.foodquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class QuizActivityDiet3 extends AppCompatActivity {
    /* radiobuttons on pg 3 for diet*/

    RadioGroup radioGroup;
    RadioButton amVegan;
    RadioButton amVegetarian;
    RadioButton amPescatarian;
    RadioButton noRestrictions;

    /* next button */
    Button btnNext3;

    /* back button */
    Button btnBack3;

    QuizResponseActivity responses;

    FirebaseUser user;
    FirebaseAuth mAuth;

    String userDiet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_pg3);

        /* checkboxes on pg 3 for diet*/

        final RadioGroup radioGroup = findViewById(R.id.radioGroup3);
        final RadioButton amVegan = findViewById(R.id.imVegan);
        final RadioButton amVegetarian = findViewById(R.id.imVegetarian);
        final RadioButton amPescatarian = findViewById(R.id.imPescatarian);
        final RadioButton noRestrictions = findViewById(R.id.noDiet);

        /* next button */
        Button btnNext3 = findViewById(R.id.nextButton3);

        /* back button */
        Button btnBack3 = findViewById(R.id.backButton3);

        responses = new QuizResponseActivity();


        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();



        btnNext3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(radioGroup.getCheckedRadioButtonId() == -1){
                    //user made no selections
                    Toast.makeText(getApplicationContext(),"Please make a selection.",Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    //adding responses to database
                    String r1 = amVegan.getText().toString();
                    String r2 = amVegetarian.getText().toString();
                    String r3 = amPescatarian.getText().toString();
                    String r4 = noRestrictions.getText().toString();


                    if(amVegan.isChecked()){
                        responses.setDietResponse3(r1);
                        userDiet = "Vegan";
                    }else if(amVegetarian.isChecked()){
                        responses.setDietResponse3(r2);
                        userDiet = "Vegetarian";
                    }else if(amPescatarian.isChecked()){
                        responses.setDietResponse3(r3);
                        userDiet = "Pescatarian";
                    }else if(noRestrictions.isChecked()) {
                        responses.setDietResponse3(r4);
                        userDiet = "No restrictions";
                    }
                    Map<String, Object> newInfo = new HashMap<>();
                    newInfo.put("Diet Response", userDiet);

                    FirebaseFirestore.getInstance().collection("users").document(user.getUid()).update(newInfo);

                    Intent intent = new Intent(QuizActivityDiet3.this, QuizPreferencesDisplay.class);
                    startActivity(intent);
                    finish();

                }
            }
        });

        btnBack3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizActivityDiet3.this,QuizActivityDiet2.class);
                startActivity(intent);
                finish();
            }
        });


    }

}
