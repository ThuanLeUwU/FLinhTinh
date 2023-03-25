package com.project.flinhtinh;

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

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.project.flinhtinh.activity.CartActivity;
import com.project.flinhtinh.activity.LoginActivity;
import com.project.flinhtinh.activity.OrderHistoryActivity;
import com.project.flinhtinh.activity.ProductDetailActivity;
import com.project.flinhtinh.apdater.CategoryAdapter;
import com.project.flinhtinh.apdater.MainAdapter;
import com.project.flinhtinh.apdater.SliderAdapter;
import com.project.flinhtinh.api.CategoryApi;
import com.project.flinhtinh.api.ProductApi;
import com.project.flinhtinh.model.Category;
import com.project.flinhtinh.model.Product;
import com.project.flinhtinh.listener.OnCategoryListener;
import com.project.flinhtinh.listener.OnProductListener;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements OnProductListener, OnCategoryListener {
    private String userId;
    private RecyclerView rcvProduct, rcvCategory;
    private MainAdapter productAdapter;
    private CategoryAdapter categoryAdapter;
    private TextView textHistory;
    private ImageView imgCart, menu;
    private SearchView searchView;
    private List<Product> listProducts;
    private List<Category> listCates;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;

    SliderView sliderView;
    int[] images = {R.drawable.one,
            R.drawable.two,
            R.drawable.three};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String userId = intent.getStringExtra("USER_ID");
        if(userId == null){
//            this.userId = UUID.randomUUID().toString();
            this.userId = "84f6a9ab-fac8-44df-9549-8c428b91a66a";
        }
        SharedPreferences sharedPreferences = getSharedPreferences("USER", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("USER_ID", this.userId);
        editor.apply();

        sliderView = findViewById(R.id.image_slider);

        SliderAdapter sliderAdapter = new SliderAdapter(images);

        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();

//        textHistory = findViewById(R.id.text_history);
//        imgCart = findViewById(R.id.shopping_cart);
//        menu = findViewById(R.id.menu);
//        searchView = findViewById(R.id.search_view);
        drawerLayout = findViewById(R.id.drawer_layout_home);
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
                    startActivity(new Intent(MainActivity.this, MainActivity.class));
                    return true;
                }

                case R.id.login: {
                    drawerLayout.close();
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    return true;
                }
                case R.id.order_history: {
                    drawerLayout.close();
                    startActivity(new Intent(MainActivity.this, OrderHistoryActivity.class));
                    return true;
                }
            }
            return true;
        });


        rcvProduct = findViewById(R.id.rcv_product);
        rcvCategory = findViewById(R.id.rcv_category);

//        textHistory.setOnClickListener(v -> {
//            Intent historyInput = new Intent(this, HistoryInputActivity.class);
//            startActivity(historyInput);
//        });
//
//        imgCart.setOnClickListener(v -> {
//            Intent cart = new Intent(this, CartActivity.class);
//            startActivity(cart);
//        });
//
//        menu.setOnClickListener(v -> {
//            Intent login = new Intent(this, LoginActivity.class);
//            startActivity(login);
//        });



        callApiProduct();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_nav, menu);
        MenuItem searchItem = menu.findItem(R.id.search_view);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(MainActivity.this, ProductDetailActivity.class);
                intent.putExtra("query", query);
                startActivity(intent);
                finish();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Xử lý khi thay đổi nội dung search query
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }


        if (id == R.id.shopping_cart) {
            startActivity(new Intent(MainActivity.this, CartActivity.class));
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



    private void callApiProduct(){
        ProductApi.PRODUCT_API.getListProduct(2023).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> list = response.body();
                listProducts = list;
                if(!list.isEmpty()){
                    productAdapter = new MainAdapter(list, MainActivity.this);
                    rcvProduct.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
                    rcvProduct.setAdapter(productAdapter);
                }
            }
            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "GET API FAILED", Toast.LENGTH_SHORT).show();
            }
        });
        //Call api Category
        CategoryApi.CATEGORY_API.getListCategory().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                List<Category> listCategory = response.body();
                listCates = listCategory;
                if(!listCategory.isEmpty()) {
                    categoryAdapter = new CategoryAdapter(listCategory, MainActivity.this);
                    rcvCategory.setLayoutManager(new GridLayoutManager(MainActivity.this, 4));
                    rcvCategory.setAdapter(categoryAdapter);
                }
            }
            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "GET API FAILED", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onProductClick(int position) {
        Intent detail = new Intent(MainActivity.this, ProductDetailActivity.class);
        detail.putExtra("PRODUCT_ID", listProducts.get(position).getProductId().toString());
        startActivity(detail);
    }

    @Override
    public void onCategoryClick(int position) {
        UUID categoryId = listCates.get(position).getCategoryId();
        ProductApi.PRODUCT_API.getListProductWithCate(categoryId).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> list = response.body();
                listProducts = list;
                if(!list.isEmpty()){
                    productAdapter.setData(listProducts);
                }
            }
            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "GET API FAILED", Toast.LENGTH_SHORT).show();
            }
        });
    }
}