package com.project.flinhtinh.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.project.flinhtinh.MainActivity;
import com.project.flinhtinh.R;
import com.project.flinhtinh.apdater.OrderHistoryAdapter;
import com.project.flinhtinh.apdater.StoreAdapter;
import com.project.flinhtinh.api.OrderApi;
import com.project.flinhtinh.api.StoreApi;
import com.project.flinhtinh.model.OrderDetail;
import com.project.flinhtinh.model.Store;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderHistoryActivity extends AppCompatActivity {
    private TextView textHistory;
    private ImageView imgCart;
    private Button btnBack;
    private RecyclerView rcvOrderHistory;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);

//        textHistory = findViewById(R.id.text_history);
//        imgCart = findViewById(R.id.shopping_cart);

        drawerLayout = findViewById(R.id.drawer_layout_history);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView = findViewById(R.id.nav);
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home: {
                    drawerLayout.close();
                    startActivity(new Intent(OrderHistoryActivity.this, MainActivity.class));
                    return true;
                }
                case R.id.login: {
                    drawerLayout.close();
                    startActivity(new Intent( OrderHistoryActivity.this, LoginActivity.class));
                    return true;
                }
                case R.id.order_history: {
                    drawerLayout.close();
                    startActivity(new Intent(OrderHistoryActivity.this, OrderHistoryActivity.class));
                    return true;
                }
            }
            return true;
        });

        btnBack = findViewById(R.id.btn_back);
        rcvOrderHistory = findViewById(R.id.rcv_order_history);

        btnBack.setOnClickListener(v -> {
            finish();
        });

//        imgCart.setOnClickListener(v -> {
//            Intent cart = new Intent(this, CartActivity.class);
//            startActivity(cart);
//        });
        callApiOrderHistory();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_nav, menu);
//        MenuItem searchItem = menu.findItem(R.id.search);
//        SearchView searchView = (SearchView) searchItem.getActionView();
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                Intent intent = new Intent(OrderHistoryActivity.this, ProductDetailActivity.class);
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


        if (id == R.id.shopping_cart) {
            startActivity(new Intent(OrderHistoryActivity.this, CartActivity.class));
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
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Thông báo");
        builder.setMessage("Giỏ hàng trống.");
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        androidx.appcompat.app.AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void callApiOrderHistory() {
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        OrderApi.ORDER_API.getOrderHistory().enqueue(new Callback<List<OrderDetail>>() {
            @Override
            public void onResponse(Call<List<OrderDetail>> call, Response<List<OrderDetail>> response) {
                List<OrderDetail> listOrderHistory = response.body();
                if(listOrderHistory != null){
                    if(!listOrderHistory.isEmpty()){
                        Toast.makeText(OrderHistoryActivity.this, "GET API SUCCESS", Toast.LENGTH_SHORT).show();
                        OrderHistoryAdapter orderHistoryAdapter = new OrderHistoryAdapter(listOrderHistory);
                        rcvOrderHistory.setLayoutManager(new GridLayoutManager(OrderHistoryActivity.this, 1));
                        rcvOrderHistory.setAdapter(orderHistoryAdapter);
                    }
                }
            }
            @Override
            public void onFailure(Call<List<OrderDetail>> call, Throwable t) {
                Toast.makeText(OrderHistoryActivity.this, "GET API FAILED", Toast.LENGTH_SHORT).show();
            }
        });
    }


}