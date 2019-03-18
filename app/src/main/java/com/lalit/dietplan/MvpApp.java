package com.lalit.dietplan;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.interceptors.HttpLoggingInterceptor;
import com.lalit.dietplan.data.DataManager;
import com.lalit.dietplan.di.component.ApplicationComponent;
import com.lalit.dietplan.di.component.DaggerApplicationComponent;
import com.lalit.dietplan.di.module.ApplicationModule;

import javax.inject.Inject;

public class MvpApp extends Application {

    @Inject
    DataManager mDataManager;

    private ApplicationComponent mApplicationComponent;


    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

        mApplicationComponent.inject(this);


        AndroidNetworking.initialize(getApplicationContext());
        if (BuildConfig.DEBUG) {
            AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY);
        }


    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }


    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }

}
