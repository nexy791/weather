package com.ribsky.simpleweatherapp.model.day


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherTodayModel(
    @Json(name = "current")
    val current: Current,
    @Json(name = "location")
    val location: Location
)