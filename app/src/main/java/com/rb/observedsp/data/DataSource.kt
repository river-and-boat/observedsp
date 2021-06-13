package com.rb.observedsp.data

interface DataSource<T> {
    fun get(key: String, defaultValue: T): T

    fun add(key: String, value: T): T

    fun update(key: String, value: T): T

    fun delete(key: String): Boolean
}