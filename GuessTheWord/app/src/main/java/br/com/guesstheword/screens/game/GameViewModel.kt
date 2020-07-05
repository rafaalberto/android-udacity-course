package br.com.guesstheword.screens.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    private val _word = MutableLiveData<String>()
    private val _score = MutableLiveData<Int>()
    private val _eventGameFinish = MutableLiveData<Boolean>()

    val word: LiveData<String> get() = _word
    val score: LiveData<Int> get() = _score
    val eventGameFinish: LiveData<Boolean> get() = _eventGameFinish

    private lateinit var wordList: MutableList<String>

    init {
        _score.value = 0
        _eventGameFinish.value = false
        resetList()
        nextWord()
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
            _eventGameFinish.value = true
        } else {
            _word.value = wordList.removeAt(0)
        }
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

}