package com.checho.spotyapp.models

import com.google.gson.annotations.SerializedName

data class SongModel(
    val name: String,
    @SerializedName("duration_ms")
    val time: String,
    @SerializedName("spotify_url")
    val url: String

)
