package com.example.weatherapplication.dataModel

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofaceBuilder {

    companion object{
        var retrofitServices : RetrofaceInterface? = null
        fun getInstance() : RetrofaceInterface{
            if (retrofitServices == null){
                retrofitServices = Retrofit.Builder()
                    .baseUrl("https://api.weatherapi.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(RetrofaceInterface::class.java)
            }
            return retrofitServices!!
        }
    }
}