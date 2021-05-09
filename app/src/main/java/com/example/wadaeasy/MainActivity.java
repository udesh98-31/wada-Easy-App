package com.example.wadaeasy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText FirstName,LastName,Email,Password,Cpassword;
    TextView textlogin;
    Button btnsign;
    DatabaseReference dbref;
    FirebaseAuth mFirebaseAuth;
    User ac;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Assign to layout Id
        mFirebaseAuth = FirebaseAuth.getInstance();
        FirstName=findViewById(R.id.FirstN);
        LastName=findViewById(R.id.LastN);
        Email=findViewById(R.id.mail);
        Password=findViewById(R.id.Password);
        Cpassword=findViewById(R.id.CPassword);
        textlogin=findViewById(R.id.tvlogin);
        btnsign=findViewById(R.id.btsign);

        ac=new User();
        btnsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email =Email.getText().toString();
                //assign password and confirm pasword value to variabale for Compare
                String pwd=Password.getText().toString();
                String Cpwd=Cpassword.getText().toString();
                dbref= FirebaseDatabase.getInstance().getReference().child("User");

                    if(TextUtils.isEmpty(FirstName.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Enter valid First name",Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(LastName.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Enter valid Last name",Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(Email.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Enter valid Email",Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(Password.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Enter valid Password",Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(Cpassword.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Enter valid cp",Toast.LENGTH_SHORT).show();
                    else if(pwd.equals(Cpwd)!=true) {
                        Cpassword.setError("possword miss match");
                        Cpassword.requestFocus();
                    }


                    else {
                        mFirebaseAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(!task.isSuccessful()){
                                    Toast.makeText(MainActivity.this,"Something went wrong, Please Try Again",Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    ac.setFirstName(FirstName.getText().toString().trim());
                                    ac.setLastName(LastName.getText().toString().trim());
                                    ac.setEmail(Email.getText().toString().trim());
                                    ac.setPassword(Password.getText().toString().trim());
                                   // ac.setCPassword(Cpassword.getText().toString().trim());

                                    dbref.push().setValue(ac);
                                    Toast.makeText(getApplicationContext(),"Sucsess singup",Toast.LENGTH_SHORT).show();
                                    ClearControls();
                                    Intent intent=new Intent(MainActivity.this,Switch.class);
                                    startActivity(intent);
                                }
                            }
                        });
                    }
            }

        });
        //Login Text view Button
        textlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ilogin=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(ilogin);
            }
        });
    }

  //keyboard input clear method
    private void ClearControls(){
        FirstName.setText("");
        LastName.setText("");
        Email.setText("");
        Password.setText("");
        Cpassword.setText("");

    }
}