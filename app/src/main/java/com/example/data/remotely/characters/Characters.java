package com.example.data.remotely.characters;

import androidx.annotation.NonNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;

public interface Characters {
    @GET("./api/characters")
    public Call<List<CharactersResponses.Character>> getCharacters();

    @GET("./api/characters")
    public Call<List<CharactersResponses.Character>> getCharacters(@Body @NonNull CharactersRequests.GetCharacters body);

    @GET("./api/characters")
    public Call<CharactersResponses.Character> getCharacter(@Body @NonNull CharactersRequests.GetCharacter body);

    @POST("./api/characters")
    public Call<Object> postCharacter(@Body @NonNull CharactersRequests.PostCharacter body);

    @DELETE("./api/characters")
    public Call<Object> deleteCharacter(@Body @NonNull CharactersRequests.DeleteCharacter body);

    @PATCH("./api/characters")
    public Call<Object> patchCharacter(@Body @NonNull CharactersRequests.PatchCharacter body);

}
