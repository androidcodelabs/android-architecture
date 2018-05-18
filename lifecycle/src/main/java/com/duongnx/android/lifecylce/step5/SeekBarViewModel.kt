package com.duongnx.android.lifecylce.step5

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by duongnx on 18/05/2018
 */
class SeekBarViewModel : ViewModel() {
    var seekbarValue = MutableLiveData<Int>()
}