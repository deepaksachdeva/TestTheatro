package com.deepak.movietheatro.ui.splash;

import dagger.Module;
import dagger.Provides;

/**
 * Module of SplashActivity
 * Created by deepak sachdeva on 13/08/17.
 *
 * version 1.0
 */
@Module
public class SplashActivityModule {
    @Provides
    SplashViewModel provideSplashViewModel() {
        return new SplashViewModel();
    }
}