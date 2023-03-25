package com.project.flinhtinh.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.project.flinhtinh.R;
import com.project.flinhtinh.model.Customer;

public class CustomerDetail extends AppCompatActivity {
    private Customer mCustomer;
    private TextView mCustomerName;
    private TextView mCustomerEmail;
    private TextView mCustomerPhone;
    private TextView mCustomerAddress;
    private TextView mCustomerStatus;
    private Button backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_customer_detail);

        Intent intent = getIntent();
        mCustomer = (Customer) intent.getSerializableExtra("customer");

        mCustomerName = findViewById(R.id.customer_name);
        mCustomerEmail = findViewById(R.id.customer_email);
        mCustomerPhone = findViewById(R.id.customer_phone);
        mCustomerAddress = findViewById(R.id.customer_address);
        mCustomerStatus = findViewById(R.id.customer_status);

        mCustomerName.setText("Name: " + mCustomer.getFullName());
        mCustomerEmail.setText("Gmail: " + mCustomer.getEmail());
        mCustomerPhone.setText("Phone: " +mCustomer.getPhone());
        mCustomerAddress.setText("Address : " +mCustomer.getAddress());
        mCustomerStatus.setText("Status :" + mCustomer.getStatus());

        backButton = (Button) findViewById(R.id.btn_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( CustomerDetail.this, CustomerViewActivity.class);
                startActivity(intent);
            }
        });
    }
}

