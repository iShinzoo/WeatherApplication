package com.example.weatherapplication

import com.example.weatherapplication.dataModel.RetrofaceInterface

class Repo (
    private val retrofaceInterface: RetrofaceInterface
){

    suspend fun getWeatherDetail(city : String) = retrofaceInterface.getWeatherdetail(city)
}