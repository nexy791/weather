package com.ribsky.simpleweatherapp.network

import com.ribsky.simpleweatherapp.model.day.WeatherTodayModel
import com.ribsky.simpleweatherapp.model.days.WeatherDaysModel
import com.ribsky.simpleweatherapp.util.Const
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(Const.WEATHER_URL_API)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

interface ApiService {
    @GET("v1/current.json?q=auto:ip&key=${Const.WEATHER_API_KEY}")
    suspend fun getWeatherToday(): WeatherTodayModel

    @GET("v1/forecast.json?q=auto:ip&days=10&key=${Const.WEATHER_API_KEY}")
    suspend fun getWeatherDays(): WeatherDaysModel

}

object WeatherAPI {
    val api: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}