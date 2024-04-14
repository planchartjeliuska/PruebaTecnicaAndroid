package com.jeliuska.pruebatecnica.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.jeliuska.pruebatecnica.adapters.AdapterUsers
import com.jeliuska.pruebatecnica.data.remote.response.UsersResponseItem
import com.jeliuska.pruebatecnica.databinding.ActivityMainBinding
import com.jeliuska.pruebatecnica.di.ViewModelInjector
import com.jeliuska.pruebatecnica.viewmodel.MainViewModel

class MainActivity : AppCompatActivity(), AdapterUsers.OnUserListener {

    private lateinit var mainBinding : ActivityMainBinding

    private val viewModel: MainViewModel by viewModels {
        ViewModelInjector.provideMainViewModelFactory()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val viewMain = mainBinding.root
        setContentView(viewMain)

        getUsers()
        observeViewModel()
    }


    private fun getUsers(){
        viewModel.getUsers()
    }
    private fun observeViewModel(){
        viewModel.users.observe(this){
            //ENVIAR DATOS AL RECICLER
            mainBinding.recyclerMenu.setLayoutManager(
                LinearLayoutManager(
                    this,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            )
            mainBinding.recyclerMenu.adapter = AdapterUsers( it,this)
        }
        viewModel.messageError.observe(this){
            it
        }
    }

    override fun onMenuClick(usersResponseItem: UsersResponseItem) {
        val idUser = usersResponseItem.id
        startActivity(Intent(this, DetailUserActivity::class.java).apply {
            putExtra("idUser",idUser )
        })

    }


}