package com.duongnx.android.lifecylce.step4.normal

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.duongnx.android.lifecylce.R
import kotlinx.android.synthetic.main.activity_location.*

/**
 * Created by duongnx on 18/05/2018
 * Location normal => don't use LifecycleObserver
 */
class LocationActivity : AppCompatActivity() {
    private val REQUEST_LOCATION_PERMISSION_CODE = 1
    private val mGpsListener = MyLocationListener()
    private var mLocationManager: LocationManager? = null

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            bindLocationListener()
        } else {
            Toast.makeText(this, "This sample requires Location access", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !== PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) !== PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
                    REQUEST_LOCATION_PERMISSION_CODE)
        } else {
            bindLocationListener()
        }
    }

    override fun onResume() {
        super.onResume()
        mLocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager?
        mLocationManager!!.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, mGpsListener)

        val lastLocation = mLocationManager!!.getLastKnownLocation(
                LocationManager.GPS_PROVIDER)
        if (lastLocation != null) {
            mGpsListener.onLocationChanged(lastLocation)
        }
    }

    override fun onPause() {
        super.onPause()
        if (mLocationManager == null) {
            return
        }
        mLocationManager!!.removeUpdates(mGpsListener)
        mLocationManager = null
    }

    private fun bindLocationListener() {

    }

    private inner class MyLocationListener : LocationListener {
        override fun onLocationChanged(location: Location) {
            tvLocation.text = location.latitude.toString() + ", " + location.longitude
        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}

        override fun onProviderEnabled(provider: String) {
            Toast.makeText(this@LocationActivity,
                    "Provider enabled: $provider", Toast.LENGTH_SHORT).show()
        }

        override fun onProviderDisabled(provider: String) {}
    }
}