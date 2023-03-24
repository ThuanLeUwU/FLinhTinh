package com.project.flinhtinh.api;

import com.project.flinhtinh.model.OTP;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface OtpApi {
    OtpApi OTP_API = new Retrofit.Builder()
            .baseUrl("https://640996b56ecd4f9e18b50c23.mockapi.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OtpApi.class);

    @POST("api/v1/otp")
    Call<OTP> createOtp(@Body OTP otp);

    @GET("api/v1/otp")
    Call<OTP[]> getOtp(@Query("email") String email);

    @PUT("api/v1/otp/{id}")
    Call<OTP> updateOtpWithEmail(@Path("id") String id, @Body OTP otp);
}
