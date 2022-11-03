package com.example.swifttow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signup extends AppCompatActivity {

    //creation of Variables
    private EditText EmailSP, PasswordSP, PasswordSP2;
    private Button SignupBtn;
    private android.app.ProgressDialog ProgressDialog;
    private FirebaseAuth fireAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //getting variables IDs
        fireAuth = FirebaseAuth.getInstance();
        EmailSP = findViewById(R.id.SignupEditTextEmailAddress);
        PasswordSP = findViewById(R.id.SignupEditTextPassword);
        PasswordSP2 = findViewById(R.id.SignupEditTextPassword2);
        SignupBtn = findViewById(R.id.signupBtn);
        ProgressDialog = new ProgressDialog(this);


        //OnClick function
        SignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUP();
            }
        });


    }
    //Function for Signup Button
    private void SignUP() {

        String Email = EmailSP.getText().toString();
        String Password1 = PasswordSP.getText().toString();
        String Password2 = PasswordSP2.getText().toString();


        //checking for Errors
        if (TextUtils.isEmpty(Email)) {
            EmailSP.setError("Enter Email");
            return;
        } else if (TextUtils.isEmpty(Password1)) {
            PasswordSP.setError("Enter Password");
            return;
        } else if (TextUtils.isEmpty(Password2)) {
            PasswordSP2.setError("Confirm Password");
            return;
        } else if (!Password1.equals(Password2)) {
            PasswordSP2.setError("Different Passwords");
            return;
        } else if (Password1.length() < 4) {
            PasswordSP.setError("Password is too small");
            return;
        } else if (!isValidEmail(Email)) {
            EmailSP.setError("Invalid Email");
            return;
        }

        //progress dialog
        ProgressDialog.setMessage("Please Wait ...");
        ProgressDialog.show();
        ProgressDialog.setCanceledOnTouchOutside(false);

        fireAuth.createUserWithEmailAndPassword(Email, Password1).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(signup.this, "Registration successful", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(signup.this, MainActivityController.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(signup.this, "Registration not successful", Toast.LENGTH_LONG).show();
                    ProgressDialog.dismiss();
                }
            }
        });

    }

    //function for checking validity of Email
    private boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }



}