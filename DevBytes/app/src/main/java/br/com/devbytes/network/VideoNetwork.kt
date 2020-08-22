package br.com.devbytes.network

data class VideoNetwork(
    val title: String,
    val description: String,
    val url: String,
    val updated: String,
    val thumbnail: String)

data class VideoNetworkContainer(val videos: List<VideoNetwork>)