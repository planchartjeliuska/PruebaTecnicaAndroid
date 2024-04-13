package com.jeliuska.pruebatecnica.di

import com.jeliuska.pruebatecnica.UserRepository
import com.jeliuska.pruebatecnica.data.remote.ApiClient

object RepositoryInjector {

        private var userRepository: UserRepository? = null

        fun provideUserRepository(): UserRepository {
            if (userRepository != null){
                return userRepository!!
            }else{
                userRepository = UserRepository(
                    servicesApi = ApiClient.build()
                )
                return userRepository!!
            }


        }

}