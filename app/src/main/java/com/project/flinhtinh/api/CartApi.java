package com.project.flinhtinh.api;

import com.project.flinhtinh.model.Cart;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CartApi {
    CartApi CART_API = new Retrofit.Builder()
            .baseUrl("https://640996b56ecd4f9e18b50c23.mockapi.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CartApi.class);

    @GET("api/v1/cart1/{id}")
    Call<Cart> getCartWithId(@Path("id")String cartId);

    @POST("api/v1/cart1")
    Call<Cart> createCart(@Body Cart cart);

    @PUT("api/v1/cart1/{id}")
    Call<Cart> updateCart(@Path("id")String id, @Body Cart cart);

    @DELETE("api/v1/cart1/{id}")
    Call<Cart> deleteCart(@Path("id")String id);
}
