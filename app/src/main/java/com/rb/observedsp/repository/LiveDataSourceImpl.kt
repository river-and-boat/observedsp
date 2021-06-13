package com.rb.observedsp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rb.observedsp.core.ObservableData
import com.rb.observedsp.data.DataSource

internal class LiveDataSourceImpl<T>(
    private val dataSource: DataSource<T>
) : ObservableData<T, LiveData<T>> {
    private val liveData = MutableLiveData<T>()

    override fun get(key: String, defaultValue: T): LiveData<T> {
        val value = dataSource.get(key, defaultValue)
        liveData.value = value
        return liveData
    }

    override fun add(key: String, value: T) {
        val result = dataSource.add(key, value)
        liveData.value = result
    }

    override fun update(key: String, value: T) {
        val result = dataSource.update(key, value)
        liveData.value = result
    }

    override fun delete(key: String) {
        val result = dataSource.delete(key)
        if (result) {
            liveData.value = null
        }
    }
}