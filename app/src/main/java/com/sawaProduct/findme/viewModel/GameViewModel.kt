package com.sawaProduct.findme.viewModel

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    private var countDownTimer: CountDownTimer? = null
    private var counterTimer = 11000L

    private val _seconds = MutableLiveData(0)
    val seconds: LiveData<Int>
        get() = _seconds

    private val _lost = MutableLiveData(0)
    val lost: LiveData<Int>
        get() = _lost

    private val _progress = MutableLiveData(1f)
    val progress: LiveData<Float>
        get() = _progress

    init {
        startCountDown()
    }

    fun startCountDown() {
        if (countDownTimer != null) {
            cancelTimer()
        }

        countDownTimer = object : CountDownTimer(counterTimer, 1000) {
            override fun onTick(millisecs: Long) {
                // Seconds
                val secs = (millisecs / 1000).toInt()

                if (secs != seconds.value) {
                    _seconds.postValue(secs)
                }

                _progress.postValue(millisecs.toFloat() / counterTimer.toFloat())

            }

            override fun onFinish() {
                _progress.postValue(1.0f)
                _lost.postValue(1)
            }
        }
        countDownTimer?.start()
    }

    fun cancelTimer() {
        _lost.postValue(0)
        countDownTimer?.cancel()
    }

    override fun onCleared() {
        super.onCleared()
        cancelTimer()
    }

}