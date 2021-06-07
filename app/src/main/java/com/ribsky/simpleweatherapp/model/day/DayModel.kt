package com.ribsky.simpleweatherapp.model.day

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DayModel(
    var image: Int,
    var text: String,
    var info: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)