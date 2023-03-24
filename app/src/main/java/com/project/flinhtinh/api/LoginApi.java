package com.project.flinhtinh.api;

import com.project.flinhtinh.model.Account;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LoginApi {
    LoginApi LOGIN_API = new Retrofit.Builder()
            .baseUrl("https://640996b56ecd4f9e18b50c23.mockapi.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LoginApi.class);

    @GET("api/v1/accounts")
    Call<Account[]> getLoginAccount(@Query("email")String email);

}
