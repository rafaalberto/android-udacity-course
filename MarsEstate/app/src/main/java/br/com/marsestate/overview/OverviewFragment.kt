package br.com.marsestate.overview

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import br.com.marsestate.R
import br.com.marsestate.databinding.FragmentOverviewBinding

class OverviewFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val binding: FragmentOverviewBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_overview, container, false)

        val overviewViewModel = ViewModelProviders.of(this).get(OverviewViewModel::class.java)
        binding.viewModel = overviewViewModel

        binding.lifecycleOwner = this

        binding.photosGrid.adapter = PhotoGripAdapter(PhotoGripAdapter.OnClickListener {
            overviewViewModel.displayPropertyDetails(it)
        })

        overviewViewModel.navigateToSelectedProperty.observe(this, Observer {
            if (null != it) {
                this.findNavController().navigate(OverviewFragmentDirections.actionOverviewFragmentToDetailFragment(it))
                overviewViewModel.displayPropertyDetailsComplete()
            }
        })

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

}
