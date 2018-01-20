package com.sunil.moviemvp.di;

import com.sunil.moviemvp.MainActivity;

import dagger.Subcomponent;

/**
 * Created by sunil on 20-01-2018.
 */

@MovieScope
@Subcomponent(modules = MovieModule.class)
public interface MovieComponent {
    void inject(MainActivity mainActivity);
}
