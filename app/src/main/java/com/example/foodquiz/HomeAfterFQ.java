package com.example.foodquiz;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class HomeAfterFQ extends AppCompatActivity {
    private static final String TAG = "Meals";
    private FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    Button seeRecipes;
    Button seemeals;

    QuizResponseActivity response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();


        setContentView(R.layout.activity_home_after_foodquiz);

        final TextView bfast = (TextView) findViewById(R.id.breakfastMeal);
        final TextView lunch1 = (TextView)findViewById(R.id.lunchMeal);
        final TextView dinner1 = (TextView)findViewById(R.id.dinnerMeal);

        seeRecipes = findViewById(R.id.btnSeeMoreRecipes);
        seemeals = findViewById(R.id.btnSeeFullMealPlan);
        response = new QuizResponseActivity();

        final TextView[] recipeFields1 = new TextView[3];

        recipeFields1[0] = findViewById(R.id.recipeBox1);
        recipeFields1[1] = findViewById(R.id.recipeBox2);
        recipeFields1[2] = findViewById(R.id.recipeBox3);

        seeRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeAfterFQ.this, Recipes.class);
                startActivity(intent);
                finish();
            }
        });

        seemeals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeAfterFQ.this, mealPlan.class);
                startActivity(intent);
                finish();
            }
        });


        fStore.collection("meals")
                .whereEqualTo("Meal Type", "Breakfast")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            String[] breakfast = new String[7];
                            int i = 0;
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                Log.d(TAG, i + document.getId() + " => " + document.getData());

                                if(i < 8){
                                    breakfast[i] = document.getData().toString();
                                }
                                i++;
                            }

                            Log.d("tag", breakfast[0]);
                            bfast.setText(breakfast[0]);

                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

        fStore.collection("meals")
                .whereEqualTo("Meal Type", "Lunch")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            String[] lunch = new String[7];
                            int i = 0;
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                Log.d(TAG, i + document.getId() + " => " + document.getData());

                                if(i < 8){
                                    lunch[i] = document.getData().toString();
                                }
                                i++;
                            }

                            Log.d("tag", lunch[0]);
                            lunch1.setText(lunch[0]);

                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

        fStore.collection("meals")
                .whereEqualTo("Meal Type", "Dinner")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            String[] dinner = new String[7];
                            int i = 0;
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                Log.d(TAG, i + document.getId() + " => " + document.getData());

                                if(i < 8){
                                    dinner[i] = document.getData().toString();
                                }
                                i++;
                            }

                            Log.d("tag", dinner[0]);
                            dinner1.setText(dinner[0]);

                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });



        fStore.collection("recipes")
                .whereEqualTo("Diet", "No Restrictions")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            int i = 0;
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                Log.d(TAG, i + document.getId() + " => " + document.getData());

                                if(i < 3){
                                    recipeFields1[i].setText(document.getData().toString());
                                }

                                i++;
                            }

                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.scmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuhome:
                startActivity(new Intent(this,HomeAfterFQ.class));
                return true;
            case R.id.menumealplan:
                startActivity(new Intent(this, mealPlan.class));
                return true;
            case R.id.menuprofileicon:
                Intent i = new Intent(this, UserProfile.class);
                startActivity(i);
                return true;
            case R.id.menurecipe:
                startActivity(new Intent(this,Recipes.class));
                return true;
            case R.id.menulogout:
                mAuth.signOut();
                Intent intent = new Intent(HomeAfterFQ.this,LoginActivity.class);
                startActivity(intent);
                Toast.makeText(this,"You have been successfully logged out.",Toast.LENGTH_LONG).show();
                finish();

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
