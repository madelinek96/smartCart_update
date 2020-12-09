package com.example.foodquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class BudgetActivity extends AppCompatActivity {

    EditText budget;

    QuizResponseActivity responses;


    FirebaseFirestore fStore;
    FirebaseAuth mAuth;
    FirebaseUser user;
    String userID;

    CollectionReference userReference;



    Integer userDiet;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_pg8);


        budget = findViewById(R.id.textBudget);

        responses = new QuizResponseActivity();


        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userReference = fStore.collection("users");



        user = mAuth.getCurrentUser();



        /* next button */
        final Button btnNext = findViewById(R.id.nextButton);

        /* back button */
        Button btnBack = findViewById(R.id.returnToHealthGoalSection);


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Integer r1 = Integer.parseInt(budget.getText().toString());

                 responses.setBudgetResponse(r1);
                 userDiet = responses.getBudgetResponse();

                Map<String, Object> newInfo = new HashMap<>();
                newInfo.put("Weekly Budget", userDiet);

                FirebaseFirestore.getInstance().collection("users").document(user.getUid()).update(newInfo);

                Intent intent = new Intent(BudgetActivity.this, LoadingScreen.class);
                startActivity(intent);
                finish();

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(BudgetActivity.this,HealthGoalsActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }



}
