package com.rb.demo

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.rb.observedsp.builder.LiveDataSourceBuilder
import com.rb.observedsp.core.DataSourceType
import com.rb.observedsp.data.DataSourceFactory

class MainActivity : AppCompatActivity() {
    private var value: Int = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val observableData = LiveDataSourceBuilder<Int>(
            baseContext,
            "Demo"
        ).build(DataSourceFactory(), DataSourceType.SQLITE)

        observableData.get(KEY, 0)
            .observe(this, {
                Log.d("Demo", "receive value: $it")
            })

        findViewById<Button>(R.id.add).setOnClickListener {
            observableData.add(KEY, ++value)
        }

        findViewById<Button>(R.id.delete).setOnClickListener {
            observableData.delete(KEY)
        }
    }

    companion object {
        const val KEY = "demo_key"
    }
}