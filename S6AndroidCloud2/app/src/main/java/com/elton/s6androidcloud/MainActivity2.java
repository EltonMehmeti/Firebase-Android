package com.elton.s6androidcloud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity2 extends AppCompatActivity {

    EditText email,password;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        email = findViewById(R.id.editTextText2);
        password = findViewById(R.id.editTextText3);
        auth = FirebaseAuth.getInstance();
    }
    public void signup (View view){
        if(!email.getText().toString().isEmpty() && !password.getText().toString().isEmpty()){

        auth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
if(task.isSuccessful())
    showMessage("Succes","User created!");
else showMessage("Error",task.getException().getLocalizedMessage());
            }
        });
        }
    }
    public void signin(View view) {
        if (!email.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {
            auth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                showMessage("Success", "You Signed In!");
                                Intent intent = new Intent(view.getContext(), MainActivity.class);
                                startActivity(intent);
                            } else {
                                showMessage("Error", task.getException().getLocalizedMessage());
                            }
                        }
                    });
        }
    }

    void showMessage(String title,String message){
        new AlertDialog.Builder(this).setTitle(title).setMessage(message).setCancelable(true).show();
    }
}