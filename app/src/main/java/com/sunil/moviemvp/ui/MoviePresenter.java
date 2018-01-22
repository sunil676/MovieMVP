package com.sunil.moviemvp.ui;

import android.support.annotation.NonNull;
import android.util.Log;

import com.sunil.moviemvp.remote.model.MovieResponseModel;
import com.sunil.moviemvp.remote.model.MovieEntity;
import com.sunil.moviemvp.repository.LocalRepository;
import com.sunil.moviemvp.repository.RemoteRepository;
import com.sunil.moviemvp.utils.Constant;

import java.util.Collections;
import java.util.Comparator;
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
    public void loadMovie(final String movieType) {
        //add observable to CompositeDisposable so that it can be dispose when Presenter is ready to be destroyed
        mCompositeDisposable.add(localRepository.getMovies().subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<MovieEntity>>() {
                    @Override
                    public void accept(List<MovieEntity> movieEntities) throws Exception {

                        if (movieType.equalsIgnoreCase(Constant.TOPRATED)){
                            // sort based on top rated.
                            Collections.sort(movieEntities, new Comparator<MovieEntity>(){
                                public int compare(MovieEntity obj1, MovieEntity obj2) {
                                    // ## Ascending order
                                    return  Double.compare(obj2.getVoteAverage(), obj1.getVoteAverage());
                                     // To compare string values
                                }
                            });
                             //Collections.reverseOrder();
                        }
                        mMovieView.onLoadMovieOk(movieEntities);
                    }
                }));
    }

    public void getMovieFromService(String movieType){
        //add observable to CompositeDisposable so that it can be dispose when Presenter is ready to be destroyed
        //Call retrofit client on background thread and update database with response from service using Room
        mCompositeDisposable.add(remoteRepository.getMovie(movieType).subscribeOn(Schedulers.io())
                .subscribe(new Consumer<MovieResponseModel>() {
                    @Override
                    public void accept(MovieResponseModel allMovieResponseModel) throws Exception {
                        //localRepository.deleteAllCMovies();
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
