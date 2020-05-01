package com.checho.spotyapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AlbumModel(
    val id: Int,
    val name: String,
    val image: String
    //val spotify_url: String,
    //val total_tracks: Int
) : Parcelable

