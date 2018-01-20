package com.sunil.moviemvp.repository;

import com.sunil.moviemvp.local.MovieDAO;
import com.sunil.moviemvp.remote.model.MovieEntity;

import java.util.List;
import java.util.concurrent.Executor;

import io.reactivex.Flowable;

/**
 * Created by sunil on 20-01-2018.
 */

public class LocalRepositoryImpl implements LocalRepository{

    private MovieDAO movieDAO;
    private Executor executor;

    public LocalRepositoryImpl(MovieDAO movieDAO, Executor exec) {
        this.movieDAO = movieDAO;
        executor = exec;
    }

    @Override
    public Flowable<List<MovieEntity>> getMovies() {
        return movieDAO.getMovies();
    }

    @Override
    public void insertMovie(final MovieEntity movieEntity) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                movieDAO.insertMovie(movieEntity);
            }
        });
    }
}
