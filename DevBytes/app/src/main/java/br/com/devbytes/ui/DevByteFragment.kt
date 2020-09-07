package br.com.devbytes.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.devbytes.R
import br.com.devbytes.databinding.FragmentDevByteBinding
import br.com.devbytes.domain.Video
import br.com.devbytes.viewmodel.DevByteViewModel
import br.com.devbytes.viewmodel.DevByteViewModelFactory

class DevByteFragment : Fragment() {

    private val viewModel: DevByteViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProviders.of(this, DevByteViewModelFactory(activity.application)).get(DevByteViewModel::class.java)
    }

    private var viewModelAdapter: DevByteAdapter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.playlist.observe(viewLifecycleOwner, Observer<List<Video>> { videos ->
            videos?.apply { viewModelAdapter?.videos = videos }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentDevByteBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_dev_byte,
            container,
            false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        viewModelAdapter = DevByteAdapter(VideoClick {
            val packageManager = context?.packageManager ?: return@VideoClick

            var intent = Intent(Intent.ACTION_VIEW, it.launchUri)
            if(intent.resolveActivity(packageManager) == null) {
                intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.url))
            }

            startActivity(intent)
        })

        binding.root.findViewById<RecyclerView>(R.id.recycler_view).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewModelAdapter
        }

        return binding.root
    }

    private val Video.launchUri: Uri
    get() {
        val httpUri = Uri.parse(url)
        return Uri.parse("vnd.youtube:" + httpUri.getQueryParameter("v"))
    }

}

class VideoClick(val block: (Video) -> Unit) {
    fun onClick(video: Video) = block(video)
}