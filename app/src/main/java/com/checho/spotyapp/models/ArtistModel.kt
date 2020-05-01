package com.checho.spotyapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ArtistModel (

    val id: Int,
    val name: String,
    val image: String
    //val popularity: String

): Parcelable