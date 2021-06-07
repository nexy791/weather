package com.ribsky.simpleweatherapp.db

import androidx.room.*
import com.ribsky.simpleweatherapp.model.day.DayModel
import com.ribsky.simpleweatherapp.model.days.DaysModel

@Dao
interface DayDao {
    @Query("SELECT * FROM daymodel")
    suspend fun getDay(): List<DayModel>?

    @Update
    suspend fun update(dayModel: List<DayModel>)

    @Insert
    suspend fun insert(dayModel: List<DayModel>)

    @Delete
    suspend fun delete(dayModel: List<DayModel>)

    @Query("DELETE FROM daymodel")
    suspend fun deleteAll()
}

@Dao
interface DaysDao {
    @Query("SELECT * FROM daysModel")
    suspend fun getDays(): List<DaysModel>?

    @Update
    suspend fun update(dayModel: List<DaysModel>)

    @Insert
    suspend fun insert(dayModel: List<DaysModel>)

    @Delete
    suspend fun delete(dayModel: List<DaysModel>)

    @Query("DELETE FROM daysModel")
    suspend fun deleteAll()
}