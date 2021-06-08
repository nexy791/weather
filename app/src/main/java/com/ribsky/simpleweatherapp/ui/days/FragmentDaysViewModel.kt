package com.ribsky.simpleweatherapp.ui.days

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ribsky.simpleweatherapp.model.days.DaysModel
import com.ribsky.simpleweatherapp.repository.Repository
import com.ribsky.simpleweatherapp.util.Const.LoadingStatus
import kotlinx.coroutines.launch

class FragmentDaysViewModel : ViewModel() {

    private val _days = MutableLiveData<List<DaysModel>>()
    private val _status = MutableLiveData<LoadingStatus>()

    val days: LiveData<List<DaysModel>>
        get() = _days

    val status: LiveData<LoadingStatus>
        get() = _status

    init {
        getWeatherForDays()
    }

    fun getWeatherForDays() {
        _status.value = LoadingStatus.LOADING
        viewModelScope.launch {
            val days = Repository.getInstance().getDaysInfo()
            if (!days.isNullOrEmpty()) {
                _status.value = LoadingStatus.SUCCESS
                _days.value = days
            } else _status.value = LoadingStatus.ERROR
        }
    }
}