package com.jeliuska.pruebatecnica.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jeliuska.pruebatecnica.UserRepository
import com.jeliuska.pruebatecnica.data.remote.response.UsersResponseItem

class DetailUserViewModel(
    private var userRepository: UserRepository
): ViewModel() {

    private val _detail = MutableLiveData<UsersResponseItem>()
    val detail: LiveData<UsersResponseItem> = _detail

    private val _messageError = MutableLiveData<String>()
    val messageError: LiveData<String> = _messageError
    fun getDetailUser(idUser :Int){
        userRepository.getDetailUser(
            idUser = idUser,
            onComplete = {
                _detail.postValue(it)
            },
            onError = {
                _messageError.postValue(it.message)
            }
        )
    }



}
class DetailUserViewModelFactory(
    private val userRepository: UserRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailUserViewModel(userRepository = userRepository) as T
    }

}