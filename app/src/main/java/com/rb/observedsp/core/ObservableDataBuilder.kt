package com.rb.observedsp.core

import com.rb.observedsp.data.DataSourceFactory

interface ObservableDataBuilder<T, E> {
    fun build(factory: DataSourceFactory, type: Int): ObservableData<T, E>
}