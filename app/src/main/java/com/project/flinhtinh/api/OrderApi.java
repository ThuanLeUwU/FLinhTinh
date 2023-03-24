package com.project.flinhtinh.api;

import com.project.flinhtinh.model.Order;
import com.project.flinhtinh.model.OrderDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface OrderApi {
    OrderApi ORDER_API = new Retrofit.Builder()
            .baseUrl("https://640996b56ecd4f9e18b50c23.mockapi.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OrderApi.class);

    @GET("api/v1/orders")
    Call<List<OrderDetail>> getListOrderHistory();

    @POST("api/v1/orders")
    Call<Order> createOrder(@Body Order order);

    @POST("api/v1/order-detail")
    Call<OrderDetail> createOrderDetail(@Body OrderDetail orderDetail);

    @GET("api/v1/orders/{id}/order-detail")
    Call<List<OrderDetail>> getOrderHistory(@Path("id") String email);
}
