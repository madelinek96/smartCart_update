package com.example.foodquiz;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PropertyPermission;

public class Recipes extends AppCompatActivity {

    FirebaseFirestore fStore;
    FirebaseAuth mAuth;
    Button showRecipes;

    //TextView recipeFields;

    QuizResponseActivity responses;


    String recipeID;

    private DocumentReference documentReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_recipes);

        //init firestore
        fStore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        recipeID = mAuth.getCurrentUser().getUid();

        //final DocumentReference documentReference = fStore.collection("recipes").document("j3Pfg52GmDgMuZIOz8cP");
        // final DocumentReference documentReference2 = fStore.collection("recipes").document("M9Yy1OSfWvgZaQ1rgCwb");
        //TextView recipeFields = findViewById(R.id.showRecipes), findViewById(R.id.showRecipes1), findViewById(R.id.showRecipes2)[];

        final TextView[] recipeFields = new TextView[23];

        recipeFields[0] = findViewById(R.id.showRecipes);
        recipeFields[1] = findViewById(R.id.showRecipes1);
        recipeFields[2] = findViewById(R.id.showRecipes16);
        recipeFields[3] = findViewById(R.id.showRecipes14);
        recipeFields[4] = findViewById(R.id.showRecipes15);
        recipeFields[5] = findViewById(R.id.showRecipes13);
        recipeFields[6] = findViewById(R.id.showRecipes4);
        recipeFields[7] = findViewById(R.id.showRecipes18);
        recipeFields[8] = findViewById(R.id.showRecipes19);
        recipeFields[9] = findViewById(R.id.showRecipes20);
        recipeFields[10] = findViewById(R.id.showRecipes21);
        recipeFields[11] = findViewById(R.id.showRecipes22);
        recipeFields[12] = findViewById(R.id.showRecipes23);
        recipeFields[13] = findViewById(R.id.showRecipes24);
        recipeFields[14] = findViewById(R.id.showRecipes25);
        recipeFields[15] = findViewById(R.id.showRecipes26);
        recipeFields[16] = findViewById(R.id.showRecipes27);
        recipeFields[17] = findViewById(R.id.showRecipes28);
        recipeFields[18] = findViewById(R.id.showRecipes29);
        recipeFields[19] = findViewById(R.id.showRecipes30);
        recipeFields[20] = findViewById(R.id.showRecipes31);
        recipeFields[21] = findViewById(R.id.showRecipes32);


        responses = new QuizResponseActivity();

        showRecipes = findViewById(R.id.showRecipesButton);

        showRecipes.setOnClickListener(new View.OnClickListener() {
            private static final String TAG = "Recipes";

            @Override
            public void onClick(View v) {

                fStore.collection("recipes")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                                if (task.isSuccessful()) {
                                    int i = 0;
                                    for (QueryDocumentSnapshot document : task.getResult()) {

                                        Log.d(TAG, i + document.getId() + " => " + document.getData());

                                        if (i < 23) {
                                            recipeFields[i].setText(document.getData().toString());
                                        }

                                        i++;
                                    }

                                } else {
                                    Log.d(TAG, "Error getting documents: ", task.getException());
                                }
                            }
                        });
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
                Intent intent = new Intent(Recipes.this,LoginActivity.class);
                startActivity(intent);
                Toast.makeText(this,"You have been successfully logged out.",Toast.LENGTH_LONG).show();
                finish();

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}









