package br.com.sleeptracker.screens.tracker

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
import br.com.sleeptracker.databinding.FragmentSleepTrackerBinding

class SleepTrackerFragment : Fragment() {

    private lateinit var binding: FragmentSleepTrackerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_sleep_tracker, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = SleepDatabase.getInstance(application).dailySleepQualityDao
        val viewModelFactory = SleepTrackerViewModelFactory(dataSource, application)
        val sleepTrackerViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(SleepTrackerViewModel::class.java)

        binding.sleepTrackerViewModel = sleepTrackerViewModel
        binding.lifecycleOwner = this

        sleepTrackerViewModel.navigateToSleepQuality.observe(this, Observer {
            it?.let {
                this.findNavController().navigate(SleepTrackerFragmentDirections.actionSleepTrackerToSleepQuality(it.id))
                sleepTrackerViewModel.doneNavigation()
            }
        })

        return binding.root
    }

}
