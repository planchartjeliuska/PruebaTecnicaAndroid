package com.jeliuska.pruebatecnica.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.jeliuska.pruebatecnica.R
import com.jeliuska.pruebatecnica.databinding.ActivityDetailUserBinding

class DetailUserActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var detailBinding: ActivityDetailUserBinding
    private lateinit var map:GoogleMap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivityDetailUserBinding.inflate(layoutInflater)
        val viewDetail = detailBinding.root
        setContentView(viewDetail)

        val bundle = intent.extras
        val idUser = bundle?.getInt("idUser")

        getDetailsUser(idUser)
        createFragment()
    }


    private fun getDetailsUser(idUser: Int?) {

    }
    private fun createFragment() {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        createMarker()
    }

    private fun createMarker() {
        val coordinates = LatLng(28.043893, -16.539329)
        val marker = MarkerOptions().position(coordinates)
        map.addMarker(marker)
        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(coordinates,15f),
            4000,
            null
        )
    }
}