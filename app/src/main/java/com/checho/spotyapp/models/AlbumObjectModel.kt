package com.checho.spotyapp.models

import com.google.gson.annotations.SerializedName

data class AlbumObjectModel(

    @SerializedName("artist")
    val artista: Int,
    val albums: List<AlbumModel>
)