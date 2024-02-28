package com.works.solutionchallange2024.common

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.works.solutionchallange2024.R
import com.works.solutionchallange2024.common.listener.permissionS
import com.works.solutionchallange2024.view.ShowProductMamActivity

class ShowProductMapFragment : Fragment(), permissionS {
    lateinit var mapView: MapView
    lateinit var flpc: FusedLocationProviderClient
    var productLongitude: Double = 0.0
    var productLatitude: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        flpc = FusedLocationProviderClient(requireContext())
        getLastLocation()
    }

    private fun getLastLocation() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            flpc.lastLocation.addOnSuccessListener { location ->
                location?.let {
                    productLatitude = it.latitude
                    productLongitude = it.longitude
                    updateMap()
                }
            }
        } else {
            // Request location permission here
            // ActivityCompat.requestPermissions(...)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_show_product_map, container, false)
        mapView = view.findViewById(R.id.mapView)
        mapView.onCreate(savedInstanceState)

        mapView.getMapAsync { googleMap ->
            updateMap()
        }

        return view
    }

    private fun updateMap() {
        val coordinates = LatLng(productLatitude, productLongitude)
        mapView.getMapAsync { googleMap ->
            googleMap.clear() // Clear existing markers
            googleMap.addMarker(MarkerOptions().position(coordinates).title("Ürün Konumu"))
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coordinates, 12f))
        }
    }
}
