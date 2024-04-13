package com.jeliuska.pruebatecnica.di

import com.jeliuska.pruebatecnica.viewmodel.MainViewModelFactory

object ViewModelInjector {
    private var mainViewModelFactory: MainViewModelFactory? = null


    fun provideMainViewModelFactory(): MainViewModelFactory {
        if (mainViewModelFactory != null){
            return mainViewModelFactory!!
        }else{
            mainViewModelFactory = MainViewModelFactory(
                userRepository = RepositoryInjector.provideUserRepository()
            )
            return mainViewModelFactory!!
        }


    }
}