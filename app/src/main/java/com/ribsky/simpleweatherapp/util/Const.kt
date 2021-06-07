package com.ribsky.simpleweatherapp.util

class Const {
    companion object {

        const val GITHUB_URL = "https://github.com/nexy791/weather"
        const val WEATHER_API_KEY = "ab5ec1df5b564ffeb48120354210606"
        const val WEATHER_URL_API = "https://api.weatherapi.com/"
        const val SHARED_PREFS_NAME = "weather"

        const val DB_NAME = "db"

        const val SHARED_PREFS_KEY_CITY = "city"
        const val SHARED_PREFS_KEY_UPDATED = "updated"

    }

    enum class LoadingStatus {
        LOADING,
        ERROR,
        SUCCESS
    }
}