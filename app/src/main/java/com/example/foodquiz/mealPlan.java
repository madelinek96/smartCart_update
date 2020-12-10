package com.example.foodquiz;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodquiz.ui.main.SectionsPagerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class mealPlan extends AppCompatActivity {
    FirebaseFirestore fStore;
    FirebaseAuth mAuth;

    /*
    TextView sunBreakfast, sunLunch, sunDinner, monBreakfast, monLunch, monDinner, tuesBreakfast, tuesLunch, tuesDinner
            ,wedsBreakfast, wedsLunch, wedsDinner, thursBreakfast, thursLunch, thursDinner,friBreakfast, friLunch, friDinner
            ,satBreakfast, satLunch, satDinner;*/

    private static final String TAG = "Meals";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_plan);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
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
                Intent intent = new Intent(mealPlan.this,LoginActivity.class);
                startActivity(intent);
                Toast.makeText(this,"You have been successfully logged out.",Toast.LENGTH_LONG).show();
                finish();

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    }


