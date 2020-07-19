package br.com.sleeptracker.screens.quality

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import br.com.sleeptracker.R
import br.com.sleeptracker.database.SleepDatabase
import br.com.sleeptracker.databinding.FragmentSleepQualityBinding

class SleepQualityFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentSleepQualityBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_sleep_quality, container, false)
        val application = requireNotNull(this.activity).application

        val arguments = SleepQualityFragmentArgs.fromBundle(arguments!!)

        val dataSource = SleepDatabase.getInstance(application).dailySleepQualityDao

        val viewModelFactory = SleepQualityViewModelFactory(arguments.sleepNightKey, dataSource)

        val sleepQualityViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(SleepQualityViewModel::class.java)

        binding.sleepQualityViewModel = sleepQualityViewModel

        sleepQualityViewModel.navigateToSleepTracker.observe(this, Observer {
            if(it == true) {
                this.findNavController().navigate(
                    SleepQualityFragmentDirections.actionSleepQualityToSleepTracker())
                sleepQualityViewModel.doneNavigating()
            }
        })

        return binding.root
    }

}
