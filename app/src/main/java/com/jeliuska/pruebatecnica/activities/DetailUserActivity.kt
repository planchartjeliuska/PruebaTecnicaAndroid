package com.jeliuska.pruebatecnica.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.jeliuska.pruebatecnica.R
import com.jeliuska.pruebatecnica.databinding.ActivityDetailUserBinding
import com.jeliuska.pruebatecnica.di.ViewModelInjector
import com.jeliuska.pruebatecnica.viewmodel.DetailUserViewModel
import kotlinx.coroutines.launch

class DetailUserActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var detailBinding: ActivityDetailUserBinding
    private lateinit var map:GoogleMap

    private val viewModel: DetailUserViewModel by viewModels {
        ViewModelInjector.provideDetailViewModelFactory()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivityDetailUserBinding.inflate(layoutInflater)
        val viewDetail = detailBinding.root
        setContentView(viewDetail)

        val bundle = intent.extras
        val idUser = bundle?.getInt("idUser")

        getDetailsUser(idUser)
        createFragment()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.detail.value
            }
        }


    }


    private fun getDetailsUser(idUser: Int?) {
        viewModel.getDetailUser(idUser!!)
        observeViewModel()
    }


    private fun observeViewModel(){
        viewModel.detail.observe(this){

            detailBinding.tvNameUser.text = it.name
            detailBinding.tvuserNameUser.text = it.username
            detailBinding.tvemailUser.text = it.email
            detailBinding.tvaddressUser.text = it.website
        }
        viewModel.messageError.observe(this){
            it
        }
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
        viewModel.detail.observe(this){
            val lat = it.address.geo.lat.toDouble()
            val lng = it.address.geo.lng.toDouble()

            val coordinates = LatLng(lat, lng)
            val marker = MarkerOptions().position(coordinates)

            map.addMarker(marker)
            map.animateCamera(
                CameraUpdateFactory.newLatLngZoom(coordinates,15f),
                4000,
                null
            )
        }
    }


}