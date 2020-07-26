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
            val resources = itemView.context.resources
            binding.sleepLength.text = convertDurationToFormatted(item.startTimeMilli, item.endTimeMilli, resources)
            binding.qualityString.text = convertNumericQualityToString(item.qualityRating, resources)
            binding.qualityImage.setImageResource(
                when (item.qualityRating) {
                    0 -> R.drawable.ic_sleep_0
                    1 -> R.drawable.ic_sleep_1
                    2 -> R.drawable.ic_sleep_2
                    3 -> R.drawable.ic_sleep_3
                    4 -> R.drawable.ic_sleep_4
                    5 -> R.drawable.ic_sleep_5
                    else -> R.drawable.ic_sleep_active
                }
            )
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