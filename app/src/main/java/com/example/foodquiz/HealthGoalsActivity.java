package com.example.foodquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class HealthGoalsActivity extends AppCompatActivity {

    EditText currentWeight;
    EditText goalWeight;
    EditText caloricGoal;

    QuizResponseActivity responses;


    FirebaseFirestore fStore;
    FirebaseAuth mAuth;
    FirebaseUser user;
    String userID;

    CollectionReference userReference;



    Integer userDiet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_quiz_pg7);

        final EditText currentWeight = findViewById(R.id.textCurrentWeight);
        final EditText goalWeight = findViewById(R.id.textGoalWeight);
        final EditText caloricGoal = findViewById(R.id.textCaloricGoal);

        responses = new QuizResponseActivity();

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userReference = fStore.collection("users");



        user = mAuth.getCurrentUser();



        /* next button */
        final Button btnNext = findViewById(R.id.nextButton);

        /* back button */
        Button btnBack = findViewById(R.id.returnToPrepSection);


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer r1 = Integer.parseInt(currentWeight.getText().toString());
                Integer r2 = Integer.parseInt(goalWeight.getText().toString());
                Integer r3 = Integer.parseInt(caloricGoal.getText().toString());

                responses.setHealthgoalResponse(r1);
                userDiet = responses.getHealthgoalResponse();
                Map<String, Object> newInfo = new HashMap<>();
                newInfo.put("Current Weight:", userDiet);

                FirebaseFirestore.getInstance().collection("users").document(user.getUid()).update(newInfo);
                //
                responses.setHealthgoalResponse(r2);
                userDiet = responses.getHealthgoalResponse();
                Map<String, Object> newInfo2 = new HashMap<>();
                newInfo2.put("Goal Weight:", userDiet);

                FirebaseFirestore.getInstance().collection("users").document(user.getUid()).update(newInfo2);
                //
                responses.setHealthgoalResponse(r3);
                userDiet = responses.getHealthgoalResponse();
                Map<String, Object> newInfo3 = new HashMap<>();
                newInfo3.put("Caloric Goals", userDiet);

                FirebaseFirestore.getInstance().collection("users").document(user.getUid()).update(newInfo3);

                Intent intent = new Intent(HealthGoalsActivity.this, QuizBudgetDisplay.class);
                startActivity(intent);
                finish();
            }

        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(HealthGoalsActivity.this,QuizActivityPrepTime.class);
                startActivity(intent);
                finish();
            }
        });


    }


}
