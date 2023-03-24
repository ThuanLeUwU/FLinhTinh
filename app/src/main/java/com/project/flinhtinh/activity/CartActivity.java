package com.project.flinhtinh.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
import com.project.flinhtinh.apdater.CartAdapter;
import com.project.flinhtinh.api.CartApi;
import com.project.flinhtinh.listener.OnButtonClickListener;
import com.project.flinhtinh.model.Cart;
import com.project.flinhtinh.model.OrderDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity implements OnButtonClickListener {
    private CartAdapter cartAdapter;
    private TextView textHistory;
    private ImageView imgCart;
    private Button btnBack, btnCheckout;
    private RecyclerView recyclerView;
    private Cart cart;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);

//        textHistory = findViewById(R.id.text_history);
//        imgCart = findViewById(R.id.shopping_cart);

        drawerLayout = findViewById(R.id.drawer_layout_cart);
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
                    startActivity(new Intent(CartActivity.this, MainActivity.class));
                    return true;
                }
                case R.id.login: {
                    drawerLayout.close();
                    startActivity(new Intent( CartActivity.this, LoginActivity.class));
                    return true;
                }
                case R.id.order_history: {
                    drawerLayout.close();
                    startActivity(new Intent(CartActivity.this, OrderHistoryActivity.class));
                    return true;
                }
            }
            return true;
        });

        btnBack = findViewById(R.id.btn_back);
        btnCheckout = findViewById(R.id.btn_order);
        recyclerView = findViewById(R.id.rcv_cart);
        SharedPreferences sharedPreferences = getSharedPreferences("USER", MODE_PRIVATE);
        String userId = sharedPreferences.getString("USER_ID", null);
        Log.d("USER_ID", userId);
        btnBack.setOnClickListener(v -> {
            finish();
        });

        btnCheckout.setOnClickListener(v -> {
            Intent orderForm = new Intent(this, CustomerOrderFormActivity.class);
            startActivity(orderForm);
        });

//        textHistory.setOnClickListener(v -> {
//            Intent historyInput = new Intent(this, HistoryInputActivity.class);
//            startActivity(historyInput);
//        });

//        imgCart.setOnClickListener(v -> {
//
//        });
        callApiGetCart(userId);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_nav, menu);
//        MenuItem searchItem = menu.findItem(R.id.search);
//        SearchView searchView = (SearchView) searchItem.getActionView();
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                Intent intent = new Intent(CartActivity.this, ProductDetailActivity.class);
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
//        return super.onCreateOptionsMenu(menu);
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }


        if (id == R.id.shopping_cart) {
            startActivity(new Intent(CartActivity.this, CartActivity.class));
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

    private void callApiUpdateCartById(String cartId, Cart cart) {
        CartApi.CART_API.updateCart(cartId, cart).enqueue(new Callback<Cart>() {
            @Override
            public void onResponse(Call<Cart> call, Response<Cart> response) {
                if (response.isSuccessful()) {
                    Log.d("TEST", "UPDATE");
                }
            }
            @Override
            public void onFailure(Call<Cart> call, Throwable t) {
                Log.d("TEST", "CANT UPDATE");
            }
        });
    }

    private void callApiGetCart(String userId) {
        CartApi.CART_API.getCartWithId(userId).enqueue(new Callback<Cart>() {
            @Override
            public void onResponse(Call<Cart> call, Response<Cart> response) {
                Cart getCart = response.body();
                cart = getCart;
                if (getCart != null) {
                    recyclerView.setLayoutManager(new GridLayoutManager(CartActivity.this, 1));
                    cartAdapter = new CartAdapter(CartActivity.this, getCart, CartActivity.this);
                    recyclerView.setAdapter(cartAdapter);
                } else {
                    Log.d("TEST", "GET CART NULL");
                }
            }
            @Override
            public void onFailure(Call<Cart> call, Throwable t) {
                Toast.makeText(CartActivity.this, "GET API FAILED", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onButtonClick(int position, String buttonName) {
        SharedPreferences sharedPreferences = getSharedPreferences("USER", MODE_PRIVATE);
        String userId = sharedPreferences.getString("USER_ID", null);
        List<OrderDetail> listOrderDetail = new ArrayList<>(cart.getCart().values());
        UUID productId = listOrderDetail.get(position).getProduct().getProductId();
        if (buttonName.equalsIgnoreCase("INCREASE")) {
            cart.editItem(productId, cart.getCart().get(productId).getQuantity() + 1);
            callApiUpdateCartById(userId, cart);
            cartAdapter.setData(cart);
        }
        if (buttonName.equalsIgnoreCase("DECREASE")) {
            cart.editItem(productId, cart.getCart().get(productId).getQuantity() - 1);
            callApiUpdateCartById(userId, cart);
            cartAdapter.setData(cart);
        }
        if (buttonName.equalsIgnoreCase("REMOVE")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Do you want to delete " + listOrderDetail.get(position).getProduct().getName() + "?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    cart.removeItem(productId);
                    callApiUpdateCartById(userId, cart);
                    cartAdapter.setData(cart);
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
}