package com.example.data.remote;

import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface Characters {

    @GET("./collections/get/charactersv2")
    @Headers("Content-Type: application/json")
    void getCharacters();
}
