package br.com.guesstheword.screens.game

import android.os.CountDownTimer
import android.text.format.DateUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    companion object {
        private const val DONE = 0L
        private const val ONE_SECOND = 1000L
        private const val COUNTDOWN_TIME = 10000L
    }

    private val _word = MutableLiveData<String>()
    private val _score = MutableLiveData<Int>()
    private val _eventGameFinish = MutableLiveData<Boolean>()
    private val _currentTime = MutableLiveData<Long>()

    val word: LiveData<String> get() = _word
    val score: LiveData<Int> get() = _score
    val eventGameFinish: LiveData<Boolean> get() = _eventGameFinish
    val currentTimeString: LiveData<String> =
        Transformations.map(_currentTime) { DateUtils.formatElapsedTime(it) }

    private lateinit var wordList: MutableList<String>
    private val timer: CountDownTimer

    init {
        _score.value = 0
        _eventGameFinish.value = false
        resetList()
        nextWord()

        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {
            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value = (millisUntilFinished / ONE_SECOND)
            }

            override fun onFinish() {
                _currentTime.value = DONE
                _eventGameFinish.value = true
            }
        }

        timer.start()
    }

    private fun resetList() {
        wordList = mutableListOf(
            "queen",
            "hospital",
            "basketball",
            "cat",
            "change",
            "snail",
            "soup",
            "calendar",
            "sad",
            "desk",
            "guitar",
            "home",
            "railway",
            "zebra",
            "jelly",
            "car",
            "crow",
            "trade",
            "bag",
            "roll",
            "bubble"
        )
        wordList.shuffle()
    }

    private fun nextWord() {
        if (wordList.isEmpty()) {
            resetList()
        }
        _word.value = wordList.removeAt(0)
    }

    fun onCorrect() {
        _score.value = (_score.value)?.plus(1)
        nextWord()
    }

    fun onSkip() {
        _score.value = (_score.value)?.minus(1)
        nextWord()
    }

    fun onGameFinishComplete() {
        _eventGameFinish.value = false
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }

}