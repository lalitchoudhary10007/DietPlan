

package com.lalit.dietplan.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;


import com.lalit.dietplan.di.ActivityContext;
import com.lalit.dietplan.di.PerActivity;
import com.lalit.dietplan.ui.Main.MainMvpPresenter;
import com.lalit.dietplan.ui.Main.MainMvpView;
import com.lalit.dietplan.ui.Main.MainPresenter;
import com.lalit.dietplan.utils.rx.AppSchedulerProvider;
import com.lalit.dietplan.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;


@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    MainMvpPresenter<MainMvpView> provideMainPresenter(MainPresenter<MainMvpView> presenter) {
        return presenter;
    }

}
