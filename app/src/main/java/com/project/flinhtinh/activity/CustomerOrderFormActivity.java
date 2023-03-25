package com.project.flinhtinh.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.project.flinhtinh.R;
import com.project.flinhtinh.model.CustomerOrderForm;

public class CustomerOrderFormActivity extends AppCompatActivity {
    Button btnConfirm, btnCancel;
    EditText fullName, phone, email, shippingAddress, city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_form);

        fullName = findViewById(R.id.et_fullname);
        phone = findViewById(R.id.et_phone);
        email = findViewById(R.id.et_email);
        shippingAddress = findViewById(R.id.et_shipping_address);
        city = findViewById(R.id.et_city);

        btnConfirm = findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener(v -> {
            if(checkCustomerOrderForm()){
                Intent orderCheckout = new Intent(this, OrderCheckoutActivity.class);
                orderCheckout.putExtra("CUSTOMER_FORM", new CustomerOrderForm(
                        fullName.getText().toString(),
                        phone.getText().toString(),
                        email.getText().toString(),
                        shippingAddress.getText().toString(),
                        city.getText().toString()));
                startActivity(orderCheckout);
            }
        });

        btnCancel = findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(v -> {
            finish();
        });
    }



    private boolean checkCustomerOrderForm(){
        boolean check = true;
        if(fullName.getText().toString().length() == 0){
            fullName.requestFocus();
            fullName.setError("Required field!");
            check = false;
        }
        if(phone.getText().toString().length() == 0){
            phone.requestFocus();
            phone.setError("Required field!");
            check = false;
        }
        String textEmail = email.getText().toString();
        if(textEmail.length() == 0){
            email.requestFocus();
            email.setError("Required field!");
            check = false;
        } else if(!Patterns.EMAIL_ADDRESS.matcher(textEmail).matches()){
            email.requestFocus();
            email.setError("Wrong email format!");
            check = false;
        }
        if(shippingAddress.getText().toString().length() == 0){
            shippingAddress.requestFocus();
            shippingAddress.setError("Required field!");
            check = false;
        }
        if(city.getText().toString().length() == 0){
            city.requestFocus();
            city.setError("Required field!");
            check = false;
        }
        return check;
    }
}
