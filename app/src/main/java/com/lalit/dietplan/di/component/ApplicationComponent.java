

package com.lalit.dietplan.di.component;

import android.app.Application;
import android.content.Context;

import com.lalit.dietplan.MvpApp;
import com.lalit.dietplan.data.DataManager;
import com.lalit.dietplan.di.ApplicationContext;
import com.lalit.dietplan.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(MvpApp app);

//    void inject(SyncService service);

    @ApplicationContext
    Context context();

    Application application();

    DataManager getDataManager();
}