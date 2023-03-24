package com.project.flinhtinh.api;

import com.project.flinhtinh.model.Customer;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CustomerApi {

    CustomerApi CUSTOMER_API = new Retrofit.Builder()
            .baseUrl("https://640996b56ecd4f9e18b50c23.mockapi.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CustomerApi.class);


    @GET("api/v1/customers")
    Call<List<Customer>> getListCustomer();

    @GET("api/v1/customers")
    Call<Customer[]> findCustomerByEmail(@Query("email") String email);

    @POST("api/v1/customers")
    Call<Customer> createCustomer(@Body Customer customer);


}
