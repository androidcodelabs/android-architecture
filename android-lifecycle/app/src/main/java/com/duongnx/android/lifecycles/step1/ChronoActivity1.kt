package com.duongnx.android.lifecycles.step1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.duongnx.android.lifecycles.R
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by duongnx on 17/05/2018
 */
class ChronoActivity1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Step 1: ChronoActivity1"
        setContentView(R.layout.activity_main)
        chronometer.start()
    }
}