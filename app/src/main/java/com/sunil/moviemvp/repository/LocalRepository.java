package com.sunil.moviemvp.repository;

import com.sunil.moviemvp.remote.model.MovieEntity;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by sunil on 20-01-2018.
 */

public interface LocalRepository {
    public Flowable<List<MovieEntity>> getMovies();
}
