package com.example.foodquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodquiz.QuizActivityDiet;
import com.example.foodquiz.QuizWelcomeDisplay;
import com.example.foodquiz.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class QuizActivityPreferences extends AppCompatActivity {

    /* checkboxes for preferences pg1 */
    RadioGroup radioGroup;
    RadioButton low_sodium;
    RadioButton low_carbs;
    RadioButton low_sugar;
    RadioButton high_protein;
    RadioButton no_preference;

    /* next button */
    Button btnNext;

    /* return to section1 button */
    Button returnToSec1;

    QuizResponseActivity responses;

    FirebaseUser user;
    FirebaseAuth mAuth;

    Boolean lowSodium;
    Boolean lowCarb;
    Boolean lowSugar;
    Boolean highProtein;
    Boolean noPreference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_pg4);

        /* checkboxes for preferences pg1 */
        final CheckBox low_sodium = findViewById(R.id.lowSodium);
        final CheckBox low_carbs = findViewById(R.id.lowCarb);
        final CheckBox low_sugar = findViewById(R.id.lowSugar);
        final CheckBox high_protein = findViewById(R.id.highProtein);
        final CheckBox no_preference = findViewById(R.id.noPreference);

        /* next button */
        Button btnNext = findViewById(R.id.nextButton);

        /* return to section1 button */
        Button returnToSec1 = findViewById(R.id.returnToDietSection);

        responses = new QuizResponseActivity();


        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        btnNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                    //adding responses to database
                    Boolean r1 = Boolean.parseBoolean(low_sodium.getText().toString());
                    Boolean r2 = Boolean.parseBoolean(low_carbs.getText().toString());
                    Boolean r3 = Boolean.parseBoolean(low_sugar.getText().toString());
                    Boolean r4 = Boolean.parseBoolean(high_protein.getText().toString());
                    Boolean r5 = Boolean.parseBoolean(no_preference.getText().toString());

                    if (low_sodium.isChecked()) {
                        responses.setPreferenceResponse(r1);
                        lowSodium = true;

                        Map<String, Object> newInfo = new HashMap<>();
                        newInfo.put("Low Sodium", lowSodium);
                        FirebaseFirestore.getInstance().collection("users").document(user.getUid()).update(newInfo);
                    }if(low_carbs.isChecked()){
                        responses.setPreferenceResponse(r2);
                        lowCarb = true;

                        Map<String, Object> newInfo1 = new HashMap<>();
                        newInfo1.put("Low Carb", lowCarb);
                        FirebaseFirestore.getInstance().collection("users").document(user.getUid()).update(newInfo1);
                    }if(low_sugar.isChecked()) {
                    responses.setPreferenceResponse(r3);
                    lowSugar = true;

                    Map<String, Object> newInfo2 = new HashMap<>();
                    newInfo2.put("Low Sugar", lowSugar);
                    FirebaseFirestore.getInstance().collection("users").document(user.getUid()).update(newInfo2);
                     }if(high_protein.isChecked()){
                        responses.setPreferenceResponse(r4);
                        highProtein = true;

                        Map<String, Object> newInfo3 = new HashMap<>();
                        newInfo3.put("High Protein", highProtein);
                        FirebaseFirestore.getInstance().collection("users").document(user.getUid()).update(newInfo3);
                    } if(no_preference.isChecked()){
                        responses.setPreferenceResponse(r5);
                        noPreference = true;

                        Map<String, Object> newInfo4 = new HashMap<>();
                        newInfo4.put("No Preferences", noPreference);
                        FirebaseFirestore.getInstance().collection("users").document(user.getUid()).update(newInfo4);
                    }

                    Intent intent = new Intent(QuizActivityPreferences.this, QuizAvoidsDisplay.class);
                    startActivity(intent);
                    finish();

                }
    });

        returnToSec1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizActivityPreferences.this, QuizActivityDiet.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
