package com.ribsky.simpleweatherapp.repository

import android.app.Application
import android.content.Context
import com.ribsky.simpleweatherapp.R
import com.ribsky.simpleweatherapp.db.AppDatabase
import com.ribsky.simpleweatherapp.model.day.DayModel
import com.ribsky.simpleweatherapp.model.day.WeatherTodayModel
import com.ribsky.simpleweatherapp.model.days.DaysModel
import com.ribsky.simpleweatherapp.model.days.WeatherDaysModel
import com.ribsky.simpleweatherapp.network.WeatherAPI
import com.ribsky.simpleweatherapp.util.Const

class Repository : Application() {

    init {
        INSTANCE = this
    }

    companion object {
        @Volatile
        private var INSTANCE: Repository? = null

        fun getInstance(): Repository {
            if (INSTANCE == null) INSTANCE = Repository()
            return INSTANCE as Repository
        }
    }

    private var dayInfo: List<DayModel>? = null
    private var daysInfo: List<DaysModel>? = null

    suspend fun getDayInfo(): List<DayModel>? {
        return if (dayInfo != null) dayInfo
        else {
            dayInfo = try {

                val response = WeatherAPI.api.getWeatherToday()
                val day = addDayInfo(response)

                AppDatabase.getInstance(applicationContext).dayDao.deleteAll()
                AppDatabase.getInstance(applicationContext).dayDao.insert(day)

                getCity(response.location.country + ", " + response.location.name)
                getUpdated(response.current.lastUpdated)

                day
            } catch (e: Exception) {
                AppDatabase.getInstance(applicationContext).dayDao.getDay()
            }
            dayInfo
        }
    }

    suspend fun getDaysInfo(): List<DaysModel>? {
        return if (daysInfo != null) daysInfo
        else {
            daysInfo = try {
                val days = addDaysInfo(
                    WeatherAPI.api.getWeatherDays()
                )

                AppDatabase.getInstance(applicationContext).daysDao.deleteAll()
                AppDatabase.getInstance(applicationContext).daysDao.insert(days)

                days
            } catch (e: Exception) {
                AppDatabase.getInstance(applicationContext).daysDao.getDays()
            }
            daysInfo
        }
    }


    private fun addDayInfo(weatherAPI: WeatherTodayModel): List<DayModel> =
        mutableListOf<DayModel>().apply {
            add(
                DayModel(
                    R.drawable.ic_temp,
                    "${weatherAPI.current.tempC}℃",
                    getString(R.string.infoTemp)
                )
            )
            add(
                DayModel(
                    R.drawable.ic_feel,
                    "${weatherAPI.current.feelslikeC}℃",
                    getString(R.string.infoFeelLikes)
                )
            )
            add(
                DayModel(
                    R.drawable.ic_wind,
                    "${weatherAPI.current.windKph}km/h",
                    getString(R.string.infoWind)
                )
            )
            add(
                DayModel(
                    R.drawable.ic_rain,
                    "${weatherAPI.current.humidity}%",
                    getString(R.string.infoHumidity)
                )
            )
            add(
                DayModel(
                    R.drawable.ic_cloud,
                    "${weatherAPI.current.cloud}%",
                    getString(R.string.infoCloudy)
                )
            )
            add(
                DayModel(
                    R.drawable.ic_uv,
                    "${weatherAPI.current.uv}",
                    getString(R.string.infoUV)
                )
            )
        }

    private fun addDaysInfo(days: WeatherDaysModel): List<DaysModel> {
        return mutableListOf<DaysModel>().apply {
            for (i in days.forecast.forecastday) {
                add(
                    DaysModel(
                        "https:${i.day.condition.icon}",
                        i.day.avgtempC.toString(),
                        i.date
                    )
                )
            }
        }
    }

    fun getCity(city: String = ""): String? {
        with(
            applicationContext.getSharedPreferences(
                Const.SHARED_PREFS_NAME,
                Context.MODE_PRIVATE
            )
        ) {
            return if (city.isNotBlank()) {
                edit().putString(Const.SHARED_PREFS_KEY_CITY, city).apply()
                city
            } else getString(Const.SHARED_PREFS_KEY_CITY, "")
        }
    }

    fun getUpdated(updated: String = ""): String? {
        with(
            applicationContext.getSharedPreferences(
                Const.SHARED_PREFS_NAME,
                Context.MODE_PRIVATE
            )
        ) {
            return if (updated.isNotBlank()) {
                edit().putString(Const.SHARED_PREFS_KEY_UPDATED, updated).apply()
                updated
            } else getString(Const.SHARED_PREFS_KEY_UPDATED, "")
        }
    }
}