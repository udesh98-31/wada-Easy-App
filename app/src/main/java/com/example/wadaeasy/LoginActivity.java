package com.example.wadaeasy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class LoginActivity extends AppCompatActivity {
    EditText LoginEmail,LoginPassword;
    TextView tvLogin;
    Button btnlogin;
    DatabaseReference dbref;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mFirebaseAuth=FirebaseAuth.getInstance();
        LoginEmail=findViewById(R.id.Lmaill);
        LoginPassword=findViewById(R.id.Lpassword);
        tvLogin=findViewById(R.id.txvLogin);

        btnlogin=findViewById(R.id.btlogin);

        mAuthStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser= mFirebaseAuth.getCurrentUser();
                if(mFirebaseAuth!=null) {
                    Toast.makeText(LoginActivity.this, "your Loged in", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this, Switch.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(LoginActivity.this, "plz Loged in", Toast.LENGTH_SHORT).show();




                }
            }
        };
        //login button onclick
        
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 String email=LoginEmail.getText().toString();
                 String pwd=LoginPassword.getText().toString();

                 if(email.isEmpty()){
                     LoginEmail.setError("please enter valid Email");
                     LoginEmail.requestFocus();
                 }
                 else if(pwd.isEmpty()){
                     LoginPassword.setError("please Enter valid password");
                     LoginPassword.requestFocus();
                 }
                 else if(email.isEmpty() && pwd.isEmpty()){
                     Toast.makeText(LoginActivity.this,"Fields are empty",Toast.LENGTH_SHORT).show();
                 }
                 else if(!(email.isEmpty() && pwd.isEmpty())){
                     mFirebaseAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                         @Override
                         public void onComplete(@NonNull Task<AuthResult> task) {
                             if(!task.isSuccessful()){
                                 Toast.makeText(LoginActivity.this,"Login Error Please Login Again",Toast.LENGTH_SHORT).show();
                             }
                             else{
                                 Intent intentHome =  new Intent(LoginActivity.this,Switch.class);
                                 startActivity(intentHome);
                             }

                         }
                     });
                 }
                 else{
                     Toast.makeText(LoginActivity.this,"Login Error",Toast.LENGTH_SHORT).show();

                 }


            }
        });
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Singinintent=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(Singinintent);
            }
        });



    }
    /*
    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }
*/

}
