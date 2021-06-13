package com.rb.observedsp.repository

import android.content.Context
import android.content.SharedPreferences
import com.rb.observedsp.data.DataSource
import java.lang.Exception

@Suppress("UNCHECKED_CAST")
internal class SPDataSourceImpl<T>(context: Context, name: String) : DataSource<T> {
    private var sharedPreferences: SharedPreferences =
        context.getSharedPreferences(name, Context.MODE_PRIVATE);

    override fun get(key: String, defaultValue: T): T {
        return when (defaultValue) {
            is String -> sharedPreferences.getString(key, defaultValue) as T
            is Int -> sharedPreferences.getInt(key, defaultValue) as T
            is Float -> sharedPreferences.getFloat(key, defaultValue) as T
            is Boolean -> sharedPreferences.getBoolean(key, defaultValue) as T
            is Long -> sharedPreferences.getLong(key, defaultValue) as T
            else -> sharedPreferences.getStringSet(key, defaultValue as Set<String>) as T
        }
    }

    override fun add(key: String, value: T): T {
        return addOrUpdate(key, value)
    }

    override fun delete(key: String): Boolean {
        return try {
            val editor = sharedPreferences.edit()
            editor.remove(key)
            editor.apply()
            true
        } catch (ex: Exception) {
            false
        }
    }

    override fun update(key: String, value: T): T {
        if (!sharedPreferences.contains(key)) {
            throw Exception("no exist key")
        }
        return addOrUpdate(key, value)
    }

    private fun addOrUpdate(key: String, value: T): T {
        val editor = sharedPreferences.edit()
        when (value) {
            is String -> editor.putString(key, value)
            is Int -> editor.putInt(key, value)
            is Float -> editor.putFloat(key, value)
            is Boolean -> editor.putBoolean(key, value)
            is Long -> editor.putLong(key, value)
            else -> editor.putStringSet(key, value as Set<String>)
        }
        editor.apply()
        return value
    }
}