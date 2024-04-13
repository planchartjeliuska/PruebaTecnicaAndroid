package com.jeliuska.pruebatecnica

import com.jeliuska.pruebatecnica.data.remote.ApiClient
import com.jeliuska.pruebatecnica.data.remote.response.UsersResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserRepository(
    val servicesApi: ApiClient.ServicesApi
) {

    fun getUsers(
        onComplete: (ArrayList<UsersResponseItem>)-> Unit,
        onError : (Throwable) -> Unit
    ){
        servicesApi.getUsers().enqueue(object : Callback<ArrayList<UsersResponseItem>> {
            override fun onResponse(
                call: Call<ArrayList<UsersResponseItem>>,
                response: Response<ArrayList<UsersResponseItem>>
            ) {
               if (response.isSuccessful){
                   onComplete(response.body() ?: arrayListOf())

               }else{
                   onError(Throwable("Error"))
               }
            }
            override fun onFailure(p0: Call<ArrayList<UsersResponseItem>>, p1: Throwable) {
              onError(p1)
            }

        })

    }

    fun getDetailUser(
        idUser : Int,
    onComplete: (UsersResponseItem)-> Unit,
    onError : (Throwable) -> Unit
    ){
        servicesApi.getDetailUser(idUser).enqueue(object : Callback<UsersResponseItem>{
            override fun onResponse(
                call: Call<UsersResponseItem>,
                response: Response<UsersResponseItem>
            ) {
                if (response.isSuccessful){
                    response.body()?.let { onComplete(it) }
                }else{
                    onError(Throwable("Error"))
                }
            }
            override fun onFailure(p0: Call<UsersResponseItem>, p1: Throwable) {
                onError(p1)
            }

        })

    }
    /*
    *
    * */



}