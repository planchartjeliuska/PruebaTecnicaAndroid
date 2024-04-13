package com.jeliuska.pruebatecnica.data.remote

import com.jeliuska.pruebatecnica.data.remote.response.UsersResponseItem
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object ApiClient {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com"

    fun build(): ServicesApi {

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

        val httpClient = OkHttpClient.Builder()
        val retrofit = retrofitBuilder.client(httpClient.build()).build()

        return retrofit.create(ServicesApi::class.java)
    }

    interface ServicesApi{

        @GET ("/users")
        fun getUsers(): Call<ArrayList<UsersResponseItem>>
    }

}