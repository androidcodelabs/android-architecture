package com.duongnx.android.lifecylce.step2

import android.os.Bundle
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.duongnx.android.lifecylce.R
import kotlinx.android.synthetic.main.activity_chronometer.*

/**
 * Created by duongnx on 18/05/2018
 * Code lab: https://codelabs.developers.google.com/codelabs/android-lifecycles
 *
 * Lab2: add ViewModel & chronometer will not be restarted when rotate the screen
 */
class ChronoActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chronometer)

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