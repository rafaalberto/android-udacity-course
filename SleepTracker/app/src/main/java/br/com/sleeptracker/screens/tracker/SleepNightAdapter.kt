package br.com.sleeptracker.screens.tracker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.sleeptracker.R
import br.com.sleeptracker.convertDurationToFormatted
import br.com.sleeptracker.convertNumericQualityToString
import br.com.sleeptracker.database.entity.DailySleepQuality
import br.com.sleeptracker.databinding.ListItemSleepNightBinding

class SleepNightAdapter : ListAdapter<DailySleepQuality, SleepNightAdapter.ViewHolder>(SleepNightDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder private constructor(private val binding: ListItemSleepNightBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DailySleepQuality) {
            binding.sleep = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemSleepNightBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class SleepNightDiffCallback : DiffUtil.ItemCallback<DailySleepQuality>() {

    override fun areItemsTheSame(oldItem: DailySleepQuality, newItem: DailySleepQuality): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DailySleepQuality, newItem: DailySleepQuality): Boolean {
        return oldItem == newItem
    }

}