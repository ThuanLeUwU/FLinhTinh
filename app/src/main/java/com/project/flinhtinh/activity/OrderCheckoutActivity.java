package com.project.flinhtinh.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.flinhtinh.R;
import com.project.flinhtinh.apdater.OrderCheckoutAdapter;
import com.project.flinhtinh.api.CartApi;
import com.project.flinhtinh.api.CustomerApi;
import com.project.flinhtinh.api.OrderApi;
import com.project.flinhtinh.model.Cart;
import com.project.flinhtinh.model.Customer;
import com.project.flinhtinh.model.CustomerOrderForm;
import com.project.flinhtinh.model.Order;
import com.project.flinhtinh.model.OrderDetail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderCheckoutActivity extends AppCompatActivity {
    private TextView textHistory, fullName, phone, email, shippingAddress, city, totalPrice;
    private ImageView imgCart, menu;
    private SearchView searchView;
    private RecyclerView recyclerView;
    private OrderCheckoutAdapter orderCheckoutAdapter;
    private Button btnBack, btnOrder;
    private Customer customer;
    private Cart cart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout);
        textHistory = findViewById(R.id.text_history);
        imgCart = findViewById(R.id.shopping_cart);
        recyclerView = findViewById(R.id.rcv_product);
        fullName = findViewById(R.id.tv_full_name);
        phone = findViewById(R.id.tv_phone);
        email = findViewById(R.id.tv_email);
        shippingAddress = findViewById(R.id.tv_shipping_address);
        city = findViewById(R.id.tv_city);
        totalPrice = findViewById(R.id.tv_total_price);
        btnBack = findViewById(R.id.btn_back);
        btnOrder = findViewById(R.id.btn_order);
        menu = findViewById(R.id.menu);
        searchView = findViewById(R.id.search_view);

        textHistory.setOnClickListener(v -> {
            Intent historyInput = new Intent(this, HistoryInputActivity.class);
            startActivity(historyInput);
        });

        SharedPreferences sharedPreferences = getSharedPreferences("USER", MODE_PRIVATE);
        String userId = sharedPreferences.getString("USER_ID", null);
        Log.d("USER_ID", userId);
        Intent intent = getIntent();
        CustomerOrderForm form = (CustomerOrderForm) intent.getSerializableExtra("CUSTOMER_FORM");
        callApiFindCustomerByEmail(form.getEmail());
        btnOrder.setOnClickListener(v -> {
            Intent orderSuccess = new Intent(OrderCheckoutActivity.this, OrderSuccessActivity.class);
            if(customer == null){
                Customer orderCustomer = new Customer(userId,
                        form.getFullName(),
                        form.getPhone(),
                        form.getEmail(),
                        "stress",
                        form.getAddress(),
                        form.getCity(),
                        "Active");
                Order order = new Order(UUID.randomUUID(),
                        orderCustomer,
                        null, null,
                        cart.totalPrice(),
                        new Date(),
                        form.getCity(),
                        form.getAddress(),
                        "SUCCESS");
                List<OrderDetail> listOderDetailList = new ArrayList<>(cart.getCart().values());
                for (OrderDetail orderDetail: listOderDetailList) {
                    callApiCreateOrderDetail(orderDetail);
                }
                callApiCreateCustomer(orderCustomer);
                callApiCreateOrder(order);
            } else {
                Order order = new Order(UUID.randomUUID(),
                        customer,
                        null, null,
                        cart.totalPrice(),
                        new Date(),
                        form.getCity(),
                        form.getAddress(),
                        "SUCCESS");
                List<OrderDetail> listOderDetailList = new ArrayList<>(cart.getCart().values());
                for (OrderDetail orderDetail: listOderDetailList) {
                    callApiCreateOrderDetail(orderDetail);
                }
                callApiCreateOrder(order);
            }
            callApiDeleteCart(userId);
            startActivity(orderSuccess);
        });

        btnBack.setOnClickListener(v -> {
            finish();
        });

        callApiGetCart(userId, form);
    }

    private void callApiGetCart(String userId, CustomerOrderForm form) {
        CartApi.CART_API.getCartWithId(userId).enqueue(new Callback<Cart>() {
            @Override
            public void onResponse(Call<Cart> call, Response<Cart> response) {
                Cart getCart = response.body();
                cart = getCart;
                if (getCart != null) {
                    recyclerView.setLayoutManager(new GridLayoutManager(OrderCheckoutActivity.this, 1));
                    orderCheckoutAdapter = new OrderCheckoutAdapter(OrderCheckoutActivity.this, getCart);
                    recyclerView.setAdapter(orderCheckoutAdapter);

                    fullName.setText("Họ và tên: "+form.getFullName());
                    phone.setText("Sđt: "+form.getPhone());
                    email.setText("Email: "+form.getEmail());
                    shippingAddress.setText("Địa chỉ giao hàng: "+form.getAddress());
                    city.setText("Thành phố: " + form.getCity());
                    totalPrice.setText(String.valueOf(getCart.totalPrice()));
                } else {
                    Log.d("TEST", "GET CART NULL");
                }
            }
            @Override
            public void onFailure(Call<Cart> call, Throwable t) {
                Toast.makeText(OrderCheckoutActivity.this, "GET API FAILED", Toast.LENGTH_SHORT).show();
            }
        });


    }
    private void callApiFindCustomerByEmail(String email){

        CustomerApi.CUSTOMER_API.findCustomerByEmail(email).enqueue(new Callback<Customer[]>() {
            @Override
            public void onResponse(Call<Customer[]> call, Response<Customer[]> response) {
                Log.d("EMAIL", email);
                Customer[] getCustomer = response.body();

                if(getCustomer.length == 1){
                    customer = getCustomer[0];
                }
            }

            @Override
            public void onFailure(Call<Customer[]> call, Throwable t) {
                Log.d("BUG", t.toString());
            }
        });
    }

    private void callApiCreateCustomer(Customer customer){
        CustomerApi.CUSTOMER_API.createCustomer(customer).enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {

            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {

            }
        });
    }

    private void callApiCreateOrder(Order order){
        OrderApi.ORDER_API.createOrder(order).enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                Log.d("CART", "CREATE ORDER SUCCESS");
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                Log.d("CART", "CREATE ORDER FAILED");
            }
        });
    }

    private void callApiCreateOrderDetail(OrderDetail orderDetail){
        OrderApi.ORDER_API.createOrderDetail(orderDetail).enqueue(new Callback<OrderDetail>() {
            @Override
            public void onResponse(Call<OrderDetail> call, Response<OrderDetail> response) {
                Log.d("CART", "CREATE DETAIL SUCCESS");
            }
            @Override
            public void onFailure(Call<OrderDetail> call, Throwable t) {
                Log.d("CART", "FAILED TO CREATE");
            }
        });
    }

    private void callApiDeleteCart(String userId){
        CartApi.CART_API.deleteCart(userId).enqueue(new Callback<Cart>() {
            @Override
            public void onResponse(Call<Cart> call, Response<Cart> response) {
                Log.d("CART", "DELETE SUCCESS");
            }

            @Override
            public void onFailure(Call<Cart> call, Throwable t) {
                Log.d("CART", "FAILED TO DELETE");
            }
        });
    }

}