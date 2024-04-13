package com.jeliuska.pruebatecnica

import android.content.Context
import com.jeliuska.pruebatecnica.viewmodel.MainViewModelFactory

object ViewModelInjector {
    private var mainViewModelFactory: MainViewModelFactory? = null


    fun provideMainViewModelFactory(): MainViewModelFactory {
        if (mainViewModelFactory != null){
            return this.mainViewModelFactory!!
        }else{
            this.mainViewModelFactory = MainViewModelFactory(
                userRepository = RepositoryInjector.provideUserRepository()
            )
            return mainViewModelFactory!!
        }


    }
}