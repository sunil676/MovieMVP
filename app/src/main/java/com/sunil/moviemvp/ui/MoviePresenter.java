package com.sunil.moviemvp.ui;

import android.support.annotation.NonNull;
import android.util.Log;

import com.sunil.moviemvp.remote.model.AllMovieResponseModel;
import com.sunil.moviemvp.remote.model.MovieEntity;
import com.sunil.moviemvp.repository.LocalRepository;
import com.sunil.moviemvp.repository.RemoteRepository;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
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

    }

    @Override
    public void unSubscribe() {
        mCompositeDisposable.clear();
    }

    @Override
    public void attachView(MovieContract.View view) {
        mMovieView = view;
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
    }

    @Override
    public void loadMovie() {
        //add observable to CompositeDisposable so that it can be dispose when Presenter is ready to be destroyed
        //Call retrofit client on background thread and update database with response from service using Room
        mCompositeDisposable.add(Observable.just(1)
                .subscribeOn(Schedulers.computation())
                .flatMap(new Function<Integer, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Integer integer) throws Exception {
                        return remoteRepository.getMovie();
                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<AllMovieResponseModel>() {
                    @Override
                    public void accept(AllMovieResponseModel allMovieResponseModel) throws Exception {
                        for(MovieEntity movieEntity : allMovieResponseModel.getResults()){
                            //database update
                            localRepository.insertCoupon(movieEntity);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("MainActivity", "exception getting coupons", throwable);


                    }}));
    }
}
