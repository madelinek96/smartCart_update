package com.example.foodquiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

public class QuizActivityDiet extends AppCompatActivity{

    private static final String TAG = "QuizResponses";
    /* radio buttons on pg 1 for red meat*/
    RadioGroup radioGroup;
    RadioButton onetoTwo;
    RadioButton twotoThree;
    RadioButton morethanThree;
    RadioButton neverVeganVegPesc;
    RadioButton neverDontLike;

    /* next button */
    Button btnNext;

    /* back button */
    Button btnBack;

    /*Database referencing*/
    FirebaseDatabase database;
    DatabaseReference reference;

    QuizResponseActivity responses;


    FirebaseFirestore fStore;
    FirebaseAuth mAuth;
    FirebaseUser user;
    String userID;

    CollectionReference userReference;



    String userDiet;

    int i = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_quiz_pg1);
        //mAuth = FirebaseAuth.getInstance();

        /* radio buttons on pg 1 for red meat*/
        final RadioGroup radioGroup = findViewById(R.id.radioGroup);
        final RadioButton onetoTwo = findViewById(R.id.oneToTwoTimes);
        final RadioButton twotoThree = findViewById(R.id.twoToThreeTimes);
        final RadioButton morethanThree = findViewById(R.id.moreThanThreeTimes);
        final RadioButton neverVeganVegPesc = findViewById(R.id.neverMeatImVegan);
        final RadioButton neverDontLike = findViewById(R.id.neverMeatDontLike);

        responses = new QuizResponseActivity();


        /* instantiating firebase
        database = FirebaseDatabase.getInstance();
        reference = database.getInstance().getReference().child("User");*/

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userReference = fStore.collection("users");



        user = mAuth.getCurrentUser();



        /* next button */
        final Button btnNext = findViewById(R.id.nextButton);

        /* back button */
        Button btnBack = findViewById(R.id.backButton);


        btnNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(radioGroup.getCheckedRadioButtonId() == -1){
                    //user made no selections
                    Toast.makeText(getApplicationContext(),"Please make a selection.",Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    //adding responses to database
                    String r1 = onetoTwo.getText().toString();
                    String r2 = twotoThree.getText().toString();
                    String r3 = morethanThree.getText().toString();
                    String r4 = neverVeganVegPesc.getText().toString();
                    String r5 = neverDontLike.getText().toString();

                    if(onetoTwo.isChecked()){
                        responses.setDietResponse1(r1);
                        userDiet = "No Restrictions";
                    }else if(twotoThree.isChecked()){
                        responses.setDietResponse1(r2);
                        userDiet = "No Restrictions";
                    }else if(morethanThree.isChecked()){
                        responses.setDietResponse1(r3);
                        userDiet = "No Restrictions";
                    }else if(neverVeganVegPesc.isChecked()){
                        responses.setDietResponse1(r4);
                        userDiet = "Vegan";
                    }else if(neverDontLike.isChecked()) {
                        responses.setDietResponse1(r5);
                        userDiet = "Vegan";
                    }


                    Map<String, Object> newInfo = new HashMap<>();
                    newInfo.put("Red Meat Response", userDiet);

                    FirebaseFirestore.getInstance().collection("users").document(user.getUid()).update(newInfo);

                    Intent intent = new Intent(QuizActivityDiet.this, QuizActivityDiet2.class);
                    startActivity(intent);
                    finish();

                    }
                }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(QuizActivityDiet.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });



    }
    private void sendToQuestion2() {
        Intent intent = new Intent(QuizActivityDiet.this, QuizActivityDiet2.class);
        startActivity(intent);
        finish();
    }


}
