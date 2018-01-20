package com.sunil.moviemvp.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.sunil.moviemvp.remote.model.MovieEntity;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by sunil on 20-01-2018.
 */
@Dao
public interface MovieDAO {

    @Query("SELECT * FROM MovieEntity")
    Flowable<List<MovieEntity>> getMovies();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovie(MovieEntity coupon);


   /* @Query("SELECT * FROM Movies WHERE store = :storeIn ")
    Maybe<MovieEntity> getCouponByStore(String storeIn);

    @Query("SELECT * FROM Movies LIMIT 1")
    Single<MovieEntity> getOneCoupon();



    @Query("DELETE FROM CouponEntity")
    void deleteAllCoupons();*/
}
