package com.ribsky.simpleweatherapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ribsky.simpleweatherapp.repository.Repository

class MainActivityViewModel : ViewModel() {

    private val _lastUpdated = MutableLiveData<String>()

    val lastUpdated: LiveData<String>
        get() = _lastUpdated

    init {
        _lastUpdated.value = Repository.getInstance().getUpdated()
    }

}