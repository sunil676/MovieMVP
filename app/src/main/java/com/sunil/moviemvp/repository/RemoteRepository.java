package com.sunil.moviemvp.repository;

import com.sunil.moviemvp.remote.model.MovieResponseModel;

import io.reactivex.Observable;

/**
 * Created by sunil on 20-01-2018.
 */

public interface RemoteRepository {

    public Observable<MovieResponseModel> getMovie(String movieType);
}
