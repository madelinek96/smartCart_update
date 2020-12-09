package com.example.foodquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class QuizAvoidsDisplay extends AppCompatActivity {

    Button tapToBegin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizavoidsdisplay);

        tapToBegin = findViewById(R.id.tapHereToStart3);

        tapToBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizAvoidsDisplay.this, QuizActivityAvoids.class);
                startActivity(intent);
                finish();
            }
        });

    }

    //showing the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.foodquizmenu, menu);
        return true;
    }
    //menu logic
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuexitquiz:
                startActivity(new Intent(this,HomeActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


}
