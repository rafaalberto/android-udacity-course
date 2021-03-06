package br.com.guesstheword.screens.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import br.com.guesstheword.R
import br.com.guesstheword.databinding.FragmentGameBinding

class GameFragment : Fragment() {

    private lateinit var viewModel: GameViewModel

    private lateinit var binding: FragmentGameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)

        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)

        binding.gameViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.eventGameFinish.observe(this, Observer {
            if (it) {
                gameFinished()
                viewModel.onGameFinishComplete()
            }
        })

        return binding.root
    }

    private fun gameFinished() {
        findNavController().navigate(
            GameFragmentDirections.actionGameToScore(viewModel.score.value ?: 0)
        )
    }
}
