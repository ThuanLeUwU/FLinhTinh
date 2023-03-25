package com.project.flinhtinh.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.project.flinhtinh.R;
import com.project.flinhtinh.model.Customer;
import com.project.flinhtinh.model.Store;

public class StoreDetail extends AppCompatActivity{
    private Button backButton;
    private Store mStore;
    private TextView mStoreName,mStorePhone, mstoreAddress,mStoreStatus;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_store_detail);

        Intent intent = getIntent();
        mStore = (Store) intent.getSerializableExtra("store");


        mStoreName =  findViewById(R.id.store_name);
        mStorePhone =  findViewById(R.id.store_phone);
        mstoreAddress = findViewById(R.id.store_address);
        mStoreStatus =  findViewById(R.id.store_status);

        mStoreName.setText("Name: " + mStore.getName());
        mStorePhone.setText("Phone: " +mStore.getPhone());
        mstoreAddress.setText("Address : " +mStore.getAddress());
        mStoreStatus.setText("Status :" + mStore.getStatus());



        backButton = (Button) findViewById(R.id.btn_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( StoreDetail.this, StoreActivity.class);
                startActivity(intent);
            }
        });
    }
}
