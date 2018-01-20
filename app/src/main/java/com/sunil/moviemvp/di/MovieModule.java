package com.sunil.moviemvp.di;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.sunil.moviemvp.local.MovieDAO;
import com.sunil.moviemvp.local.MovieDatabase;
import com.sunil.moviemvp.remote.MovieAPI;
import com.sunil.moviemvp.repository.LocalRepository;
import com.sunil.moviemvp.repository.LocalRepositoryImpl;
import com.sunil.moviemvp.repository.RemoteRepository;
import com.sunil.moviemvp.repository.RemoteRepositoryImpl;
import com.sunil.moviemvp.ui.MoviePresenter;

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
    public MovieDAO getMovieDAO(MovieDatabase movieDatabase){
        return movieDatabase.movieDao();
    }

    @MovieScope
    @Provides
    public MovieDatabase getMovieDatabase(){
        return Room.databaseBuilder(context.getApplicationContext(),
                MovieDatabase.class, "movies.db")
                .build();
    }
    @MovieScope
    @Provides
    public LocalRepository getLocalRepo(MovieDAO movieDAO, Executor exec){
        return new LocalRepositoryImpl(movieDAO, exec);
    }
    @MovieScope
    @Provides @Named("activity")
    public CompositeDisposable getCompositeDisposable(){
        return new CompositeDisposable();
    }

    @MovieScope
    @Provides
    public RemoteRepository getRemoteRepo(MovieAPI movieAPI){
        return new RemoteRepositoryImpl(movieAPI);
    }

    @MovieScope
    @Provides
    public MoviePresenter getMoviePresenter(LocalRepository localRepository, RemoteRepository remoteRepository){
        return new MoviePresenter(localRepository, remoteRepository);
    }
}
