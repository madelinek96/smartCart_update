package com.example.foodquiz;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.data.model.User;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class EditUserProfile extends AppCompatActivity {
    ImageView profileImageView; //profilepic
    TextView editName;
    TextView editEmail;
    ProgressBar progressBar;
    Button saveProfile;
    Button changePassword;
    Button updateFoodQuiz;
    Button deleteAcc;
    Uri imageUri;
    UploadTask uploadTask;
    StorageReference storageReference;
    DatabaseReference databaseReference;
    DocumentReference documentReference;
    FirebaseUser userID;
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    private static final int PICK_IMAGE = 1;
    private static final String TAG = "UserProfile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edituserprofile);

        profileImageView = findViewById(R.id.userProfile);
        editName = findViewById(R.id.editProfileName);
        changePassword = findViewById(R.id.btnChangePassword);
        editEmail = findViewById(R.id.editProfileEmail);

        saveProfile = findViewById(R.id.btnEditProfile);
        changePassword = findViewById(R.id.btnChangePassword);
        updateFoodQuiz = findViewById(R.id.btnUpdateFoodQuiz);
        deleteAcc = findViewById(R.id.btnDeleteAccount);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

        userID = mAuth.getCurrentUser();


        StorageReference profileRef = storageReference.child("gs://smartcart-184ed.appspot.com/users/");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(profileImageView);
            }
        });


        //SAVING PROFILE DATA
        saveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editName.getText().toString().isEmpty() || editEmail.getText().toString().isEmpty()){
                    Toast.makeText(EditUserProfile.this,"One or Many fields are empty.",Toast.LENGTH_SHORT).show();
                    return;
                }

                //changing email in database
                final String email = editEmail.getText().toString();
                userID.updateEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        DocumentReference documentReference = fStore.collection("users").document(userID.getUid());
                        Map<String,Object> edited = new HashMap<>();
                        edited.put("email",email);
                        edited.put("fullname",editName.getText().toString());
                        documentReference.update(edited).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(EditUserProfile.this,"Profile Updated", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), UserProfile.class));
                                finish();
                            }
                        });
                        Toast.makeText(EditUserProfile.this,"Email is changed.",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(EditUserProfile.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });

        //CHANGING PROFILE IMAGE
        profileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGalleryIntent,1000);
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            if(resultCode == Activity.RESULT_OK){
                Uri imageUri = data.getData();

                uploadImageToFirebase(imageUri);
            }
        }
    }

    private void uploadImageToFirebase(Uri imageUri){
        //upload image to firebase storage
        final StorageReference fileRef = storageReference.child("gs://smartcart-184ed.appspot.com/users/");
        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(profileImageView);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Failed.",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

}
