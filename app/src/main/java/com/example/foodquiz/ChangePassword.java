package com.example.foodquiz;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePassword extends AppCompatActivity {
    Button changePass2;
    EditText currentPass;
    EditText newPass;
    EditText confirmPass;
    FirebaseAuth mAuth;
    FirebaseUser user;
    private static final String TAG = "User";


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatepassword);

        changePass2 = findViewById(R.id.btnChangePass2);
        currentPass = findViewById(R.id.enterCurrentPassword);
        newPass = findViewById(R.id.enterNewPassword);
        confirmPass = findViewById(R.id.enterConfirmPassword);

        mAuth = FirebaseAuth.getInstance();

        changePass2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePassword();
            }
        });

    }

    private void changePassword() {
        if(!currentPass.getText().toString().isEmpty() &&
            !newPass.getText().toString().isEmpty() &&
                !confirmPass.getText().toString().isEmpty()){

                    if(newPass.getText().toString().equals(confirmPass.getText().toString())){
                        //reauthenticate user before changing
                        user = mAuth.getInstance().getCurrentUser();
                        if(user != null && user.getEmail() != null){
                            AuthCredential credential = EmailAuthProvider.getCredential(user.getEmail(),currentPass.getText().toString());

                            user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getApplicationContext(),"User re-authenticated.",Toast.LENGTH_SHORT).show();
                                        //Log.d(TAG, "User re-authenticated");

                                        user.updatePassword(newPass.getText().toString())
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            Toast.makeText(getApplicationContext(),"User password updated.",Toast.LENGTH_SHORT).show();
                                                            //Log.d(TAG, "User password updated.");

                                                            mAuth.signOut();
                                                            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                                                            startActivity(intent);
                                                            finish();
                                                        }
                                                    }
                                                });


                                    }else{
                                        Toast.makeText(getApplicationContext(),"Current password is incorrect.",Toast.LENGTH_SHORT).show();
                                        //Log.d(TAG,"Re-authentication failed.");
                                    }
                                }
                            });

                        }else{
                            startActivity(new Intent(this, LoginActivity.class));
                            finish();
                        }


                    }else{
                        Toast.makeText(this,"Password does not match.",Toast.LENGTH_SHORT).show();
                    }

        }else{
            Toast.makeText(this,"Please enter all fields.",Toast.LENGTH_SHORT).show();
        }
    }


}
