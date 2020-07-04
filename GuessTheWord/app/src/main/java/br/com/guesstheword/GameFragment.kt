package br.com.guesstheword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.guesstheword.databinding.FragmentGameBinding

class GameFragment : Fragment() {

    private var word = ""
    private var score = 0

    private lateinit var wordList: MutableList<String>
    private lateinit var binding: FragmentGameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)
        resetList()
        nextWord()
        binding.correctButton.setOnClickListener { onCorrect() }
        binding.skipButton.setOnClickListener { onSkip() }
        updateScoreText()
        updateWordText()
        return binding.root
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
            gameFinished()
        } else {
            word = wordList.removeAt(0)
        }
        updateWordText()
        updateScoreText()
    }

    private fun onCorrect() {
        score++
        nextWord()
    }

    private fun onSkip() {
        score--
        nextWord()
    }

    private fun updateWordText() {
        binding.wordText.text = word
    }

    private fun updateScoreText() {
        binding.scoreText.text = score.toString()
    }

    private fun gameFinished() {
        findNavController().navigate(GameFragmentDirections.actionGameToScore(score))
    }

}
