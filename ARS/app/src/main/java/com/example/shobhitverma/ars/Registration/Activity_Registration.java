package com.example.shobhitverma.ars.Registration;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shobhitverma.ars.Home.Activity_Home;
import com.example.shobhitverma.ars.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Activity_Registration extends AppCompatActivity {
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private EditText et1,et2;
    private Button btn1,btn2;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks;
    private FirebaseAuth mAuth;
    private static int splashTimeOut=4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        mAuth=FirebaseAuth.getInstance();
        et1=(EditText)findViewById(R.id.registration_eT_mobileNo);
        et2=(EditText)findViewById(R.id.registration_eT_verificationCode);
        btn1=(Button)findViewById(R.id.registration_btn_sendVerificationCode);
        btn2=(Button)findViewById(R.id.registration_btn_confirmCode);
        if(mAuth.getCurrentUser()!=null)
        {
            Toast.makeText(Activity_Registration.this,"Already Login.." + mAuth.getCurrentUser().getPhoneNumber(),Toast.LENGTH_LONG).show();
            startActivity(new Intent(Activity_Registration.this, Activity_Home.class));
            finish(); }

        callbacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {
                Toast.makeText(Activity_Registration.this,"Verification Code Is Sent",Toast.LENGTH_LONG).show();

                mVerificationId = verificationId;
                mResendToken = token;
            }
            @Override
            public void onVerificationFailed(FirebaseException e) {
                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    Toast.makeText(Activity_Registration.this,"Verification Failed",Toast.LENGTH_LONG).show();
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                    // ...
                }
                else if(e instanceof FirebaseAuthUserCollisionException) {
                    Toast.makeText(Activity_Registration.this, "Already Has Account", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(Activity_Registration.this, Activity_Home.class));
                    finish();
                }
            }



        };
        /*Handler handler=new Handler();
        for(int i=0;i<100;i++)
        {


            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    sendVerificationCode();
                }
            },splashTimeOut);
        }*/
      btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              sendVerificationCode();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Code=et2.getText().toString();
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, Code);
                signInWithPhoneAuthCredential(credential);
            }
        });
    }
    private void sendVerificationCode()
    {
        String s=et1.getText().toString();
        if(TextUtils.isEmpty(s))
            Toast.makeText(Activity_Registration.this, "Please Enter Mobile No", Toast.LENGTH_LONG).show();

        /*else if(s.length()!=10)
          Toast.makeText(Activity_Registration.this, "Please Enter Valid Mobile No", Toast.LENGTH_LONG).show();*/
         else
        {
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    s,        // Phone number to verify
                    2,                 // Timeout duration
                    TimeUnit.SECONDS,   // Unit of timeout
                    Activity_Registration.this,               // Activity (for callback binding)
                    callbacks);
        }


    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(Activity_Registration.this,"Successful LogIn",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(Activity_Registration.this,Activity_Home.class));
                            finish();
                            FirebaseUser user = task.getResult().getUser();
                            // ...
                        } else {

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {

                            }
                        }
                    }
                });
    }

}
