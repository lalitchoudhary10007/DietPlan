

package com.lalit.dietplan.di.module;

import android.app.Application;
import android.content.Context;


import com.lalit.dietplan.BuildConfig;
import com.lalit.dietplan.R;
import com.lalit.dietplan.data.AppDataManager;
import com.lalit.dietplan.data.DataManager;
import com.lalit.dietplan.data.Network.ApiHeader;
import com.lalit.dietplan.data.Network.ApiHelper;
import com.lalit.dietplan.data.Network.AppApiHelper;
import com.lalit.dietplan.data.db.AppDbHelper;
import com.lalit.dietplan.data.db.DbHelper;
import com.lalit.dietplan.data.prefs.AppPreferencesHelper;
import com.lalit.dietplan.data.prefs.PreferencesHelper;
import com.lalit.dietplan.di.ApiInfo;
import com.lalit.dietplan.di.ApplicationContext;
import com.lalit.dietplan.di.DatabaseInfo;
import com.lalit.dietplan.di.PreferenceInfo;
import com.lalit.dietplan.utils.AppConstants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @ApiInfo
    String provideApiKey() {
        return BuildConfig.API_KEY;
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

    @Provides
    @Singleton
    ApiHeader.ProtectedApiHeader provideProtectedApiHeader(@ApiInfo String apiKey,
                                                           PreferencesHelper preferencesHelper) {
        return new ApiHeader.ProtectedApiHeader(
                apiKey,
                (long) 123,
               ""
                );
    }


}
