
package com.example.foodquiz;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.firebase.ui.auth.data.model.User;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    Button goToWelcomePage;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        goToWelcomePage = findViewById(R.id.takeFoodQuiz);

        goToWelcomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, QuizWelcomeDisplay.class);
                startActivity(intent);
                finish();
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
                startActivity(new Intent(this,HomeActivity.class));
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
                Intent intent = new Intent(HomeActivity.this,LoginActivity.class);
                startActivity(intent);
                Toast.makeText(this,"You have been successfully logged out.",Toast.LENGTH_LONG).show();
                finish();

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
