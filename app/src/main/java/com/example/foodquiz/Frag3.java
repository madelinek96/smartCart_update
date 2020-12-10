package com.example.foodquiz;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Frag3 extends Fragment {
    private static final String TAG = "Meals";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        return inflater.inflate(R.layout.frag3_layout, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        FirebaseFirestore fStore = FirebaseFirestore.getInstance();
        final TextView bfast = (TextView) getView().findViewById(R.id.tuesdayBreakfast);
        final TextView lunch1 = (TextView) getView().findViewById(R.id.tuesdayLunch);
        final TextView dinner1 = (TextView) getView().findViewById(R.id.tuesdayDinner);



        FirebaseAuth mAuth = FirebaseAuth.getInstance();



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

                            Log.d("tag", breakfast[2]);
                            bfast.setText(breakfast[2]);

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

                            Log.d("tag", lunch[2]);
                            lunch1.setText(lunch[2]);

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

                            Log.d("tag", dinner[2]);
                            dinner1.setText(dinner[2]);

                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }


}
