package com.rb.observedsp.core

interface ObservableData<T, E> {
    fun get(key: String, defaultValue: T): E

    fun add(key: String, value: T)

    fun update(key: String, value: T)

    fun delete(key: String)
}