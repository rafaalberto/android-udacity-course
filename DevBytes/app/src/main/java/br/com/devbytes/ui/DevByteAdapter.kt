package br.com.devbytes.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.devbytes.R
import br.com.devbytes.databinding.DevbyteItemBinding
import br.com.devbytes.domain.Video

class DevByteAdapter(private val callback: VideoClick) : RecyclerView.Adapter<DevByteViewHolder>() {
    var videos: List<Video> = emptyList()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DevByteViewHolder {
        val withDataBinding: DevbyteItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            DevByteViewHolder.LAYOUT,
            parent,
            false)
        return DevByteViewHolder(withDataBinding)
    }

    override fun getItemCount() = videos.size

    override fun onBindViewHolder(holder: DevByteViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.video = videos[position]
            it.videoCallback = callback
        }
    }
}

class DevByteViewHolder(val viewDataBinding: DevbyteItemBinding) : RecyclerView.ViewHolder(viewDataBinding.root) {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.devbyte_item
    }
}
