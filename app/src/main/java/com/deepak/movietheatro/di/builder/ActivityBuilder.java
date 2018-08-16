package com.deepak.movietheatro.di.builder;

import com.deepak.movietheatro.ui.splash.SplashActivity;
import com.deepak.movietheatro.ui.splash.SplashActivityModule;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = SplashActivityModule.class)
    abstract SplashActivity bindSplashActivity();
}
