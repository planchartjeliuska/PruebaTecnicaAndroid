package com.jeliuska.pruebatecnica.di

import com.jeliuska.pruebatecnica.viewmodel.DetailUserViewModel
import com.jeliuska.pruebatecnica.viewmodel.DetailUserViewModelFactory
import com.jeliuska.pruebatecnica.viewmodel.MainViewModelFactory

object ViewModelInjector {
    private var mainViewModelFactory: MainViewModelFactory? = null
    private var detailUserViewModelFactory : DetailUserViewModelFactory?=null


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

    fun provideDetailViewModelFactory(): DetailUserViewModelFactory{
        if (detailUserViewModelFactory != null){
            return detailUserViewModelFactory!!
        }else{
            detailUserViewModelFactory = DetailUserViewModelFactory(
                userRepository = RepositoryInjector.provideUserRepository()
            )
            return detailUserViewModelFactory!!
        }
    }



}