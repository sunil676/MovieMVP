package com.sunil.moviemvp.repository;

import com.sunil.moviemvp.remote.MovieAPI;
import com.sunil.moviemvp.remote.model.AllMovieResponseModel;

import io.reactivex.Observable;

/**
 * Created by sunil on 20-01-2018.
 */

public class RemoteRepositoryImpl implements RemoteRepository{

    private MovieAPI movieAPI;

    public RemoteRepositoryImpl(MovieAPI movieAPI){
        movieAPI = movieAPI;
    }

    @Override
    public Observable<AllMovieResponseModel> getMovie() {
        return movieAPI.getPopularMovie();
    }
}
