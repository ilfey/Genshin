package com.example.data.remotely.auth;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Auth {
    @POST("/api/login")
    @Headers({
            "Content-Type: application/json"
    })
    public Call<Object> login(@Body LoginBody body);
}
