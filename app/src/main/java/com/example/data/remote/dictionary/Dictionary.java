package com.example.data.remote.dictionary;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface Dictionary {

    @GET("./collections/get/dict?token=a4191046104f8f3674f788e804c2d0")
    @Headers({
        "Content-Type: application/json",
        "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/98.0.4758.80 Safari/537.36"
    })
    public Call<DictionaryResponse> getDictionary();
}