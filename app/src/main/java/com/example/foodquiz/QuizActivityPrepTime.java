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

public class QuizActivityPrepTime extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioGroup radioGroup1;
    RadioGroup radioGroup2;

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

    QuizResponseActivity responses;

    FirebaseUser user;
    FirebaseAuth mAuth;

    String breakfastPrep;
    String lunchPrep;
    String dinnerPrep;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_pg6);

        final RadioGroup radioGroup = findViewById(R.id.breakfastRadioGroup);
        final RadioGroup radioGroup1 = findViewById(R.id.lunchRadioGroup);
        final RadioGroup radioGroup2= findViewById(R.id.dinnerRadioGroup);


        final RadioButton zerototen = findViewById(R.id.zeroToTen);
        final RadioButton tentothirty = findViewById(R.id.tenToThirty);
        final RadioButton thirtytofifty = findViewById(R.id.thirtyToFifty);

        final RadioButton zerototen2 = findViewById(R.id.zeroToTen2);
        final RadioButton tentothirty2 = findViewById(R.id.tenToThirty2);
        final RadioButton thirtytofifty2 = findViewById(R.id.thirtyToFifty2);

        final RadioButton zerototen3 = findViewById(R.id.zeroToTen3);
        final RadioButton tentothirty3 = findViewById(R.id.tenToThirty3);
        final RadioButton thirtytofifty3 = findViewById(R.id.thirtyToFifty3);

        Button returntoSec2 = findViewById(R.id.returntoAvoidsSection);
        Button btnNext = findViewById(R.id.nextButton);

        responses = new QuizResponseActivity();


        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  if(radioGroup.getCheckedRadioButtonId() == -1 || radioGroup1.getCheckedRadioButtonId() == -1 || radioGroup2.getCheckedRadioButtonId() == -1){
                    //user made no selections
                    Toast.makeText(getApplicationContext(),"Please select an option for each.",Toast.LENGTH_SHORT).show();
                    return;
                }else{*/
                    //adding responses to database
                    //breakfast
                    String r1 = zerototen.getText().toString();
                    String r2 = tentothirty.getText().toString();
                    String r3 = thirtytofifty.getText().toString();

                    if(radioGroup.getCheckedRadioButtonId() == -1){
                        //user made no breakfast selection
                        Toast.makeText(getApplicationContext(),"Please select an option for breakfast.",Toast.LENGTH_SHORT).show();
                        return;
                    }else if(zerototen.isChecked()){
                        responses.setPrepResponse(r1);
                        breakfastPrep = "0-10";
                        Map<String, Object> newInfo = new HashMap<>();
                        newInfo.put("Breakfast Prep Time", breakfastPrep);
                        FirebaseFirestore.getInstance().collection("users").document(user.getUid()).update(newInfo);
                    }else if(tentothirty.isChecked()){
                        responses.setPrepResponse(r2);
                        breakfastPrep = "10-30";
                        Map<String, Object> newInfo = new HashMap<>();
                        newInfo.put("Breakfast Prep Time", breakfastPrep);
                        FirebaseFirestore.getInstance().collection("users").document(user.getUid()).update(newInfo);
                    }else if(thirtytofifty.isChecked()){
                        responses.setPrepResponse(r3);
                        breakfastPrep = "30-50+";
                        Map<String, Object> newInfo = new HashMap<>();
                        newInfo.put("Breakfast Prep Time", breakfastPrep);
                        FirebaseFirestore.getInstance().collection("users").document(user.getUid()).update(newInfo);
                    }

                    //lunch
                    String r4 = zerototen2.getText().toString();
                    String r5 = tentothirty2.getText().toString();
                    String r6 = thirtytofifty2.getText().toString();

                if(radioGroup1.getCheckedRadioButtonId() == -1){
                    //user made no breakfast selection
                    Toast.makeText(getApplicationContext(),"Please select an option for lunch.",Toast.LENGTH_SHORT).show();
                    return;
                }else if(zerototen2.isChecked()){
                    responses.setPrepResponse(r4);
                    lunchPrep = "0-10";
                    Map<String, Object> newInfo = new HashMap<>();
                    newInfo.put("Lunch Prep Time", lunchPrep);
                    FirebaseFirestore.getInstance().collection("users").document(user.getUid()).update(newInfo);
                }else if(tentothirty2.isChecked()){
                    responses.setPrepResponse(r5);
                    lunchPrep = "10-30";
                    Map<String, Object> newInfo = new HashMap<>();
                    newInfo.put("Lunch Prep Time", lunchPrep);
                    FirebaseFirestore.getInstance().collection("users").document(user.getUid()).update(newInfo);
                }else if(thirtytofifty2.isChecked()){
                    responses.setPrepResponse(r6);
                    lunchPrep = "30-50+";
                    Map<String, Object> newInfo = new HashMap<>();
                    newInfo.put("Lunch Prep Time", lunchPrep);
                    FirebaseFirestore.getInstance().collection("users").document(user.getUid()).update(newInfo);
                }

                    //dinner
                    String r7 = zerototen3.getText().toString();
                    String r8 = tentothirty3.getText().toString();
                    String r9 = thirtytofifty3.getText().toString();

                if(radioGroup2.getCheckedRadioButtonId() == -1){
                    //user made no dinner selection
                    Toast.makeText(getApplicationContext(),"Please select an option for dinner.",Toast.LENGTH_SHORT).show();
                    return;
                }else if(zerototen3.isChecked()){
                    responses.setPrepResponse(r7);
                    dinnerPrep = "0-10";
                    Map<String, Object> newInfo = new HashMap<>();
                    newInfo.put("Dinner Prep Time", dinnerPrep);
                    FirebaseFirestore.getInstance().collection("users").document(user.getUid()).update(newInfo);
                }else if(tentothirty3.isChecked()){
                    responses.setPrepResponse(r8);
                    dinnerPrep = "10-30";
                    Map<String, Object> newInfo = new HashMap<>();
                    newInfo.put("Dinner Prep Time", dinnerPrep);
                    FirebaseFirestore.getInstance().collection("users").document(user.getUid()).update(newInfo);
                }else if(thirtytofifty3.isChecked()){
                    responses.setPrepResponse(r9);
                    dinnerPrep = "30-50+";
                    Map<String, Object> newInfo = new HashMap<>();
                    newInfo.put("Dinner Prep Time", dinnerPrep);
                    FirebaseFirestore.getInstance().collection("users").document(user.getUid()).update(newInfo);
                }


                Intent intent = new Intent(QuizActivityPrepTime.this, QuizHealthGoalsDisplay.class);
                startActivity(intent);
                finish();
            }
        });


        returntoSec2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizActivityPrepTime.this, QuizActivityAvoids.class);
                startActivity(intent);
                finish();
            }
        });





    }
}