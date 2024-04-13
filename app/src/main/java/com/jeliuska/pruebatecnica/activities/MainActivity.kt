package com.jeliuska.pruebatecnica.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.jeliuska.pruebatecnica.ViewModelInjector
import com.jeliuska.pruebatecnica.databinding.ActivityMainBinding
import com.jeliuska.pruebatecnica.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding : ActivityMainBinding

    private val viewModel: MainViewModel by viewModels {
        ViewModelInjector.provideMainViewModelFactory()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val viewMain = mainBinding.root
        setContentView(viewMain)

    }




}