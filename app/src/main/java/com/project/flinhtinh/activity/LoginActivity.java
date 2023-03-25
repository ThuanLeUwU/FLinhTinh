package com.project.flinhtinh.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.project.flinhtinh.R;
import com.project.flinhtinh.api.LoginApi;
import com.project.flinhtinh.model.Account;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText loginUserName, loginPassword;
    Button btnLogin, btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        btnLogin = findViewById(R.id.button_login);
        btnBack = findViewById(R.id.button_back);
        loginUserName = findViewById(R.id.username);
        loginPassword = findViewById(R.id.password);

        //Back home
        btnLogin.setOnClickListener(view -> {
            String userName = loginUserName.getText().toString();
            String password = loginPassword.getText().toString();
            callApiLogin(userName, password);
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void callApiLogin(String email, String password){
        LoginApi.LOGIN_API.getLoginAccount(email).enqueue(new Callback<Account[]>() {
            @Override
            public void onResponse(Call<Account[]> call, Response<Account[]> response) {
                Account[] account = response.body();
                if(account.length == 1) {
                    if (account[0].getEmail().equals(email) && account[0].getPassword().equals(password)) {

                        Intent i = new Intent(LoginActivity.this, CustomerViewActivity.class);
                        startActivity(i);
                    }
                } else {

                }
            }

            @Override
            public void onFailure(Call<Account[]> call, Throwable t) {

            }
        });
    }
}