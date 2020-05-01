package com.checho.spotyapp.service

import com.checho.spotyapp.models.AlbumModel
import com.checho.spotyapp.models.AlbumObjectModel
import com.checho.spotyapp.models.ArtistModel
import com.checho.spotyapp.models.SongObjectModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SpotyServices {

    @GET("artists")
    fun getArtists():Call<List<ArtistModel>>

    @GET("albums")
    fun getAlbums(@Query("artist") value: Int): Call<List<AlbumObjectModel>>

    @GET("songs")
    fun getSongsByAlbum(@Query("album") value: Int): Call<List<SongObjectModel>>


}