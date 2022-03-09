package com.example.adapters;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.ListPreloader;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.integration.recyclerview.RecyclerToListViewScrollListener;

public final class RecyclerViewPreloader<T> extends RecyclerView.OnScrollListener {

    private final RecyclerToListViewScrollListener recyclerScrollListener;

    public RecyclerViewPreloader(
            @NonNull Activity activity,
            @NonNull ListPreloader.PreloadModelProvider<T> preloadModelProvider,
            @NonNull ListPreloader.PreloadSizeProvider<T> preloadDimensionProvider,
            int maxPreload) {
        this(Glide.with(activity), preloadModelProvider, preloadDimensionProvider, maxPreload);
    }

    public RecyclerViewPreloader(
            @NonNull FragmentActivity fragmentActivity,
            @NonNull ListPreloader.PreloadModelProvider<T> preloadModelProvider,
            @NonNull ListPreloader.PreloadSizeProvider<T> preloadDimensionProvider,
            int maxPreload) {
        this(Glide.with(fragmentActivity), preloadModelProvider, preloadDimensionProvider, maxPreload);
    }

    public RecyclerViewPreloader(
            @NonNull Fragment fragment,
            @NonNull ListPreloader.PreloadModelProvider<T> preloadModelProvider,
            @NonNull ListPreloader.PreloadSizeProvider<T> preloadDimensionProvider,
            int maxPreload) {
        this(Glide.with(fragment), preloadModelProvider, preloadDimensionProvider, maxPreload);
    }

    @Deprecated
    public RecyclerViewPreloader(
            @NonNull android.app.Fragment fragment,
            @NonNull ListPreloader.PreloadModelProvider<T> preloadModelProvider,
            @NonNull ListPreloader.PreloadSizeProvider<T> preloadDimensionProvider,
            int maxPreload) {
        this(Glide.with(fragment), preloadModelProvider, preloadDimensionProvider, maxPreload);
    }

    public RecyclerViewPreloader(
            @NonNull RequestManager requestManager,
            @NonNull ListPreloader.PreloadModelProvider<T> preloadModelProvider,
            @NonNull ListPreloader.PreloadSizeProvider<T> preloadDimensionProvider,
            int maxPreload) {

        ListPreloader<T> listPreloader = new ListPreloader<>(requestManager, preloadModelProvider, preloadDimensionProvider, maxPreload);

        recyclerScrollListener = new RecyclerToListViewScrollListener(listPreloader);
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        recyclerScrollListener.onScrolled(recyclerView, dx, dy);
    }
}
