package com.example.weatherapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapplication.dataModel.RetrofaceBuilder


class MainActivity : AppCompatActivity() {

    private lateinit var repo: Repo
    private lateinit var weatherViewModel: WeatherViewModel
    private lateinit var weatherViewModelFactory: WeatherViewModelFactory
    private lateinit var loader : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

        val cityName = "Delhi"
        weatherViewModel.getWeatherDetail(cityName)

        weatherViewModel.weatherDetailLiveData.observe(this){
            Log.i("WeatherDetail",it.toString())
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
    }
}