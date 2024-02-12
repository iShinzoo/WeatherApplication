package com.example.weatherapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.weatherapplication.dataModel.Current
import com.example.weatherapplication.dataModel.RetrofaceBuilder


class MainActivity : AppCompatActivity() {

    private lateinit var repo: Repo
    private lateinit var weatherViewModel: WeatherViewModel
    private lateinit var weatherViewModelFactory: WeatherViewModelFactory
    private lateinit var loader : ProgressBar

    private lateinit var edtCityName : EditText
    private lateinit var weatherRegion : TextView
    private lateinit var weatherIcon : ImageView
    private lateinit var waetherCondition : TextView
    private lateinit var btnSearch : ImageView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()


        btnSearch.setOnClickListener( View.OnClickListener {
            weatherViewModel.getWeatherDetail(edtCityName.text.toString())
        })

        weatherViewModel.weatherDetailLiveData.observe(this){

            val currentWeatherType = it.current.condition.text
            val currentTempInC = it.current.temp_c
            val imageLink = "https:${it.current.condition.icon}"
            val currentCity = it.location.name
            val currentState = it.location.country

            weatherRegion.text = " $currentCity , $currentState"
            waetherCondition.text = "$currentWeatherType , $currentTempInC°C"


            Glide.with(this)
                .load(imageLink)
                .into(weatherIcon)
        }

        weatherViewModel.isLoading.observe(this){
            if (it){
                loader.visibility = View.VISIBLE
            } else {
                loader.visibility = View.GONE
            }
        }

    }
    private fun init(){
        repo = Repo(RetrofaceBuilder.getInstance())
        weatherViewModelFactory = WeatherViewModelFactory(repo)
        weatherViewModel = ViewModelProvider(this,weatherViewModelFactory).get(WeatherViewModel::class.java)
        loader = findViewById(R.id.loader)
        edtCityName = findViewById(R.id.searchbar)
        weatherRegion = findViewById(R.id.weatherRegion)
        weatherIcon = findViewById(R.id.weatherIcon)
        waetherCondition = findViewById(R.id.weatherCondition)
        btnSearch = findViewById(R.id.btnSearch)
    }
}

