package com.checho.spotyapp.listener

import android.os.Bundle
import com.checho.spotyapp.models.AlbumModel
import java.util.*

interface ListenerAlbum {

    fun onClickedAlbum(
        bundle: Bundle?,
        album: AlbumModel
    )
}