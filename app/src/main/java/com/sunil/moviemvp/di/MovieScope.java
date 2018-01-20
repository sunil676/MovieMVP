package com.sunil.moviemvp.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by sunil on 20-01-2018.
 */

@Scope
@Retention(value= RetentionPolicy.RUNTIME)
public @interface MovieScope {
}
