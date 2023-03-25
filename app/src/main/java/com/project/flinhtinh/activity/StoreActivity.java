package com.project.flinhtinh.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.flinhtinh.MainActivity;
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

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store);


        rcvStore = findViewById(R.id.rcv_store);


        drawerLayout = findViewById(R.id.drawer_layout_store);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        callApiGetListStore();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_admin, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }

        if (id == R.id.icon_person) {
            startActivity(new Intent(StoreActivity.this, CustomerViewActivity.class));
        }

        if (id == R.id.icon_store) {
            startActivity(new Intent(StoreActivity.this, StoreActivity.class));
        }


        if (id == R.id.logout) {
            startActivity(new Intent(StoreActivity.this, MainActivity.class));
        }
        return super.onOptionsItemSelected(item);
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