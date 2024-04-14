package com.jeliuska.pruebatecnica.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jeliuska.pruebatecnica.UserRepository
import com.jeliuska.pruebatecnica.data.remote.response.UsersResponseItem

class MainViewModel(
    private var userRepository: UserRepository
): ViewModel() {

    private val _users = MutableLiveData<ArrayList<UsersResponseItem>>()
    val users: LiveData<ArrayList<UsersResponseItem>> = _users

    private val _messageError = MutableLiveData<String>()
    val messageError: LiveData<String> = _messageError
    fun getUsers(){
        userRepository.getUsers(
            onComplete = {
                _users.postValue(it)
            },
            onError = {
                _messageError.postValue(it.message)
            }
        )
    }


}
class MainViewModelFactory(
    private val userRepository: UserRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(userRepository = userRepository) as T
    }

}