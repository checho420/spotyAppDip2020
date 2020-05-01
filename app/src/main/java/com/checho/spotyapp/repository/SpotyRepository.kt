package com.checho.spotyapp.repository

import com.checho.spotyapp.models.*
import com.checho.spotyapp.service.ServiceFactory
import com.checho.spotyapp.service.SpotyServices
import retrofit2.Call
import java.lang.Exception

class SpotyRepository {

    private var spotyServices: SpotyServices

    init {
        val serviceFactory = ServiceFactory()
        spotyServices = serviceFactory.getInstanceSpotyService()
    }

    fun getArtist(): List<ArtistModel>{
        try {
            val call: retrofit2.Call<List<ArtistModel>> = spotyServices.getArtists()
            val response = call.execute()
            if (response.isSuccessful){
                return response.body()!!
            }else {
                throw Exception(response.errorBody().toString())
            }


        }catch (exception: Exception){
            throw exception

        }
    }

    fun getAlbums(artist: Int): List<AlbumModel> {
        try {
            val call: retrofit2.Call<List<AlbumObjectModel>> = spotyServices.getAlbums(artist)
            val response = call.execute()
            if (response.isSuccessful) {
                return response.body()!![0].albums
            } else {
                throw Exception(response.errorBody().toString())
            }
        } catch (exception: Exception) {
            throw exception
        }
    }

    fun getSongsByAlbum(albumId: Int): List<SongModel> {
        try {
            val call: retrofit2.Call<List<SongObjectModel>> = spotyServices.getSongsByAlbum(albumId)
            val response = call.execute()
            if (response.isSuccessful) {
                return response.body()!![0].songs
            } else {
                throw Exception(response.errorBody().toString())
            }

        } catch (e: Exception) {
            throw e
        }
    }


}