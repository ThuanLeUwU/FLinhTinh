package com.project.flinhtinh.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.flinhtinh.R;
import com.project.flinhtinh.apdater.StoreAdapter;
import com.project.flinhtinh.api.StoreApi;
import com.project.flinhtinh.model.Store;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoreActivity extends AppCompatActivity {
    private TextView textCustomer, textStaff;
    private ImageView iconCustomer, iconStaff, menu;
    private SearchView searchView;
    private RecyclerView rcvStore;
    private StoreAdapter storeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store);

        textCustomer = findViewById(R.id.text_customer);
        iconCustomer = findViewById(R.id.icon_person);
        textStaff = findViewById(R.id.text_staff);
        iconStaff = findViewById(R.id.icon_staff);
        rcvStore = findViewById(R.id.rcv_store);
        menu = findViewById(R.id.menu);
        searchView = findViewById(R.id.search_view);

        View.OnClickListener onClickCustomer = v -> {
            Intent customerView = new Intent(this, CustomerViewActivity.class);
            startActivity(customerView);
            finish();
        };
        View.OnClickListener onClickStaff = v -> {
            Intent storeView = new Intent(this, StoreActivity.class);
            startActivity(storeView);
            finish();
        };

        textCustomer.setOnClickListener(onClickCustomer);
        iconCustomer.setOnClickListener(onClickCustomer);

        callApiGetListStore();
    }

    private void callApiGetListStore(){
        StoreApi.STORE_API.getListStore().enqueue(new Callback<List<Store>>() {
            @Override
            public void onResponse(Call<List<Store>> call, Response<List<Store>> response) {
                List<Store> listStore = response.body();
                if(!listStore.isEmpty()){
                    storeAdapter = new StoreAdapter(StoreActivity.this, listStore);
                    rcvStore.setLayoutManager(new GridLayoutManager(StoreActivity.this, 1));
                    rcvStore.setAdapter(storeAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Store>> call, Throwable t) {

            }
        });
    }

}