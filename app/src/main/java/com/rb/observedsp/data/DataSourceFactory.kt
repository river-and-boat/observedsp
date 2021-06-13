package com.rb.observedsp.data

import android.content.Context
import com.rb.observedsp.core.DataSourceType
import com.rb.observedsp.repository.SPDataSourceImpl
import com.rb.observedsp.repository.SQLiteDataSourceImpl

class DataSourceFactory {
    fun <T> createDataSource(
        context: Context,
        type: Int,
        name: String
    ): DataSource<T> {
        return when(type) {
            DataSourceType.SHARED_PREFERENCES -> SPDataSourceImpl(context, name)
            DataSourceType.SQLITE -> SQLiteDataSourceImpl(context, name)
            else -> throw Exception("error type")
        }
    }
}