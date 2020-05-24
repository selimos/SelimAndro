package com.example.myandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText mTextusername;
    EditText mTextPassword;
    EditText mTextcnfPassword;
    Button mButtonRegister;
    TextView mTextViewLogin;
    DataBaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db = new DataBaseHelper(this);
        mTextusername = (EditText)findViewById(R.id.edittext_username);
        mTextPassword = (EditText)findViewById(R.id.edittext_password);
        mTextcnfPassword = (EditText)findViewById(R.id.edittext_cnf_password);
        mButtonRegister = (Button)findViewById(R.id.button_register);
        mTextViewLogin = (TextView)findViewById(R.id.textview_Login);
        mTextViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LoginIntent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(LoginIntent);
            }
        });
        mButtonRegister.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = mTextusername.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();
                String cnf_pwd = mTextcnfPassword.getText().toString().trim();
                if(pwd.equals(cnf_pwd)){
                    long val = db.addUser(user,pwd);
                    if(val>0) {
                        Toast.makeText(RegisterActivity.this, "Succesfully registred", Toast.LENGTH_SHORT).show();
                        Intent moveToLogin = new Intent( RegisterActivity.this,MainActivity.class);
                        startActivity(moveToLogin);
                    }else{
                        Toast.makeText(RegisterActivity.this,"Error",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(RegisterActivity.this,"Password not matching",Toast.LENGTH_SHORT).show();
                }
            }
        }));
    }
}
