package com.example.foodquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.internal.$Gson$Preconditions;

import java.util.HashMap;
import java.util.Map;

public class QuizActivityAvoids extends AppCompatActivity {
    /* checkboxes for preferences pg1 */
    CheckBox eggs;
    CheckBox fish;
    CheckBox shellfish;
    CheckBox treeNuts;
    CheckBox peanuts;
    CheckBox wheat;
    CheckBox soybeans;

    /* next button */
    Button btnNext;

    /* return to section1 button */
    Button returnToSec2;

    QuizResponseActivity responses;

    FirebaseUser user;
    FirebaseAuth mAuth;

    Boolean boolEggs;
    Boolean boolFish;
    Boolean boolShellfish;
    Boolean boolTreeNuts;
    Boolean boolPeanuts;
    Boolean boolWheat;
    Boolean boolSoybeans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_pg5);

        /* checkboxes for preferences pg1 */
        final CheckBox eggs = findViewById(R.id.avoidEggs);
        final CheckBox fish = findViewById(R.id.avoidFish);
        final CheckBox shellfish = findViewById(R.id.avoidShellfish);
        final CheckBox treeNuts = findViewById(R.id.avoidTreeNuts);
        final CheckBox peanuts = findViewById(R.id.avoidPeanuts);
        final CheckBox wheat = findViewById(R.id.avoidWheat);
        final CheckBox soybeans = findViewById(R.id.avoidSoybeans);


        /* next button */
        Button btnNext = findViewById(R.id.nextButton);

        /* return to section1 button */
        Button returnToSec2 = findViewById(R.id.returnToPreferenceSection);

        responses = new QuizResponseActivity();


        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                        //adding responses to database
                        Boolean r1 = Boolean.parseBoolean(eggs.getText().toString());
                        Boolean r2 = Boolean.parseBoolean(fish.getText().toString());
                        Boolean r3 = Boolean.parseBoolean(shellfish.getText().toString());
                        Boolean r4 = Boolean.parseBoolean(treeNuts.getText().toString());
                        Boolean r5 = Boolean.parseBoolean(peanuts.getText().toString());
                        Boolean r6 = Boolean.parseBoolean(wheat.getText().toString());
                        Boolean r7 = Boolean.parseBoolean(soybeans.getText().toString());


                        if (eggs.isChecked()) {
                            responses.setAvoidsResponse(r1);
                            boolEggs = true;

                            Map<String, Object> newInfo = new HashMap<>();
                            newInfo.put("Avoids Eggs", boolEggs);

                            FirebaseFirestore.getInstance().collection("users").document(user.getUid()).update(newInfo);
                        }if(fish.isChecked()){
                            responses.setAvoidsResponse(r2);
                            boolFish = true;

                            Map<String, Object> newInfo = new HashMap<>();
                            newInfo.put("Avoids Fish", boolFish);

                            FirebaseFirestore.getInstance().collection("users").document(user.getUid()).update(newInfo);
                        }if(shellfish.isChecked()){
                            responses.setAvoidsResponse(r3);
                            boolShellfish = true;

                            Map<String, Object> newInfo = new HashMap<>();
                            newInfo.put("Avoids Shellfish", boolShellfish);

                            FirebaseFirestore.getInstance().collection("users").document(user.getUid()).update(newInfo);
                        }if(treeNuts.isChecked()){
                            responses.setAvoidsResponse(r4);
                            boolTreeNuts = true;

                            Map<String, Object> newInfo = new HashMap<>();
                            newInfo.put("Avoids Treenuts", boolTreeNuts);

                            FirebaseFirestore.getInstance().collection("users").document(user.getUid()).update(newInfo);
                        }if(peanuts.isChecked()){
                            responses.setAvoidsResponse(r5);
                            boolPeanuts = true;

                            Map<String, Object> newInfo = new HashMap<>();
                            newInfo.put("Avoids Peanuts", boolPeanuts);

                            FirebaseFirestore.getInstance().collection("users").document(user.getUid()).update(newInfo);
                        }if(wheat.isChecked()){
                            responses.setAvoidsResponse(r6);
                            boolWheat = true;

                            Map<String, Object> newInfo = new HashMap<>();
                            newInfo.put("Avoids Wheat", boolWheat);

                            FirebaseFirestore.getInstance().collection("users").document(user.getUid()).update(newInfo);
                        }if(soybeans.isChecked()){
                            responses.setAvoidsResponse(r7);
                            boolSoybeans = true;

                            Map<String, Object> newInfo = new HashMap<>();
                            newInfo.put("Avoids Soybeans", boolSoybeans);

                            FirebaseFirestore.getInstance().collection("users").document(user.getUid()).update(newInfo);
                        }

                        Intent intent = new Intent(QuizActivityAvoids.this, QuizPrepTimeDisplay.class);
                        startActivity(intent);
                        finish();
            }
        });


        returnToSec2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizActivityAvoids.this, QuizActivityPreferences.class);
                startActivity(intent);
                finish();
            }
        });


    }


}
