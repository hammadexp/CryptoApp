package com.matecho.wms.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SharedPreference(private val context: Context) {
    private val PREFS_NAME = "Prefs_WMS"
    private var settings: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null

    //Save String value in shared preference
    fun saveValueInSharedPreference(key: String?, value: String?) {
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        editor = settings!!.edit()
        editor!!.putString(key, value)
        editor!!.commit()
    }

    //Save boolean value in shared preference
    fun saveValueInSharedPreference(key: String?, value: Boolean) {
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        editor = settings!!.edit()
        editor!!.putBoolean(key, value)
        editor!!.commit()
    }

    //Save int value in shared preference
    fun saveValueInSharedPreference(key: String?, value: Int) {
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        editor = settings!!.edit()
        editor!!.putInt(key, value)
        editor!!.commit()
    }

    //Save long value in shared preference
    fun saveValueInSharedPreference(key: String?, value: Long) {
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        editor = settings!!.edit()
        editor!!.putLong(key, value)
        editor!!.commit()
    }

    //Save float value in shared preference
    fun saveValueInSharedPreference(key: String?, value: Float) {
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        editor = settings!!.edit()
        editor!!.putFloat(key, value)
        editor!!.commit()
    } //Save float value in shared preference

    fun saveStringList(key: String?, list: List<String?>?) {
        val gson = Gson()
        val jsonUser = gson.toJson(list)
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        editor = settings!!.edit()
        editor!!.putString(key, jsonUser)
        editor!!.commit()
    }

    fun getStringListValue(key: String?): List<String> {
        val gson = Gson()
        val services: List<String>
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val jsonPreferences = settings!!.getString(key, "")
        val type = object : TypeToken<List<String?>?>() {}.type
        services = gson.fromJson(jsonPreferences, type)
        return services
    }

    //Get String value from shared preference
    fun getStringValue(key: String?): String {
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return settings!!.getString(key, "")!!
    }

    //Get boolean value from shared preference
    fun getBooleanValue(key: String?): Boolean {
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return settings!!.getBoolean(key, false)
    }

    //Get int value from shared preference
    fun getIntValue(key: String?): Int {
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return settings!!.getInt(key, 0)
    }

    //Get long value from shared preference
    fun getLongValue(key: String?): Long {
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return settings!!.getLong(key, 0)
    }

    //Get float value from shared preference
    fun getFloatValue(key: String?): Float {
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return settings!!.getFloat(key, 0f)
    }

    //To remove all values from preferences use editor.clear() method as shown below.
    fun clearSharedPreference() {
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        editor = settings!!.edit()
        editor!!.clear()
        editor!!.commit()
    }

    //To remove a specific key-value pair use editor.remove(KEY) method as shown below.
    fun removeValue(key: String?) {
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        editor = settings!!.edit()
        editor!!.remove(key)
        editor!!.commit()
    }

}