package com.deepak.movietheatro.di.component;

import android.app.Application;

import com.deepak.movietheatro.MovieTheatroApp;
import com.deepak.movietheatro.di.builder.ActivityBuilder;
import com.deepak.movietheatro.di.module.AppModule;
import javax.inject.Singleton;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class, ActivityBuilder.class})
public interface AppComponent {
    void inject(MovieTheatroApp app);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }
}
