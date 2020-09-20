package br.com.devbytes.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import br.com.devbytes.database.VideosDatabase
import br.com.devbytes.database.asDomainModel
import br.com.devbytes.domain.Video
import br.com.devbytes.network.Network
import br.com.devbytes.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VideosRepository(private val database: VideosDatabase) {

    val videos: LiveData<List<Video>> = Transformations.map(database.videoDao.getVideos()) {
        it.asDomainModel()
    }

    suspend fun refreshVideos() {
        withContext(Dispatchers.IO) {
            val playlists = Network.devBytes.getPlaylistAsync().await()
            database.videoDao.insertAll(*playlists.asDatabaseModel())
        }
    }

}