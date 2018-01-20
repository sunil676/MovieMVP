package com.sunil.moviemvp.repository;

import com.sunil.moviemvp.remote.model.AllMovieResponseModel;

import io.reactivex.Observable;

/**
 * Created by sunil on 20-01-2018.
 */

public interface RemoteRepository {

    public Observable<AllMovieResponseModel> getMovie();
}
