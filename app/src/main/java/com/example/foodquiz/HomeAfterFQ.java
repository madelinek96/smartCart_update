package com.example.foodquiz;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class HomeAfterFQ extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();


        setContentView(R.layout.activity_home_after_foodquiz);


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
                Toast.makeText(this, "Meal Plan selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menuprofileicon:
                Intent i = new Intent(this, UserProfile.class);
                startActivity(i);
                return true;
            case R.id.menurecipe:
                Toast.makeText(this, "Recipes selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menusubscribe:
                Toast.makeText(this, "Subscribe to Pro selected", Toast.LENGTH_SHORT).show();
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
