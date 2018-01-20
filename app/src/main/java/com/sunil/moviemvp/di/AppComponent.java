package com.sunil.moviemvp.di;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by sunil on 20-01-2018.
 */

@Singleton
@Component(modules={AppModule.class})
public interface AppComponent {
    MovieComponent getMovieComponent(MovieModule movieModule);
}
