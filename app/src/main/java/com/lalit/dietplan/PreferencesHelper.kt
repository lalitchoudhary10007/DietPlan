package com.lalit.dietplan

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

class PreferencesHelper(context: Context) {

    private val PREF_NAME = "Diet_Plan"
    private var PRIVATE_MODE = 0

    companion object {
        const val Diet_Key = "ReminderSetOrNot"
    }
        val sharedPref: SharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)

       fun SaveValueInSharedPref(value: Boolean , key: String){
           val editor = sharedPref.edit()
           editor.putBoolean(key, value)
           editor.apply()
       }

    fun GetSharedPrefValue(key: String): Boolean {
      return sharedPref.getBoolean(key, false)
    }

}