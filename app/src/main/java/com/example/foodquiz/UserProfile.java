package com.example.foodquiz;

import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.data.model.User;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class UserProfile extends AppCompatActivity {
    TextView fullName;
    TextView email;
    EditText pPassword;
    ImageView profileImage;
    ProgressBar progressBar;
    Button editProfile;
    Button changePassword;
    Button updateFoodQuiz;
    Button deleteAcc;
    StorageReference storageReference;
    FirebaseFirestore fStore;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;
    DocumentReference documentReference;
    String userID;
    FirebaseAuth mAuth;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showprofile);

        profileImage = findViewById(R.id.userProfile);
        fullName = findViewById(R.id.displayName);
        pPassword = findViewById(R.id.password);
        email = findViewById(R.id.displayEmail);
        progressBar = findViewById(R.id.profileProgressBar);
        editProfile = findViewById(R.id.btnEditProfile);
        changePassword = findViewById(R.id.btnChangePassword);
        updateFoodQuiz = findViewById(R.id.btnUpdateFoodQuiz);
        deleteAcc = findViewById(R.id.btnDeleteAccount);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        userID = mAuth.getInstance().getCurrentUser().getUid();


        //loading the profile picture up for the user
        StorageReference profileRef = storageReference.child("users/"+ mAuth.getCurrentUser().getUid());
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(profileImage);
            }
        });

        //referencing that user and showing their profile
            documentReference = fStore.collection("users").document(userID);
            documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                    if (documentSnapshot == null) {
                        Log.i("Error", error.toString());
                        Toast.makeText(UserProfile.this, "Document doesnt exist", Toast.LENGTH_SHORT).show();
                    } else {
                        fullName.setText(documentSnapshot.getString("fullname"));
                        email.setText(documentSnapshot.getString("email"));

                    }
                }
            });

        //editing profile
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfile.this, EditUserProfile.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"Editing Profile...",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        //changing password
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfile.this,ChangePassword.class);
                startActivity(intent);
                finish();
            }
        });

        //deleting account
        deleteAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(UserProfile.this);
                dialog.setTitle("Are you sure?");
                dialog.setMessage("Deleting this account will result in completely removing your " +
                        "account from the system and you won't be able to access the app.");
                dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        progressBar.setVisibility(View.VISIBLE);
                        firebaseUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                progressBar.setVisibility(View.GONE);
                                if(task.isSuccessful()){
                                    //user is deleted
                                    Toast.makeText(getApplicationContext(),"Account Deleted.",Toast.LENGTH_LONG).show();

                                    Intent intent = new Intent(UserProfile.this,LoginActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                }

                            }
                        });
                    }
                });

                dialog.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.dismiss();
                    }
                });

                AlertDialog alertDialog = dialog.create();
                alertDialog.show();
            }
        });

    }


    //showing the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.scmenu, menu);
        return true;
    }
    //menu logic
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
                Intent intent = new Intent(UserProfile.this,LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                Toast.makeText(this,"You have been successfully logged out.",Toast.LENGTH_LONG).show();
                finish();

            default:
                return super.onOptionsItemSelected(item);
        }

    }

}
