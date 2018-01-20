package com.sunil.moviemvp.di;

import com.sunil.moviemvp.remote.MovieAPI;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sunil on 20-01-2018.
 */

@Module
public class AppModule {
    public static final String BASE_URL = "http://api.themoviedb.org/3";

    @Singleton
    @Provides
    public Executor getExecutor(){
        return  Executors.newFixedThreadPool(2);
    }

    @Singleton
    @Provides
    public Retrofit getRemoteClient(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
    @Singleton
    @Provides
    public MovieAPI getMovieClient(Retrofit retrofit){
        return retrofit.create(MovieAPI.class);
    }
}