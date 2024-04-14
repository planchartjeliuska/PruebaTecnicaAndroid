package com.jeliuska.pruebatecnica.activities

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
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

        detailBinding.backButton.setOnClickListener {
            finish()
        }

        getDetailsUser(idUser)
        createMapFragment()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.detail.value
            }
        }

        detailBinding.addressHeader.setOnClickListener {
            if (detailBinding.containerAddress.visibility == View.GONE) {
                detailBinding.containerAddress.visibility = View.VISIBLE
                detailBinding.expandAddressArrow.setImageDrawable(AppCompatResources.getDrawable(this, R.drawable.baseline_arrow_drop_up_24))
            } else {
                detailBinding.containerAddress.visibility = View.GONE
                detailBinding.expandAddressArrow.setImageDrawable(AppCompatResources.getDrawable(this, R.drawable.twotone_arrow_drop_down_24))
            }
        }

        detailBinding.companyHeader.setOnClickListener {
            if (detailBinding.containerCompany.visibility == View.GONE) {
                detailBinding.containerCompany.visibility = View.VISIBLE
                detailBinding.expandCompanyArrow.setImageDrawable(AppCompatResources.getDrawable(this, R.drawable.baseline_arrow_drop_up_24))
            } else {
                detailBinding.containerCompany.visibility = View.GONE
                detailBinding.expandCompanyArrow.setImageDrawable(AppCompatResources.getDrawable(this, R.drawable.twotone_arrow_drop_down_24))
            }
        }
    }

    private fun getDetailsUser(idUser: Int?) {
        viewModel.getDetailUser(idUser!!)
        observeViewModel()
    }


    private fun observeViewModel(){
        viewModel.detail.observe(this){

            detailBinding.textUsername.text = it.username
            detailBinding.textEmail.text = it.email
            detailBinding.textName.text = it.name
            detailBinding.textStreet.text = "Street: " + it.address.street
            detailBinding.textSuite.text = it.address.suite
            detailBinding.textCity.text = "City: " + it.address.city
            detailBinding.textPostalCode.text = "Zip Code: " + it.address.zipcode
            detailBinding.textPhone.text = it.phone
            detailBinding.textWeb.text = it.website
            detailBinding.textNameCompany.text = it.company.name
            detailBinding.textCatchPhrase.text = "Phrase" + it.company.catchPhrase
            detailBinding.textBs.text = it.company.bs
        }
        viewModel.messageError.observe(this){
            it
        }
    }

    private fun createMapFragment() {
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
                CameraUpdateFactory.newLatLngZoom(coordinates,10f),
                1000,
                null
            )
        }
    }
}