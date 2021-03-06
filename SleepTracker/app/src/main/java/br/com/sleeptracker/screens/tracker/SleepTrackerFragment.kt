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
import com.google.android.material.snackbar.Snackbar

class SleepTrackerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentSleepTrackerBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_sleep_tracker, container, false
        )

        val application = requireNotNull(this.activity).application

        val dataSource = SleepDatabase.getInstance(application).dailySleepQualityDao

        val viewModelFactory = SleepTrackerViewModelFactory(dataSource, application)

        val sleepTrackerViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(SleepTrackerViewModel::class.java)

        binding.sleepTrackerViewModel = sleepTrackerViewModel

        binding.lifecycleOwner = this

        val adapter = SleepNightAdapter(SleepNightListener { sleepId ->
            sleepTrackerViewModel.onSleepNightClicked(sleepId)
        })

        binding.sleepList.adapter = adapter

        sleepTrackerViewModel.navigateToSleepQuality.observe(this, Observer {
            it?.let {
                this.findNavController()
                    .navigate(SleepTrackerFragmentDirections.actionSleepTrackerToSleepQuality(it.id))
                sleepTrackerViewModel.doneNavigation()
            }
        })

        sleepTrackerViewModel.showSnackbarEvent.observe(this, Observer {
            if (it == true) {
                Snackbar.make(
                    activity!!.findViewById(android.R.id.content),
                    getString(R.string.cleared_message), Snackbar.LENGTH_SHORT
                ).show()
                sleepTrackerViewModel.doneShowingSnackbar()
            }
        })

        sleepTrackerViewModel.nights.observe(viewLifecycleOwner, Observer {
            it?.let { adapter.submitList(it) }
        })

        sleepTrackerViewModel.navigateToSleepDataQuality.observe(this, Observer {
            it?.let {
                this.findNavController()
                    .navigate(SleepTrackerFragmentDirections.actionSleepTrackerToSleepDetail(it))
                sleepTrackerViewModel.onSleepDataQualityNavigated()
            }
        })

        return binding.root
    }

}
