package br.com.marsstate.overview

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import br.com.marsstate.R
import br.com.marsstate.databinding.FragmentOverviewBinding

class OverviewFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentOverviewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_overview, container, false)

        val overviewViewModel = ViewModelProviders.of(this).get(OverviewViewModel::class.java)
        binding.viewModel = overviewViewModel

        binding.lifecycleOwner = this

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

}
