package com.sunil.moviemvp.local;

import android.arch.persistence.room.Query;

import com.sunil.moviemvp.remote.model.MovieEntity;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by sunil on 20-01-2018.
 */

public interface MovieDAO {

    @Query("SELECT * FROM MovieEntity")
    Flowable<List<MovieEntity>> getMovies();

   /* @Query("SELECT * FROM Movies WHERE store = :storeIn ")
    Maybe<MovieEntity> getCouponByStore(String storeIn);

    @Query("SELECT * FROM Movies LIMIT 1")
    Single<MovieEntity> getOneCoupon();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCoupon(MovieEntity coupon);


    @Query("DELETE FROM CouponEntity")
    void deleteAllCoupons();*/
}
