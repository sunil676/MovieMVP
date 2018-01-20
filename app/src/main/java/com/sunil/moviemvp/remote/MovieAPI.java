package com.sunil.moviemvp.remote;

import com.sunil.moviemvp.remote.model.AllMovieResponseModel;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by sunil on 20-01-2018.
 */

public interface MovieAPI {
    @GET("/movie/popular?page=4&language=en&api_key=c9a058c168b2ee5563bada0f30c7b0e8/")
    Observable<AllMovieResponseModel> getPopularMovie();

    @GET("/movie/top_rated?page=1&language=en&api_key=c9a058c168b2ee5563bada0f30c7b0e8")
    Observable<AllMovieResponseModel> getTopRatedMovie();

}
