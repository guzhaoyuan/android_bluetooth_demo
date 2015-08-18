package com.example.gzy.demo2;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import java.util.Map;

import loginService.LoginService;

public class MainActivity extends ActionBarActivity {

    private EditText username;
    private EditText password;
    private Button login;
    private CheckBox cb;
    private TextView loginLockedTV;
    private TextView attemptsLeftTV;
    private TextView numberOfRemainingLoginAttemptsTV;
    int numberOfRemainingLoginAttempts = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupVariables();
        Map<String,String> map = LoginService.getSavedUserInfo(this);
        if(map!=null){
            username.setText(map.get(username));
            password.setText(map.get(password));
        }
    }


    public void authenticateLogin(View view) {
        if(cb.isChecked()){
            boolean result = LoginService.saveUserInfo(this,username.getText().toString(),password.getText().toString());
            if(result)
                Toast.makeText(this,"Save Successfully",Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this,"Save Unsuccessfully",Toast.LENGTH_SHORT).show();
        }
        if (username.getText().toString().equals("gzy") &&//发送用户名到服务器
                password.getText().toString().equals("guzhaoyuan")) {
            Intent intent =new Intent();
            intent.setClass(MainActivity.this,info.class);
            startActivity(intent);
            MainActivity.this.finish();
        } else {
            Toast.makeText(getApplicationContext(), "username and password do not match",
                    Toast.LENGTH_SHORT).show();
            numberOfRemainingLoginAttempts--;
            attemptsLeftTV.setVisibility(View.VISIBLE);
            numberOfRemainingLoginAttemptsTV.setVisibility(View.VISIBLE);
            numberOfRemainingLoginAttemptsTV.setText(Integer.toString(numberOfRemainingLoginAttempts));

            if (numberOfRemainingLoginAttempts == 0) {
                login.setEnabled(false);
                loginLockedTV.setVisibility(View.VISIBLE);
                loginLockedTV.setBackgroundColor(Color.RED);
                loginLockedTV.setText("LOGIN LOCKED!!!");
            }
        }
    }

    private void setupVariables() {
        username = (EditText) findViewById(R.id.usernameET);
        password = (EditText) findViewById(R.id.passwordET);
        login = (Button) findViewById(R.id.loginBtn);
        loginLockedTV = (TextView) findViewById(R.id.loginLockedTV);
        attemptsLeftTV = (TextView) findViewById(R.id.attemptsLeftTV);
        numberOfRemainingLoginAttemptsTV = (TextView) findViewById(R.id.numberOfRemainingLoginAttemptsTV);
        numberOfRemainingLoginAttemptsTV.setText(Integer.toString(numberOfRemainingLoginAttempts));
        cb = (CheckBox)findViewById(R.id.checkbox);
    }
}
