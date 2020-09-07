package br.com.devbytes.network

import br.com.devbytes.domain.Video
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VideoNetwork(
    val title: String,
    val description: String,
    val url: String,
    val updated: String,
    val thumbnail: String,
    val closedCaptions: String?)

@JsonClass(generateAdapter = true)
data class VideoNetworkContainer(val videos: List<VideoNetwork>)

fun VideoNetworkContainer.asDomainModel(): List<Video> {
    return videos.map {
        Video(
            title = it.title,
            description = it.description,
            url = it.url,
            updated = it.updated,
            thumbnail = it.thumbnail)
    }
}