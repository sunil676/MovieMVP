package com.sunil.moviemvp.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.sunil.moviemvp.remote.model.MovieEntity;

/**
 * Created by sunil on 20-01-2018.
 */

@Database(entities = {MovieEntity.class}, version = 1)
public abstract class MovieDatabase extends RoomDatabase {
    public abstract MovieDAO couponDao();
}