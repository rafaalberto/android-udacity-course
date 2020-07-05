package br.com.guesstheword.screens.title

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.guesstheword.R
import br.com.guesstheword.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentTitleBinding =
            DataBindingUtil.inflate(inflater,
                R.layout.fragment_title, container, false)
        binding.playGameButton.setOnClickListener { findNavController().navigate(
            TitleFragmentDirections.actionTitleToGame()) }
        return binding.root
    }

}
