package com.example.data.remote;

import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface Dictionary {

    @GET("./collections/get/dict")
    @Headers("Content-Type: application/json")
    DictionaryResponse getDictionary();
}
