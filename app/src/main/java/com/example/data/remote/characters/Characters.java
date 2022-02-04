package com.example.data.remote.characters;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface Characters {

    @GET("./collections/get/charactersv2?token=a4191046104f8f3674f788e804c2d0")
    @Headers({
        "Content-Type: application/json",
        "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/98.0.4758.80 Safari/537.36"
    })
    public Call<CharactersResponse> getCharacters();
}
