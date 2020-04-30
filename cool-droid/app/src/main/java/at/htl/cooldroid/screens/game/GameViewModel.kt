package at.htl.cooldroid.screens.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    init {
        _score.value = 0
    }

    fun addNewClick() {
        _score.value = _score.value?.plus(1)
        //Timber.i("Log ${_score.value}")
    }
}
