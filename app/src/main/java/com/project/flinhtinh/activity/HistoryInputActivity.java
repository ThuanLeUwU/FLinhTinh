package com.project.flinhtinh.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.project.flinhtinh.R;
import com.project.flinhtinh.api.CustomerApi;
//import com.project.flinhtinh.api.OtpApi;
import com.project.flinhtinh.model.Customer;
//import com.project.flinhtinh.model.OTP;

import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryInputActivity extends AppCompatActivity {
    private final String from = "quantmse151001@fpt.edu.vn";
    private final String password = "maptie123456789";
    private final String subject = "OTP authentication";

    private EditText inputEmail;
    private Button btnCancel, btnConfirm;
    private TextView textHistory;
    private ImageView imgCart, menu;
    private ProgressBar progressBar;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_input);
        progressBar = findViewById(R.id.confirm_progress_bar);
        inputEmail = findViewById(R.id.input_email);
        btnCancel = findViewById(R.id.btn_cancel);
        btnConfirm = findViewById(R.id.btn_confirm);


        btnConfirm.setOnClickListener(v -> {
            btnConfirm.setText("");
            btnConfirm.setEnabled(false);
            progressBar.setVisibility(View.VISIBLE);
            String email = inputEmail.getText().toString();
            findCustomerByEmail(email);
        });
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        btnCancel.setOnClickListener(v -> {
            finish();
        });
    }

    private void findCustomerByEmail(String email) {

        CustomerApi.CUSTOMER_API.findCustomerByEmail(email).enqueue(new Callback<Customer[]>() {
            @Override
            public void onResponse(Call<Customer[]> call, Response<Customer[]> response) {
                Customer[] customer = response.body();
                if (customer.length == 1) {
                    Intent orderHistory = new Intent(HistoryInputActivity.this, OrderHistoryActivity.class);
                    orderHistory.putExtra("EMAIL", email);
                    startActivity(orderHistory);
                    btnConfirm.setText("Xác Nhận");
                    btnConfirm.setEnabled(true);
                    progressBar.setVisibility(View.INVISIBLE);
                } else {

                }
            }

            @Override
            public void onFailure(Call<Customer[]> call, Throwable t) {

            }
        });
    }
}