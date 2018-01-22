package com.sunil.moviemvp.repository;

import com.sunil.moviemvp.remote.MovieAPI;
import com.sunil.moviemvp.remote.model.MovieResponseModel;
import com.sunil.moviemvp.utils.Constant;

import io.reactivex.Observable;

/**
 * Created by sunil on 20-01-2018.
 */

public class RemoteRepositoryImpl implements RemoteRepository{

    private MovieAPI movieAPI;

    public RemoteRepositoryImpl(MovieAPI movieAPI){
        this.movieAPI = movieAPI;
    }

    @Override
    public Observable<MovieResponseModel> getMovie(String movieType) {
        if (movieType.equals(Constant.POPULAR)) {
            return movieAPI.getPopularMovie();
        }/*else if (movieType.equals(Constant.TOPRATED)){
            return movieAPI.getTopRatedMovie();
        }*/else {
            return movieAPI.getPopularMovie();
        }
    }
}
