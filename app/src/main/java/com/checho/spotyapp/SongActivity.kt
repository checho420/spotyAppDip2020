package com.checho.spotyapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.checho.spotyapp.listener.ListenerSong
import com.checho.spotyapp.models.AlbumModel
import com.checho.spotyapp.models.SongModel
import com.checho.spotyapp.repository.SpotyRepository
import com.checho.spotyapp.utils.ITEM_ALBUM
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_song.*
import java.lang.Exception

class SongActivity : AppCompatActivity(), ListenerSong {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song)

        rvSong.layoutManager = LinearLayoutManager(this)

     /*   val song = (0..20).map {
            SongModel("cancion $it", "1:10", "")
        }
        val adapterSong = SongAdapter(song)
        rvSong.adapter = adapterSong

      */

        var album: AlbumModel? = null
        intent?.let {
            album = it.getParcelableExtra<AlbumModel>(ITEM_ALBUM)
            Picasso.with(this).load(album!!.image).into(imgHeaderDetail)
            txtTitleDetail.text = album!!.name
        }

        createThreadToGetSongs(album!!.id)
    }

    private fun createThreadToGetSongs(idAlbum: Int) {
        var thread = Thread(Runnable {
            getSongsFromRepository(idAlbum)
        })
        thread.start()
    }

    private fun getSongsFromRepository(idAlbum: Int) {
        try {
            val repository = SpotyRepository()
            val result = repository.getSongsByAlbum(idAlbum)
            loadAdapter(result)

        } catch (e: Exception) {
            runOnUiThread{
                Toast.makeText(this, e.message ?: "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadAdapter(result: List<SongModel>) {
        runOnUiThread {
            val adapterSong = SongAdapter(result, this)
            rvSong.adapter = adapterSong
        }
    }

    override fun onClickedSong(urlSong: String) {
        val intent = Intent(Intent.ACTION_VIEW).setData(Uri.parse(urlSong))
        if (intent.resolveActivity(this.packageManager)!= null){
            startActivity(intent)
        }
    }
}
