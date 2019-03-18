package com.lalit.dietplan.data;

import android.content.Context;

import com.lalit.dietplan.data.Network.ApiHelper;
import com.lalit.dietplan.data.db.DbHelper;
import com.lalit.dietplan.data.prefs.PreferencesHelper;
import com.lalit.dietplan.di.ApplicationContext;

import org.json.JSONObject;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class AppDataManager implements DataManager {

    private static final String TAG = "AppDataManager";

    private final Context mContext;
    private final DbHelper mDbHelper;
    private final PreferencesHelper mPreferencesHelper;
    private final ApiHelper mApiHelper;


    @Inject
    public AppDataManager(@ApplicationContext Context context,
                          DbHelper dbHelper,
                          PreferencesHelper preferencesHelper,
                          ApiHelper apiHelper) {
        mContext = context;
        mDbHelper = dbHelper;
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
    }


    @Override
    public Single<JSONObject> getDietData() {
        return mApiHelper.getDietData();
    }

    @Override
    public boolean GetRemindervalue() {
        return mPreferencesHelper.GetRemindervalue();
    }

    @Override
    public void SetReminderValue(Boolean b) {
         mPreferencesHelper.SetReminderValue(b);
    }
}
