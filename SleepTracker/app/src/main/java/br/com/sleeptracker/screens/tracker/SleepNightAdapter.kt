package br.com.sleeptracker.screens.tracker

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.sleeptracker.R
import br.com.sleeptracker.TextItemViewHolder
import br.com.sleeptracker.database.entity.DailySleepQuality

class SleepNightAdapter: RecyclerView.Adapter<TextItemViewHolder>() {

    var data = listOf<DailySleepQuality>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val item = data[position]
        holder.textView.text = item.qualityRating.toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.text_item_view, parent, false) as TextView

        return TextItemViewHolder(view)
    }

}