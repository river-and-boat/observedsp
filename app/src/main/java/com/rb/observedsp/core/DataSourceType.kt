package com.rb.observedsp.core

import androidx.annotation.IntDef

@IntDef(
    DataSourceType.SHARED_PREFERENCES,
    DataSourceType.SQLITE
)
annotation class DataSourceType {
    companion object {
        const val SHARED_PREFERENCES = 1
        const val SQLITE = 2
    }
}
