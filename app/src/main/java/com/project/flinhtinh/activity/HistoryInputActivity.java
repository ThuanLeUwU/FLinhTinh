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
//        textHistory = findViewById(R.id.text_history);
//        imgCart = findViewById(R.id.shopping_cart);
//        menu = findViewById(R.id.menu);
//        searchView = findViewById(R.id.search_view);
        inputEmail = findViewById(R.id.input_email);
        btnCancel = findViewById(R.id.btn_cancel);
        btnConfirm = findViewById(R.id.btn_confirm);

//        imgCart.setOnClickListener(v -> {
//            Intent cart = new Intent(this, CartActivity.class);
//            startActivity(cart);
//        });

//        menu.setOnClickListener(v -> {
//            Intent login = new Intent(this, LoginActivity.class);
//            startActivity(login);
//        });

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
//                    callApiGetOtp(email);
                    Intent orderHistory = new Intent(HistoryInputActivity.this, OrderHistoryActivity.class);
                    orderHistory.putExtra("EMAIL", email);
                    startActivity(orderHistory);
                    btnConfirm.setText("Xác Nhận");
                    btnConfirm.setEnabled(true);
                    progressBar.setVisibility(View.INVISIBLE);
                } else {
                    Toast.makeText(HistoryInputActivity.this, "NO EMAIL FOUND", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Customer[]> call, Throwable t) {
                Toast.makeText(HistoryInputActivity.this, "GET API FAILED", Toast.LENGTH_SHORT).show();
            }
        });
    }

//    private void createOtp(OTP otp) {
//        OtpApi.OTP_API.createOtp(otp).enqueue(new Callback<OTP>() {
//            @Override
//            public void onResponse(Call<OTP> call, Response<OTP> response) {
//                //do sth
//            }
//
//            @Override
//            public void onFailure(Call<OTP> call, Throwable t) {
//                //do sth
//            }
//        });
//    }
//
//    private void callApiGetOtp(String email){
//        OtpApi.OTP_API.getOtp(email).enqueue(new Callback<OTP[]>() {
//            @Override
//            public void onResponse(Call<OTP[]> call, Response<OTP[]> response) {
//                OTP[] getOtp = response.body();
//                String otp = randomOtpGenerator();
//                if(getOtp.length == 1){
//                    getOtp[0].setOtpCode(randomOtpGenerator());
//                    String newOtp = getOtp[0].getOtpCode();
//                    getOtp[0].setExpiredDate(new Date());
//                    callApiUpdateOtp(getOtp[0].getOtpId().toString(), getOtp[0]);
//                    sendEmail(email, newOtp);
//                } else {
//                    UUID uuid = UUID.randomUUID();
//                    createOtp(new OTP(uuid, otp, email, new Date(), "ALLOW"));
//                    sendEmail(email, otp);
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<OTP[]> call, Throwable t) {
//
//            }
//        });
//    }
//
//    private void sendEmail(String email, String otp){
//        Properties props = new Properties();
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.port", "587");
//        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(from, password);
//            }
//        });
//        try {
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(from));
//            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
//            message.setSubject(subject);
//            message.setText("Đây là mã otp của bạn:\n\t\t\t" + otp);
//            Transport.send(message);
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private void callApiUpdateOtp(String id, OTP otp){
//        OtpApi.OTP_API.updateOtpWithEmail(id ,otp).enqueue(new Callback<OTP>() {
//            @Override
//            public void onResponse(Call<OTP> call, Response<OTP> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<OTP> call, Throwable t) {
//
//            }
//        });
//    }
//
//    private String randomOtpGenerator() {
//        Random random = new Random();
//        return String.format("%06d", random.nextInt(999999 - 100000));
//    }
}