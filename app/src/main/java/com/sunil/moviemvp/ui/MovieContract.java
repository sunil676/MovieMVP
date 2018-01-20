package com.sunil.moviemvp.ui;

import com.sunil.moviemvp.base.BaseContract;
import com.sunil.moviemvp.remote.model.MovieEntity;

import java.util.List;

/**
 * Created by sunil on 20-01-2018.
 */

public class MovieContract {

    public interface Presenter extends BaseContract.Presenter<View> {
        void loadMovie();
    }

    public interface View extends BaseContract.View{
        void onLoadMovieOk(List<MovieEntity> movieList);
        void showLoadErrorMessage(String errorMsg);
        void showEmptyView(boolean isShow);

    }
}
