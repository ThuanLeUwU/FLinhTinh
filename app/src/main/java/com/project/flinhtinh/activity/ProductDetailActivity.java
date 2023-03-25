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

import com.project.flinhtinh.R;
import com.project.flinhtinh.api.CartApi;
import com.project.flinhtinh.api.ProductApi;
import com.project.flinhtinh.model.Cart;
import com.project.flinhtinh.model.OrderDetail;
import com.project.flinhtinh.model.Product;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailActivity extends AppCompatActivity {
    ImageView productImg;
    TextView productName, productDescription, productPrice, productQuantity;
    Button btnAddToCart, btnCancel, btnPlus, btnSubtract;
    Product product;
    Cart cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail);
        btnCancel = findViewById(R.id.btn_cancel);
        btnAddToCart = findViewById(R.id.btn_add_cart);
        btnPlus = findViewById(R.id.btn_plus);
        btnSubtract = findViewById(R.id.btn_subtract);
        productImg = findViewById(R.id.product_image);
        productQuantity = findViewById(R.id.product_quantity);
        productName = findViewById(R.id.product_name);
        productDescription = findViewById(R.id.product_description);
        productPrice = findViewById(R.id.product_price);

        SharedPreferences sharedPreferences = getSharedPreferences("USER", MODE_PRIVATE);
        String userId = sharedPreferences.getString("USER_ID", null);
        Log.d("USER_ID", userId);
        callApiGetCart(userId);

        btnAddToCart.setOnClickListener(v -> {
            //Get current User ID
            int quantity = Integer.parseInt((String) productQuantity.getText());
            double price = Double.parseDouble((String) productPrice.getText());

            if (!cart.getCart().containsKey(product.getProductId())) {
                cart.addItem(product.getProductId(), new OrderDetail("string", null, product, quantity, price, "ACTIVE"));
                Log.d("TEST", "Don't have the item");
            } else {
                cart.editItem(product.getProductId(), quantity);
                Log.d("TEST", "Have the item");
            }

            callApiUpdateCartById(userId, cart);
        });

        btnSubtract.setOnClickListener(v -> {
            int quantity = Integer.parseInt(productQuantity.getText().toString());
            if (quantity > 1) {
                int newValue = quantity - 1;
                productQuantity.setText(String.valueOf(newValue));
            }
        });

        btnPlus.setOnClickListener(v -> {
            int quantity = Integer.parseInt(productQuantity.getText().toString());
            int newValue = quantity + 1;
            productQuantity.setText(String.valueOf(newValue));
        });


        btnCancel.setOnClickListener(v -> {
            finish();
        });

        callApiProductDetail();

    }

    private void callApiProductDetail() {
        Intent intent = getIntent();
        String productId = intent.getStringExtra("PRODUCT_ID");
        ProductApi.PRODUCT_API.getProductDetail(productId).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                Product getProduct = response.body();
                product = getProduct;
                if (getProduct != null) {
                    Picasso.get().load(getProduct.getImage()).into(productImg);
                    productName.setText(getProduct.getName());
                    productDescription.setText(getProduct.getDescription());
                    productPrice.setText(String.valueOf(getProduct.getPrice()));
                }
                Toast.makeText(ProductDetailActivity.this, "GET API SUCCESS", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Toast.makeText(ProductDetailActivity.this, "GET API FAILED", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void callApiGetCart(String cartId) {
        CartApi.CART_API.getCartWithId(cartId).enqueue(new Callback<Cart>() {
            @Override
            public void onResponse(Call<Cart> call, Response<Cart> response) {
                Cart getCart = response.body();
                if (getCart != null) {
                    cart = getCart;
                    Log.d("TEST", "CART NOT NULL");
                } else {
                    Log.d("TEST", "CART NULL");
                    cart = new Cart(cartId, new HashMap<>());
                    callApiCreateCart(cart);
                }
            }

            @Override
            public void onFailure(Call<Cart> call, Throwable t) {
                Toast.makeText(ProductDetailActivity.this, "GET API FAILED", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void callApiCreateCart(Cart cart) {
        Log.d("TEST", "CREATE CART");
        CartApi.CART_API.createCart(cart).enqueue(new Callback<Cart>() {
            @Override
            public void onResponse(Call<Cart> call, Response<Cart> response) {
            }

            @Override
            public void onFailure(Call<Cart> call, Throwable t) {
            }
        });
    }

    private void callApiUpdateCartById(String cartId, Cart cart) {
        CartApi.CART_API.updateCart(cartId, cart).enqueue(new Callback<Cart>() {
            @Override
            public void onResponse(Call<Cart> call, Response<Cart> response) {
                if (response.isSuccessful()) {
                    Log.d("TEST", "UPDATE");
                    Toast.makeText(ProductDetailActivity.this, "UPDATE SUCCESS", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Cart> call, Throwable t) {
                Log.d("TEST", "CANT UPDATE");
                Toast.makeText(ProductDetailActivity.this, "CANT UPDATE", Toast.LENGTH_SHORT).show();
            }
        });
    }

}