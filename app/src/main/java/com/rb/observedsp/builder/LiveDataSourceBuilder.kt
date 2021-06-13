package com.rb.observedsp.builder

import android.content.Context
import androidx.lifecycle.LiveData
import com.rb.observedsp.core.ObservableData
import com.rb.observedsp.core.ObservableDataBuilder
import com.rb.observedsp.data.DataSourceFactory
import com.rb.observedsp.repository.LiveDataSourceImpl

class LiveDataSourceBuilder<T>(
    private val context: Context,
    private val name: String
) : ObservableDataBuilder<T, LiveData<T>> {
    override fun build(
        factory: DataSourceFactory,
        type: Int
    ): ObservableData<T, LiveData<T>> {
        val dataSource = factory.createDataSource<T>(context, type, name)
        return LiveDataSourceImpl(dataSource)
    }
}