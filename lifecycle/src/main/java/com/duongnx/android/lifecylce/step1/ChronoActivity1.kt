package com.duongnx.android.lifecylce.step1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.duongnx.android.lifecylce.R
import kotlinx.android.synthetic.main.activity_chronometer.*

/**
 * Created by duongnx on 18/05/2018
 * Code lab: https://codelabs.developers.google.com/codelabs/android-lifecycles
 *
 * Lab1: chronometer will be restart when we rotate the screen
 */
class ChronoActivity1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chronometer)

        chronometer.start()
    }
}