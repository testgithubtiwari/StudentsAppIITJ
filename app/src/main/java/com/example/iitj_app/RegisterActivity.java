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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private EditText username,useremail,userpassword,userpasswordagain,usercontact;
    private Button signUp;
    private ProgressDialog pd;

    private static final  String TAG="RegisterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username=findViewById(R.id.Name);
        useremail=findViewById(R.id.adminemailId);
        userpassword=findViewById(R.id.adminpwd);
        userpasswordagain=findViewById(R.id.pwdagain);
        usercontact=findViewById(R.id.userContact);

        pd=new ProgressDialog(this);


        signUp=findViewById(R.id.btnsignup);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name=username.getText().toString();
                String email=useremail.getText().toString();
                String pwd=userpassword.getText().toString();
                String pwdagain=userpasswordagain.getText().toString();
                String contact=usercontact.getText().toString();

                // validate the mobile number using regular expression
                String mobileRegx="[6-9][0-9]{9}";
                Matcher mobileMatcher;
                Pattern mobilePattern=Pattern.compile(mobileRegx);
                mobileMatcher=mobilePattern.matcher(contact);

                if(TextUtils.isEmpty(name)){
                    username.setError("Empty!");
                    username.requestFocus();
                }else if(TextUtils.isEmpty(email))
                {
                    useremail.setError("Empty!");
                    useremail.requestFocus();
                }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    useremail.setError("Enter valid E-mail");
                    useremail.requestFocus();
                }
                else if(TextUtils.isEmpty(contact))
                {
                    usercontact.setError("Conatct number is Required");
                    usercontact.requestFocus();
                }else if(contact.length()!=10)
                {
                    usercontact.setError("Contact number should be of 10 digits");
                    usercontact.requestFocus();
                }else if(!mobileMatcher.find()){
                    usercontact.setError("Format is wrong");
                    usercontact.requestFocus();
                }else if(TextUtils.isEmpty(pwd)){
                    userpassword.setError("Empty!");
                    userpassword.requestFocus();
                }else if(pwd.length()<=6)
                {
                    userpassword.setError("Password should be grater than 6 digits.");
                    userpassword.requestFocus();
                }else if(!pwdagain.equals(pwd))
                {
                    userpasswordagain.setError("Password not matches");
                    userpasswordagain.requestFocus();
                }
                else {
                    registerUser(name,email,pwd,contact);
                }
            }
        });
    }

    private void registerUser(String name, String email, String pwd, String contact) {
        pd.setMessage("Registration Started!");
        pd.show();
        FirebaseAuth auth=FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(RegisterActivity.this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            FirebaseUser firebaseUser=auth.getCurrentUser();

                            UserProfileChangeRequest profileChangeRequest=new UserProfileChangeRequest.Builder().setDisplayName(name).build();
                            firebaseUser.updateProfile(profileChangeRequest);

                            // enter user data firebase in the real time
                            ReadWriteUserDetails writeUserDetails =new ReadWriteUserDetails(name,contact);

                            /// extracting reference for databse registered users
                            DatabaseReference referenceProfile= FirebaseDatabase.getInstance().getReference("Registered Users IITJ_APP");

                            referenceProfile.child(firebaseUser.getUid()).setValue(writeUserDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if(task.isSuccessful())
                                    {
                                        pd.dismiss();
                                        Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    }else {

                                        Toast.makeText(RegisterActivity.this,"Registration failed , try again",Toast.LENGTH_LONG).show();

                                    }
                                    pd.dismiss();
                                }
                            });

                        }
                        else {
                            try{
                                throw task.getException();
                            }
                            catch (FirebaseAuthWeakPasswordException e){
                                userpassword.setError("Your password is too weak. Kindly use numeric,alphabets and special characters");
                                userpassword.requestFocus();
                            } catch (FirebaseAuthInvalidCredentialsException e){
                                useremail.setError("Your e-mail is invalid or already in use. Kindly re-enter");
                                useremail.requestFocus();

                            }catch(FirebaseAuthUserCollisionException e){
                                useremail.setError("User is already registered with this e-mail, use another");
                                useremail.requestFocus();
                            }

                            catch (Exception e) {
                                Log.e(TAG,e.getMessage());
                                Toast.makeText(RegisterActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                            }
                            pd.dismiss();
                        }

                    }
                });

    }
}