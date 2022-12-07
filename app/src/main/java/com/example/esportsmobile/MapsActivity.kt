package com.example.esportsmobile

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.esportsmobile.databinding.ActivityMapsBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var lastLocation : Location
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    private lateinit var binding: ActivityMapsBinding

    private val locations : MutableList<LatLng> = ArrayList()

    private fun startLocations(){
        val brazil = LatLng(-8.556731811416357, -54.256461455128644)
        val eua = LatLng(39.658450334388235, -100.83849321031202)
        val europe = LatLng(50.89254663419282, 14.941033986279752)
        val china = LatLng(36.32660638391752, 104.18984016532804)
        val southKorea = LatLng(35.85738532114404, 126.75064563142678)
        locations.apply {
            add(brazil)
            add(eua)
            add(europe)
            add(china)
            add(southKorea)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startLocations()

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        setUpMap()

        val quixada = LatLng(-4.913680375031387, -39.03306445243842)
        for (place in locations){
            mMap.addMarker(MarkerOptions().position(place))
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLng(quixada))
    }

    private fun setUpMap(){

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {

            return
        }
        mMap.isMyLocationEnabled = true
        fusedLocationProviderClient.lastLocation.addOnSuccessListener(this) {location ->
            if (location!=null){
                lastLocation = location
                val currentLatLong = LatLng(location.latitude, location.longitude)
                mMap.addMarker(MarkerOptions().position(currentLatLong).title("I am Here"))
                mMap.animateCamera(CameraUpdateFactory.newLatLng(currentLatLong))
            }
        }
    }
}