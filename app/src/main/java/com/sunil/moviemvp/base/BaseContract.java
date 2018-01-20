package com.sunil.moviemvp.base;

/**
 * Created by sunil on 20-01-2018.
 */

public class BaseContract{

    public interface Presenter<T>{
        void subscribe();
        void unSubscribe();
        void attachView(T view);
    }

    public interface View {

    }
}
