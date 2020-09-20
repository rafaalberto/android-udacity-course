package br.com.devbytes.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.devbytes.domain.Video

@Entity
data class VideoEntity (
    @PrimaryKey
    val url: String,
    val updated: String,
    val title: String,
    val description: String,
    val thumbnail: String
)

fun List<VideoEntity>.asDomainModel(): List<Video> {
    return map {
        Video(
            url = it.url,
            title = it.title,
            description = it.description,
            updated = it.updated,
            thumbnail = it.thumbnail
        )
    }
}
