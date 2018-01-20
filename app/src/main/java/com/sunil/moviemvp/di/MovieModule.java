package com.sunil.moviemvp.di;

import android.arch.persistence.room.Room;
import android.content.Context;

import java.util.concurrent.Executor;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by sunil on 20-01-2018.
 */

@Module
public class MovieModule {

    private Context context;

    public MovieModule(Context ctx){
        context = ctx;
    }
    @MovieScope
    @Provides
    public CouponDAO getCouponDAO(CouponDatabase couponDatabase){
        return couponDatabase.couponDao();
    }

    @MovieScope
    @Provides
    public CouponDatabase getCouponDatabase(){
        return Room.databaseBuilder(context.getApplicationContext(),
                CouponDatabase.class, "coupons.db")
                .build();
    }
    @MovieScope
    @Provides
    public LocalRepository getLocalRepo(CouponDAO couponDAO, Executor exec){
        return new LocalRepositoryImpl(couponDAO, exec);
    }
    @MovieScope
    @Provides @Named("activity")
    public CompositeDisposable getCompositeDisposable(){
        return new CompositeDisposable();
    }
    @MovieScope
    @Provides @Named("vm")
    public CompositeDisposable getVMCompositeDisposable(){
        return new CompositeDisposable();
    }
    @MovieScope
    @Provides
    public RemoteRepository getRemoteRepo(CouponAPI cpnClient){
        return new RemoteRepositoryImpl(cpnClient);
    }
}
