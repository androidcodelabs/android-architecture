package com.duongnx.android.lifecycles.step2

import android.os.Bundle
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.duongnx.android.lifecycles.R
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by duongnx on 17/05/2018
 */
class ChronoActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Step 2: Add a ViewModel"
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProviders.of(this).get(ChronometerViewModel::class.java)
        if (viewModel.mStartTime == 0L) {
            val time = SystemClock.elapsedRealtime()
            viewModel.mStartTime = time
            chronometer.base = time
        } else
            chronometer.base = viewModel.mStartTime
        chronometer.start()
    }
}