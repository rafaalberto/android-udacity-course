package br.com.marsestate.overview

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import br.com.marsestate.R
import br.com.marsestate.databinding.GridViewItemBinding

class OverviewFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: GridViewItemBinding = DataBindingUtil.inflate(inflater, R.layout.grid_view_item, container, false)

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
