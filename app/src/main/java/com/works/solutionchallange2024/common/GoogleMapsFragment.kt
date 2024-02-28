package com.works.solutionchallange2024.common

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.telephony.CellLocation.requestLocationUpdate
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.Task
import com.works.solutionchallange2024.common.listener.permissionS
import com.works.solutionchallange2024.databinding.FragmentGoogleMapsBinding
import com.works.solutionchallange2024.model.LocationData
import java.io.IOException
import java.util.Locale


class GoogleMapsFragment: Fragment(), permissionS{
    private lateinit var binding: FragmentGoogleMapsBinding
    var flpcS =ShowProductMapFragment()
    @SuppressLint("VisibleForTests")
    private lateinit var locationRequest: LocationRequest

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentGoogleMapsBinding.inflate(inflater, container, false)
        binding.buttonLocationGetLoc.setOnClickListener {
            initLocationServices()
            takeLastLocation(requireContext(),requireActivity())
        }
        return binding.root
    }
    private fun initLocationServices() {
        flpcS.flpc = LocationServices.getFusedLocationProviderClient(context)
        locationRequest = LocationRequest.create()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 5000
        locationRequest.fastestInterval = 2000
    }


    //UYGULAMA İZNİ GPS İZİNLERİNİN OLACAGI KISIM BURASI

}

