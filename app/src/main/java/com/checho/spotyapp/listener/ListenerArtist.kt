package com.checho.spotyapp.listener

import android.os.Bundle
import com.checho.spotyapp.models.ArtistModel

interface ListenerArtist {
    fun onClickedArtist(
        bundle: Bundle?,
        album: ArtistModel
    )
}