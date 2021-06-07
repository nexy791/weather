package com.ribsky.simpleweatherapp.model.days

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DaysModel(
    var image: String,
    var text: String,
    var info: String,
    @PrimaryKey(autoGenerate = true) var id: Int = 0
)