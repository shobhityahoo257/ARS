package com.example.shobhitverma.ars.Registration;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shobhitverma.ars.R;

//package com.example.hp.myapplication;

public class Activity_SignUp extends AppCompatActivity {

    Button SignupButton;
    EditText name,email;
    String nameString,emailString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name =  (EditText) findViewById(R.id.signup_name);
        email = (EditText) findViewById(R.id.signup_email);
        SignupButton = (Button) findViewById(R.id.signup_button);

        nameString = name.getText().toString();
        emailString = name.getText().toString();

        SignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Activity_SignUp.this,"Details Recieved \n\tName  : " + nameString + "\n\tEmail : " + emailString ,Toast.LENGTH_LONG).show();
            }
        });
    }
}