package com.example.iitj_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;

public class ForgotPassword extends AppCompatActivity {

    private EditText useremail;
    private Button getPwd;
    private FirebaseAuth authProfile;
    private ProgressDialog pd;

    private static final  String TAG="ForgotPassword";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);


        useremail=findViewById(R.id.userEmail);
        getPwd=findViewById(R.id.getPwd);
        pd=new ProgressDialog(this);

        getPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=useremail.getText().toString();

                if(TextUtils.isEmpty(email))
                {
                    useremail.setError("Email is required");
                    useremail.requestFocus();
                }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    useremail.setError("Email format is not valid");
                    useremail.requestFocus();
                }else
                {
                    resetPassword(email);
                }
            }
        });
    }

    private void resetPassword(String email) {
        pd.setMessage("Loading..");
        pd.show();
        authProfile=FirebaseAuth.getInstance();
        authProfile.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    pd.dismiss();
                    Toast.makeText(ForgotPassword.this,"Please check your email inbox to get new password",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(ForgotPassword.this,LoginActivity.class);
                    // clear stack to prevent user coming back to this activity
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK |
                            Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }else
                {
                    try{
                        throw task.getException();
                    }catch(FirebaseAuthInvalidCredentialsException e){
                        useremail.setError("User doesn't exist or no longer exist. Please register again");

                    }catch (Exception e){
                        Log.e(TAG,e.getMessage());
                        Toast.makeText(ForgotPassword.this,e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
                pd.dismiss();
            }
        });
    }
}