package com.example.rfmcafe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rfmcafe.model.Login;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtEmail, edtPassword;
    Button btnSignUp, btnSignIn;

    ArrayList<Login> accountList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.referWodgets();
        this.loadInitialData();
    }

    private void referWodgets() {
        edtEmail = findViewById(R.id.edt_email);
        edtPassword = findViewById(R.id.edt_password);

        btnSignUp = findViewById(R.id.btn_signup);
        btnSignUp.setOnClickListener(this);

        btnSignIn = findViewById(R.id.btn_signin);
        btnSignIn.setOnClickListener(this);

        //allocate memory to ArrayList object
        accountList = new ArrayList<Login>();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_signup:
                //sign up
                this.doSignUp();
                break;
            case R.id.btn_signin:
                //sign in
                this.doSignIn();
                break;
        }
    }

    private void doSignUp() {
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();

        Login newAccount = new Login(email, password);
        accountList.add(newAccount);

        Toast.makeText(getApplicationContext(), "Login Created Successfully!",
                Toast.LENGTH_LONG).show();
    }

    private void doSignIn() {
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();

        for (int i=0; i<accountList.size(); i++) {
            if (email.equalsIgnoreCase(accountList.get(i).getEmail()) &&
                    password.equals(accountList.get(i).getPassword())) {
                Toast.makeText(getApplicationContext(), "Valid Login",
                        Toast.LENGTH_LONG).show();
                break;
            }
            else {
                Toast.makeText(getApplicationContext(), "Invalid Login \n Please Try Again!",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    private void loadInitialData() {
        Login account1 = new Login("me@sh.ca", "me1");
        Login account2 = new Login("you@sh.ca", "you2");

        accountList.add(account1);
        accountList.add(account2);
    }

    private void startMainActivity() {
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }
}
