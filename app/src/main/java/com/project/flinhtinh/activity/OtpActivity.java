package com.project.flinhtinh.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.project.flinhtinh.R;
import com.project.flinhtinh.api.OtpApi;
import com.project.flinhtinh.model.OTP;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtpActivity extends AppCompatActivity {
    private EditText inputOtp;
    private Button btnCancel, btnConfirm;
    private TextView textHistory;
    private ImageView imgCart, menu;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        inputOtp = findViewById(R.id.input_otp);
//        textHistory = findViewById(R.id.text_history);
//        imgCart = findViewById(R.id.shopping_cart);
        menu = findViewById(R.id.menu);
        searchView = findViewById(R.id.search_view);
        btnCancel = findViewById(R.id.btn_cancel);
        btnConfirm = findViewById(R.id.btn_confirm);

        textHistory.setOnClickListener(v -> {
            Intent historyInput = new Intent(this, HistoryInputActivity.class);
            startActivity(historyInput);
        });

        imgCart.setOnClickListener(v -> {
            Intent cart = new Intent(this, CartActivity.class);
            startActivity(cart);
        });

        menu.setOnClickListener(v -> {
            Intent login = new Intent(this, LoginActivity.class);
            startActivity(login);
        });

        btnConfirm.setOnClickListener(v -> {
            Intent intent = getIntent();
            String email = intent.getStringExtra("EMAIL");
            String otp = inputOtp.getText().toString();
            callApiGetOtpByEmail(email, otp);
        });

        btnCancel.setOnClickListener(v -> {
            finish();
        });
    }

    private void callApiGetOtpByEmail(String email, String otp) {
        OtpApi.OTP_API.getOtp(email).enqueue(new Callback<OTP[]>() {
            @Override
            public void onResponse(Call<OTP[]> call, Response<OTP[]> response) {
                OTP[] getOtp = response.body();
//                if(getOtp[0].getExpiredDate().before(new Date())){
//                    Toast.makeText(OtpActivity.this, "OTP EXPIRED", Toast.LENGTH_SHORT).show();
//                } else {
                if (getOtp[0].getOtpCode().equalsIgnoreCase(otp)) {
                    Intent orderHistory = new Intent(OtpActivity.this, OrderHistoryActivity.class);
                    startActivity(orderHistory);
                } else {
                    Toast.makeText(OtpActivity.this, "WRONG OTP", Toast.LENGTH_SHORT).show();
                }
//                }
            }

            @Override
            public void onFailure(Call<OTP[]> call, Throwable t) {
                Toast.makeText(OtpActivity.this, "Something wrong happen", Toast.LENGTH_SHORT).show();
                Log.d("TEST", t.toString());
            }
        });
    }
}