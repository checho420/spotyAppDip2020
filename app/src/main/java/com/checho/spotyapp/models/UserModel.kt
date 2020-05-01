package com.checho.spotyapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserModel (

    val name: String,
    val userName: String,
    val email: String,
    val password: String,
    val _id: String? = null

): Parcelable