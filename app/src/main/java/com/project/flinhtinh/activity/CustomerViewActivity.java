package com.project.flinhtinh.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.project.flinhtinh.MainActivity;
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

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer);

//        textStore = findViewById(R.id.text_store);
//        textStaff = findViewById(R.id.text_staff);
//        iconStore = findViewById(R.id.icon_store);
//        iconStaff = findViewById(R.id.icon_staff);
        rcvCustomer = findViewById(R.id.rcv_customer);
//        menu = findViewById(R.id.menu);
//        searchView = findViewById(R.id.search_view);

        View.OnClickListener onClickStore = v -> {
            Intent storeView = new Intent(this, StoreActivity.class);
            startActivity(storeView);
            finish();
        };
//        textStore.setOnClickListener(onClickStore);
//        iconStore.setOnClickListener(onClickStore);

        drawerLayout = findViewById(R.id.drawer_layout_customer);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        navigationView = findViewById(R.id.nav);
//        navigationView.bringToFront();
//        navigationView.setNavigationItemSelectedListener(item -> {
//            switch (item.getItemId()) {
//                case R.id.home: {
//                    drawerLayout.close();
//                    startActivity(new Intent(CustomerViewActivity.this, MainActivity.class));
//                    return true;
//                }
//
//                case R.id.login: {
//                    drawerLayout.close();
//                    startActivity(new Intent(CustomerViewActivity.this, LoginActivity.class));
//                    return true;
//                }
//                case R.id.order_history: {
//                    drawerLayout.close();
//                    startActivity(new Intent(CustomerViewActivity.this, OrderHistoryActivity.class));
//                    return true;
//                }
//            }
//            return true;
//        });


        callApiGetListCustomer();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_admin, menu);
//        MenuItem searchItem = menu.findItem(R.id.icon_person);
//        SearchView searchView = (SearchView) searchItem.getActionView();
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                Intent intent = new Intent(CustomerViewActivity.this, ProductDetailActivity.class);
//                intent.putExtra("query", query);
//                startActivity(intent);
//                finish();
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                // Xử lý khi thay đổi nội dung search query
//                return false;
//            }
//        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }

        if (id == R.id.icon_person) {
            startActivity(new Intent(CustomerViewActivity.this, CustomerViewActivity.class));
        }

        if (id == R.id.icon_store) {
            startActivity(new Intent(CustomerViewActivity.this, StoreActivity.class));
        }

//        if (id == R.id.icon_staff) {
//            startActivity(new Intent(CustomerViewActivity.this, MainActivity.class));
//        }

        if (id == R.id.logout) {
            startActivity(new Intent(CustomerViewActivity.this, MainActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }


    private ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result != null) {
                        //Làm gì đó khi có response trả về
                    } else {
                    }
                }
            }
    );


    private void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Thông báo");
        builder.setMessage("Giỏ hàng trống.");
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
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