package com.project.flinhtinh.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.flinhtinh.R;
import com.project.flinhtinh.apdater.CustomerAdapter;
import com.project.flinhtinh.api.CustomerApi;
import com.project.flinhtinh.model.Customer;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerViewActivity extends AppCompatActivity {
    private TextView textCustomer, textStore, textStaff;
    private ImageView iconCustomer, iconStore, iconStaff, menu;
    private SearchView searchView;
    private CustomerAdapter customerAdapter;
    private List<Customer> listCustomer;
    private RecyclerView rcvCustomer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer);

        textStore = findViewById(R.id.text_store);
        textStaff = findViewById(R.id.text_staff);
        iconStore = findViewById(R.id.icon_store);
        iconStaff = findViewById(R.id.icon_staff);
        rcvCustomer = findViewById(R.id.rcv_customer);
        menu = findViewById(R.id.menu);
        searchView = findViewById(R.id.search_view);

        View.OnClickListener onClickStore = v -> {
            Intent storeView = new Intent(this, StoreActivity.class);
            startActivity(storeView);
            finish();
        };
        textStore.setOnClickListener(onClickStore);
        iconStore.setOnClickListener(onClickStore);


        callApiGetListCustomer();
    }

    private void callApiGetListCustomer(){
        CustomerApi.CUSTOMER_API.getListCustomer().enqueue(new Callback<List<Customer>>() {
            @Override
            public void onResponse(Call<List<Customer>> call, Response<List<Customer>> response) {
                List<Customer> getListCustomer = response.body();
                listCustomer = getListCustomer;
                if(!getListCustomer.isEmpty()){
                    customerAdapter = new CustomerAdapter(CustomerViewActivity.this, getListCustomer);
                    rcvCustomer.setLayoutManager(new GridLayoutManager(CustomerViewActivity.this, 1));
                    rcvCustomer.setAdapter(customerAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Customer>> call, Throwable t) {
                Log.d("CUSTOMER", "CALL API CUSTOMER FAILED");
            }
        });
    }

}