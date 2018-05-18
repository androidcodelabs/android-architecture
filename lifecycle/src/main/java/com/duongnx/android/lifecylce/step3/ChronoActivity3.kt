package com.duongnx.android.lifecylce.step3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.duongnx.android.lifecylce.R
import kotlinx.android.synthetic.main.activity_chrono.*

/**
 * Created by duongnx on 18/05/2018
 * Code lab: https://codelabs.developers.google.com/codelabs/android-lifecycles
 *
 * use LiveData
 */
class ChronoActivity3 : AppCompatActivity() {
    lateinit var viewModel: LiveDataTimerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chrono)
        viewModel = ViewModelProviders.of(this).get(LiveDataTimerViewModel::class.java)
        subscribe()
    }

    fun subscribe() {
        var elapsedTimeObserver = Observer<Long> {
            val newText = this@ChronoActivity3.resources.getString(R.string.seconds, it)
            tvTimer.text = newText
        }
        viewModel.mElapsedTime.observe(this, elapsedTimeObserver)
    }
}