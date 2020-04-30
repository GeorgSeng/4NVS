package at.htl.cooldroid.screens.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class GameViewModel : ViewModel() {

    private val _counter = MutableLiveData<Int>()
    val counter: LiveData<Int>
        get() = _counter

    init {
        _counter.value = 0
    }

    fun addNewClick() {
        _counter.value = _counter.value?.plus(1)
        Timber.i("Log ${_counter.value}")
    }
}
