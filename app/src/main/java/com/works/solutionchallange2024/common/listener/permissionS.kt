package com.works.solutionchallange2024.common.listener

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.provider.Settings
import android.util.Log
import androidx.core.app.ActivityCompat

import androidx.core.content.ContentProviderCompat.requireContext
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.Task
import com.works.solutionchallange2024.common.GetLocation
import com.works.solutionchallange2024.common.ShowProductMapFragment
import com.works.solutionchallange2024.model.LocationData
import java.io.IOException
import java.util.Locale
import android.Manifest.permission.ACCESS_FINE_LOCATION
var getLocation : GetLocation = GetLocation()
private var mMap: GoogleMap? = null
var thisData: LocationData ?=null
private lateinit var locationTask: Task<Location>
//private var flpc: FusedLocationProviderClient ?=null
 interface permissionS {

    fun takeLastLocation(context: Context, activity: Activity) {
        var sehir = ""
        var adres = ""
        var enlem = ""
        var boylam = ""
        var locationData: LocationData
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            // KONUM AÇ KAPA
            Log.i("1. İF","BURDAYIZ")
            val alertDialog = AlertDialog.Builder(context)
            alertDialog.setTitle("Konum Hatası")
            alertDialog.setMessage("Lütfen konum özelliğini açın.")
            alertDialog.setPositiveButton("Ayarlar") { _, _ ->
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                activity?.startActivity(intent)
            }
            alertDialog.show()
        }
        // UYGULAMA İZİNLERİ VER  VERME
        if (ActivityCompat.checkSelfPermission(context, ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            Log.i("2. İF","BURDAYIZ")
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(ACCESS_FINE_LOCATION), 100
            )
        }
        // BURDAN İTİBAREN SON KONUM ALMA FONKSİYONU OLUCAK.
        mMap?.isMyLocationEnabled = true
        locationTask = ShowProductMapFragment().flpc?.lastLocation ?:
        ShowProductMapFragment().flpc!!.lastLocation.addOnSuccessListener(activity) { location ->
            if (location != null) {
                try {
                    val geocoder = Geocoder(context, Locale.getDefault())
                    val adress = geocoder.getFromLocation(location.latitude, location.longitude, 1)
                    var currentLatLong = LatLng(location.latitude, location.longitude)
                    sehir = adress?.get(0)?.adminArea.toString()
                    adres = adress?.get(0)?.getAddressLine(0).toString()
                    enlem = currentLatLong.latitude.toString()
                    boylam = currentLatLong.longitude.toString()
                } catch (e: IOException) {
                    e.printStackTrace()
                } finally {
                    locationData = LocationData(enlem, boylam, adres, sehir)
                    thisData= getLocation?.checkLastLocation(locationData)
                }
            }
            }
        }
//deneme
}