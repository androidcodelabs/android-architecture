package com.duongnx.android.lifecylce.step4.observer

import android.content.Context
import android.location.LocationListener
import android.location.LocationManager
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

/**
 * Created by duongnx on 18/05/2018
 */
object BoundLocationManager {
    fun bindLocationListenerIn(lifecycleOwner: LifecycleOwner,
                               listener: LocationListener, context: Context) {
        BoundLocationListener(lifecycleOwner, listener, context)
    }
}

class BoundLocationListener : LifecycleObserver {
    private var mContext: Context? = null
    private var mLocationManager: LocationManager? = null
    private var mListener: LocationListener? = null

    constructor(lifecycleOwner: LifecycleOwner, listener: LocationListener, context: Context) {
        mContext = context
        mListener = listener
        lifecycleOwner.lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun addLocationListener() {
        // Note: Use the Fused Location Provider from Google Play Services instead.
        // https://developers.google.com/android/reference/com/google/android/gms/location/FusedLocationProviderApi

        mLocationManager = mContext!!.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        mLocationManager!!.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, mListener)
        Log.d("BoundLocationMgr", "Listener added")

        // Force an update with the last location, if available.
        val lastLocation = mLocationManager!!.getLastKnownLocation(
                LocationManager.GPS_PROVIDER)
        if (lastLocation != null) {
            mListener!!.onLocationChanged(lastLocation)
        }
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun removeLocationListener() {
        if (mLocationManager == null) {
            return
        }
        mLocationManager!!.removeUpdates(mListener)
        mLocationManager = null
        Log.d("BoundLocationMgr", "Listener removed")
    }
}