package com.jeliuska.pruebatecnica.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jeliuska.pruebatecnica.UserRepository

class MainViewModel(
    private var userRepository: UserRepository
): ViewModel() {


}
class MainViewModelFactory(
    private val userRepository: UserRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(userRepository = userRepository) as T
    }

}