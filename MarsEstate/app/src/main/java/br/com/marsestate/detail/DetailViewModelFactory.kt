package br.com.marsestate.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.marsestate.network.MarsProperty

@Suppress("unchecked_cast")
class DetailViewModelFactory(private val marsProperty: MarsProperty, private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DetailViewModel::class.java)) return DetailViewModel(marsProperty, application) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
