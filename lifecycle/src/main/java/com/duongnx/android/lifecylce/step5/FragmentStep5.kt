package com.duongnx.android.lifecylce.step5

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.duongnx.android.lifecylce.R

/**
 * Created by duongnx on 18/05/2018
 */
class FragmentStep5 : Fragment() {
    private var mSeekBar: SeekBar? = null

    private var viewModel: SeekBarViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_step5, container, false)
        viewModel = ViewModelProviders.of(activity!!).get(SeekBarViewModel::class.java)
        mSeekBar = root.findViewById(R.id.seekBar)
        subscribeSeekBar()
        return root
    }


    private fun subscribeSeekBar() {
        mSeekBar!!.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    Log.d("Step5", "Progress changed!")
                    viewModel!!.seekbarValue.value = progress
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}

            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        // Update the SeekBar when the ViewModel is changed.
        viewModel!!.seekbarValue.observe(activity!!, Observer<Int> { value ->
            if (value != null) {
                Log.d("Step5", "seekbarValue changed!")
                mSeekBar!!.progress = value
            }
        })
    }
}