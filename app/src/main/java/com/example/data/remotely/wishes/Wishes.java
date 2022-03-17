package com.example.data.remotely.wishes;

import androidx.annotation.NonNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;

public interface Wishes {
    @GET("/api/wishes")
    public Call<List<WishesResponses.Wish>> getWishes();

    @GET("/api/wishes")
    public Call<List<WishesResponses.Wish>> getWishes(@Body @NonNull WishesRequests.GetWishes body);

    @GET("/api/wishes")
    public Call<WishesResponses.Wish> getWish(@Body @NonNull WishesRequests.GetWish body);

    @POST("/api/wishes")
    public Call<Object> postWish(@Body @NonNull WishesRequests.PostWish body);

    @DELETE("/api/wishes")
    public Call<Object> deleteWish(@Body @NonNull WishesRequests.DeleteWish body);

    @PATCH("/api/wishes")
    public Call<Object> patchWish(@Body @NonNull WishesRequests.PatchWish body);
}
