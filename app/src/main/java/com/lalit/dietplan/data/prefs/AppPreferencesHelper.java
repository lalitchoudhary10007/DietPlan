package com.lalit.dietplan.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.lalit.dietplan.di.ApplicationContext;
import com.lalit.dietplan.di.PreferenceInfo;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppPreferencesHelper implements PreferencesHelper{

    private static final String PREF_KEY_REMINDER_VALUE = "PREF_KEY_REMINDER_VALUE";
    private final SharedPreferences mPrefs;

    @Inject
    public AppPreferencesHelper(@ApplicationContext Context context,
                                @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    @Override
    public boolean GetRemindervalue() {
        return mPrefs.getBoolean(PREF_KEY_REMINDER_VALUE, false);
    }

    @Override
    public void SetReminderValue(Boolean b) {
        mPrefs.edit().putBoolean(PREF_KEY_REMINDER_VALUE, b).apply();
    }
}
