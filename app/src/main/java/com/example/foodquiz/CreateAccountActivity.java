package com.example.foodquiz;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CreateAccountActivity extends AppCompatActivity{
    private static final String TAG = "UserProfile";
    private CheckBox showPassword;
    private Button createAcc;
    private TextView loginBtn;
    private TextView pword;
    private TextView email;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private TextView fName;
    private TextView confirm_pass;
    FirebaseFirestore fStore;
    String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createaccount);


        mAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.login_progressBar);

        createAcc = findViewById(R.id.btnCreateAccount);
        fName = findViewById(R.id.fullName);
        loginBtn = findViewById(R.id.btnLogin);
        email = findViewById(R.id.email_address);
        pword = findViewById(R.id.password);
        confirm_pass = findViewById(R.id.confirmPassword);
        progressBar = findViewById(R.id.register_progressbar);


        fStore = FirebaseFirestore.getInstance();

        if(mAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), QuizWelcomeDisplay.class));
            finish();
        }


        /*SHOW/HIDE PASSWORD*/
        showPassword = findViewById(R.id.showPassword);
        showPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    pword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    confirm_pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    pword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    confirm_pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String reg_fullname = fName.getText().toString();
                final String reg_email = email.getText().toString();
                String reg_pass = pword.getText().toString();
                String reg_confirmpass = confirm_pass.getText().toString();


                if (!TextUtils.isEmpty(reg_email) || !TextUtils.isEmpty(reg_pass)) {

                    if (reg_pass.equals(reg_confirmpass)) {

                        progressBar.setVisibility(View.VISIBLE);

                        mAuth.createUserWithEmailAndPassword(reg_email, reg_pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    userID = mAuth.getCurrentUser().getUid();
                                    DocumentReference documentReference = fStore.collection("users").document(userID);
                                    Map<String,Object> user = new HashMap<>();
                                    user.put("fullname",reg_fullname);
                                    user.put("email",reg_email);
                                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Log.d(TAG, "OnSuccess: user profile is created for " + userID);
                                        }
                                    });
                                    sendtoMain();
                                } else {
                                    String error = task.getException().getMessage();
                                    Toast.makeText(getApplicationContext(), "Error :" + error, Toast.LENGTH_LONG).show();
                                }

                                progressBar.setVisibility(View.INVISIBLE);
                                Intent intent = new Intent(CreateAccountActivity.this, QuizWelcomeDisplay.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                    } else {
                        Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_LONG).show();
                    }
                }else if(TextUtils.isEmpty(reg_email)){
                    email.setError("Email is Required");
                    return;
                }
                else if(TextUtils.isEmpty(reg_fullname)){
                    email.setError("Full Name is Required");
                    return;
                }
                else if(TextUtils.isEmpty(reg_pass)){
                    pword.setError("Password is Required");
                    return;
                }
                else if(TextUtils.isEmpty(reg_confirmpass)){
                    confirm_pass.setError("Confirm Password is Required");
                    return;
                }
                else if(reg_pass.length() < 6){
                    pword.setError("Password must be longer than 6 characters");
                    return;
                }
            }
        });

    }


    protected void onStart(){
        super.onStart();

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if(currentUser != null){
            sendtoMain();
        }
    }
    private void sendtoMain(){
            Toast.makeText(CreateAccountActivity.this,"User Created.",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(CreateAccountActivity.this, QuizWelcomeDisplay.class);
            startActivity(intent);
            finish();
    }

}
