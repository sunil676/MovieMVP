package com.sunil.moviemvp.ui;

import android.support.annotation.NonNull;
import android.util.Log;

import com.sunil.moviemvp.remote.model.AllMovieResponseModel;
import com.sunil.moviemvp.remote.model.MovieEntity;
import com.sunil.moviemvp.repository.LocalRepository;
import com.sunil.moviemvp.repository.RemoteRepository;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by sunil on 20-01-2018.
 */

public class MoviePresenter implements MovieContract.Presenter {

    @NonNull
    private MovieContract.View mMovieView;
    private CompositeDisposable mCompositeDisposable;

    private LocalRepository localRepository;
    private RemoteRepository remoteRepository;


    public MoviePresenter(LocalRepository localRepo, RemoteRepository remoteRepo){
        localRepository = localRepo;
        remoteRepository = remoteRepo;
    }

    @Override
    public void subscribe() {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
    }

    @Override
    public void unSubscribe() {
        mCompositeDisposable.clear();
    }

    @Override
    public void attachView(MovieContract.View view) {
        mMovieView = view;
    }

    @Override
    public void loadMovie() {
        //add observable to CompositeDisposable so that it can be dispose when Presenter is ready to be destroyed
        mCompositeDisposable.add(localRepository.getMovies().subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<MovieEntity>>() {
                    @Override
                    public void accept(List<MovieEntity> movieEntities) throws Exception {
                        mMovieView.onLoadMovieOk(movieEntities);
                    }
                }));
    }

    public void getMovieFromService(){
        //add observable to CompositeDisposable so that it can be dispose when Presenter is ready to be destroyed
        //Call retrofit client on background thread and update database with response from service using Room
        mCompositeDisposable.add(remoteRepository.getMovie().subscribeOn(Schedulers.io())
                .subscribe(new Consumer<AllMovieResponseModel>() {
                    @Override
                    public void accept(AllMovieResponseModel allMovieResponseModel) throws Exception {
                        for (MovieEntity movieEntity : allMovieResponseModel.getResults()) {
                            //database update
                            localRepository.insertMovie(movieEntity);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("MainActivity", "exception getting movies", throwable);
                    }
                }));
    }
}
