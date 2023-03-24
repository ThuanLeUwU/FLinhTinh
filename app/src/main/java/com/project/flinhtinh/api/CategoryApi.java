package com.project.flinhtinh.api;

import com.project.flinhtinh.model.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface CategoryApi {
    CategoryApi CATEGORY_API = new Retrofit.Builder()
            .baseUrl("https://640996b56ecd4f9e18b50c23.mockapi.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CategoryApi.class);



    @GET("api/v1/category")
    Call<List<Category>> getListCategory();
}
