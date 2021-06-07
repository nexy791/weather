package com.ribsky.simpleweatherapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ribsky.simpleweatherapp.model.day.DayModel
import com.ribsky.simpleweatherapp.model.days.DaysModel
import com.ribsky.simpleweatherapp.util.Const

@Database(entities = [DayModel::class, DaysModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract val dayDao: DayDao
    abstract val daysDao: DaysDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, Const.DB_NAME
            ).build()
    }

}