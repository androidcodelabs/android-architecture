package com.duongnx.android.lifecylce.step3

import android.os.SystemClock
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

/**
 * Created by duongnx on 18/05/2018
 */
class LiveDataTimerViewModel : ViewModel() {
    val ONE_SECOND: Long = 1000
    var mElapsedTime = MutableLiveData<Long>()
    var mInitialTime: Long = 0

    init {
        mInitialTime = SystemClock.elapsedRealtime()
        val timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                val newValue = (SystemClock.elapsedRealtime() - mInitialTime) / 1000
                mElapsedTime.postValue(newValue)
            }
        }, ONE_SECOND, ONE_SECOND)
    }
}