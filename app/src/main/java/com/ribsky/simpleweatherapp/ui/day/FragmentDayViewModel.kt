package com.ribsky.simpleweatherapp.ui.day

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ribsky.simpleweatherapp.model.day.DayModel
import com.ribsky.simpleweatherapp.repository.Repository
import com.ribsky.simpleweatherapp.util.Const.LoadingStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class FragmentDayViewModel : ViewModel() {

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _status = MutableLiveData<LoadingStatus>()
    private val _day = MutableLiveData<List<DayModel>>()
    private val _city = MutableLiveData<String>()

    val city: LiveData<String>
        get() = _city

    val day: LiveData<List<DayModel>>
        get() = _day

    val status: LiveData<LoadingStatus>
        get() = _status

    init {
        getWeatherForToday()
    }

    fun getWeatherForToday() {
        _status.value = LoadingStatus.LOADING
        coroutineScope.launch {
            val day = Repository.getInstance().getDayInfo()
            if (!day.isNullOrEmpty()) {
                _city.value = Repository.getInstance().getCity()
                _status.value = LoadingStatus.SUCCESS
                _day.value = day
            } else _status.value = LoadingStatus.ERROR
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}