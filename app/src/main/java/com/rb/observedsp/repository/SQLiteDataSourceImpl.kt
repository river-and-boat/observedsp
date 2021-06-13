package com.rb.observedsp.repository

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.rb.observedsp.data.DataSource

@Suppress("UNCHECKED_CAST")
class SQLiteDataSourceImpl<T>(context: Context, name: String) : DataSource<T> {
    private var db: SQLiteDatabase =
        SQLiteDatabase.openOrCreateDatabase("${context.dataDir}/$name", null)

    init {
        db.execSQL(CREATE_TABLE)
    }

    @SuppressLint("Recycle")
    override fun get(key: String, defaultValue: T): T {
        val columns = arrayOf("value")
        val condition = "id=?"
        val selectionArgs = arrayOf(key)
        val cursor = db.query(TABLE_NAME, columns, condition, selectionArgs, null, null, null)
        if (cursor.moveToFirst()) {
            val result = cursor.getString(cursor.getColumnIndex("value"))
            try {
                return result as T
            } catch (ex: Exception) {
                throw Exception("cast error")
            }
        }
        return defaultValue
    }

    override fun add(key: String, value: T): T {
        val content = ContentValues()
        content.put("id", key)
        content.put("value", value.toString())
        val result = db.insertWithOnConflict(
            TABLE_NAME, null, content, SQLiteDatabase.CONFLICT_REPLACE
        )
        if (result > 0) {
            return value
        }
        throw Exception("error in insert")
    }

    override fun update(key: String, value: T): T {
        val content = ContentValues()
        content.put("value", value.toString())
        val condition = "id=?"
        val whereArgs = arrayOf(key)
        val result = db.update(TABLE_NAME, content, condition, whereArgs)
        if (result > 0) {
            return value
        }
        throw Exception("error in update")
    }

    override fun delete(key: String): Boolean {
        val condition = "id=?"
        val whereArgs = arrayOf(key)
        val result = db.delete(TABLE_NAME, condition, whereArgs)
        if (result > 0) {
            return true
        }
        return false
    }

    companion object {
        private const val TABLE_NAME = "demo"
        private const val CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS $TABLE_NAME(id VARCHAR PRIMARY KEY, value VARCHAR)"
    }
}