package br.com.sleeptracker.screens.tracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.sleeptracker.R
import br.com.sleeptracker.databinding.FragmentSleepTrackerBinding

class SleepTrackerFragment : Fragment() {

    private lateinit var binding: FragmentSleepTrackerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sleep_tracker, container, false)
        binding.startButton.setOnClickListener { navigateToQuality() }
        return binding.root
    }

    private fun navigateToQuality() {
        findNavController().navigate(SleepTrackerFragmentDirections.actionSleepTrackerToSleepQuality(0))
    }

}
