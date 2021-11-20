package com.example.aroma.presenter;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import com.example.aroma.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class MainPresenter {

    IMainView view;
    Context context;
    private final FirebaseAuth auth;
    private final FirebaseFirestore db;

    public  MainPresenter(IMainView view)
    {
        auth=FirebaseAuth.getInstance();
        db= FirebaseFirestore.getInstance();
        this.view=view;
    }

    public  void signIn(String email,String password)
    {
        //authenticate user
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        // progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            getUserInformation(auth.getCurrentUser().getEmail());

                        } else {
                            //Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            //startActivity(intent);
                            // finish();
                            view.onError("Failed to login: "+task.getException().getMessage());
                        }
                    }
                });

    }

    private  void getUserInformation(String email)
    {
        email=email.toLowerCase();
        db.collection("users").whereEqualTo("email",email).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.getResult().size()>0)
                {
                    User user=task.getResult().getDocuments().get(0).toObject(User.class);
                    view.onLoginSuccesful();
                    return;
                }

                view.onError("Something went wrong!");

            }
        });
    }


    //SIGN UP
    public void createUser(final User user, String password)
    {
        auth.createUserWithEmailAndPassword(user.getEmail(), password)
                .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        //checking if success
                        if(task.isSuccessful()){
                            //display some message here
                            addUser(user);

                            //  registerView;
                        }else{
                            //display some message here
                            view.onError("Failed to Register User: "+task.getException().getMessage());

                        }

                    }
                });


    }

    private   void addUser(User user){


        CollectionReference dbCourses = db.collection("users");

        dbCourses.add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                // after the data addition is successful
                // we are displaying a success toast message.
                //Toast.makeText(MainActivity.this, "Your Course has been added to Firebase Firestore", Toast.LENGTH_SHORT).show();
                view.onSuccess();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // this method is called when the data addition process is failed.
                // displaying a toast message when data addition is failed.
                // Toast.makeText(MainActivity.this, "Fail to add course \n" + e, Toast.LENGTH_SHORT).show();

                view.onError(e.getMessage());
            }
        });
    }

}
