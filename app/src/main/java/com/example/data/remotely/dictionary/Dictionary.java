package com.example.data.remotely.dictionary;

import androidx.annotation.NonNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;

public interface Dictionary {

    @GET("./api/dictionary")
    public Call<List<DictionaryResponses.Word>> getDictionary();

    @GET("./api/dictionary")
    public Call<List<DictionaryResponses.Word>> getDictionary(@Body @NonNull DictionaryRequests.GetDictionary body);

    @GET("./api/dictionary")
    public Call<DictionaryResponses.Word> getWord(@Body @NonNull DictionaryRequests.GetWord body);

    @POST("./api/dictionary")
    public Call<Object> postWord(@Body @NonNull DictionaryRequests.GetWord body);

    @DELETE("./api/dictionary")
    public Call<Object> deleteWord(@Body @NonNull DictionaryRequests.DeleteWord body);

    @PATCH("./api/dictionary")
    public Call<Object> patchWord(@Body @NonNull DictionaryRequests.PatchWord body);
}
