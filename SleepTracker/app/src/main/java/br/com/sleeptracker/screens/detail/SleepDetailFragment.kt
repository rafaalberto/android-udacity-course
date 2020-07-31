package br.com.sleeptracker.screens.detail

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
import br.com.sleeptracker.databinding.FragmentSleepDetailBinding

class SleepDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentSleepDetailBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_sleep_detail, container, false)

        val application = requireNotNull(this.activity).application
        val arguments = SleepDetailFragmentArgs.fromBundle(arguments!!)

        val dataSource = SleepDatabase.getInstance(application).dailySleepQualityDao
        val viewModelFactory = SleepDetailViewModelFactory(arguments.sleepNightKey, dataSource)

        val sleepDetailViewModel = ViewModelProviders.of(this, viewModelFactory).get(SleepDetailViewModel::class.java)
        binding.sleepDetailViewModel = sleepDetailViewModel
        binding.lifecycleOwner = this

        sleepDetailViewModel.navigateToSleepTracker.observe(this, Observer {
            if(it == true) {
                this.findNavController().navigate(SleepDetailFragmentDirections.actionSleepDetailToSleepTracker())
                sleepDetailViewModel.doneNavigation()
            }
        })

        return binding.root
    }

}
