package com.example.foodquiz;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.LinkMovementMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;


public class LoginActivity extends AppCompatActivity {
    private Button createOneBtn;
    private TextView loginBtn;
    private TextView pword;
    private TextView email;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private CheckBox showPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.login_progressBar);
        createOneBtn = findViewById(R.id.createOne);
        loginBtn = findViewById(R.id.btnLogin);
        email = findViewById(R.id.email_address);
        pword = findViewById(R.id.password);


        //logging into the app
        loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String loginEmail = email.getText().toString();
                String loginpass = pword.getText().toString();

                if(!TextUtils.isEmpty(loginEmail) || !TextUtils.isEmpty(loginpass)){
                    progressBar.setVisibility(View.VISIBLE);
                    mAuth.signInWithEmailAndPassword(loginEmail, loginpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                sendtoMain();
                            }else{
                                String error = task.getException().getMessage();
                                Toast.makeText(getApplicationContext(), "Error :" + error, Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.INVISIBLE);
                            }
                        }
                    });
                }else if (TextUtils.isEmpty(loginEmail)) {
                    email.setError("Email is Required");
                    return;
                }
                else if(TextUtils.isEmpty(loginpass)){
                        email.setError("Password is Required");
                        return;
                }
            }
        });

        /*SHOW/HIDE PASSWORD*/
        showPassword = findViewById(R.id.showPassword);
        showPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    pword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    pword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        createOneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.class);
                startActivity(intent);
            }
        });

    }

protected void onStart(){
        super.onStart();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
}
private void sendtoMain(){
    Intent intent = new Intent(LoginActivity.this, HomeAfterFQ.class);
    startActivity(intent);
    finish();
}




}
