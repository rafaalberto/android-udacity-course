package br.com.sleeptracker.screens.tracker

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import br.com.sleeptracker.R
import br.com.sleeptracker.convertDurationToFormatted
import br.com.sleeptracker.convertNumericQualityToString
import br.com.sleeptracker.database.entity.DailySleepQuality

@BindingAdapter("sleepDurationFormatted")
fun TextView.setSleepDurationFormatted(item: DailySleepQuality?) {
    item?.let {
        text = convertDurationToFormatted(item.startTimeMilli, item.endTimeMilli, context.resources)
    }
}

@BindingAdapter("sleepQualityString")
fun TextView.setSleepQualityString(item: DailySleepQuality?) {
    item?.let {
        text = convertNumericQualityToString(item.qualityRating, context.resources)
    }
}

@BindingAdapter("sleepImage")
fun ImageView.setSleepImage(item: DailySleepQuality?) {
    item?.let {
        setImageResource(when (item.qualityRating) {
            0 -> R.drawable.ic_sleep_0
            1 -> R.drawable.ic_sleep_1
            2 -> R.drawable.ic_sleep_2
            3 -> R.drawable.ic_sleep_3
            4 -> R.drawable.ic_sleep_4
            5 -> R.drawable.ic_sleep_5
            else -> R.drawable.ic_sleep_active
        })
    }
}