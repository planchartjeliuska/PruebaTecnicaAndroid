package com.jeliuska.pruebatecnica

import android.content.Context

object RepositoryInjector {

        private var userRepository: UserRepository? = null

        fun provideUserRepository(): UserRepository {
            if (userRepository != null){
                return this.userRepository!!
            }else{
                this.userRepository = UserRepository()
                return userRepository!!
            }


        }

}