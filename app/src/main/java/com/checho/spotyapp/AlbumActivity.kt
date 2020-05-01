package com.checho.spotyapp

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.checho.spotyapp.listener.ListenerAlbum
import com.checho.spotyapp.models.AlbumModel
import com.checho.spotyapp.models.ArtistModel
import com.checho.spotyapp.repository.SpotyRepository
import com.checho.spotyapp.utils.ITEM_ALBUM
import com.checho.spotyapp.utils.ITEM_ARTISTA
import com.checho.spotyapp.utils.ValidateInternet
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_album.*
import kotlinx.android.synthetic.main.activity_song.*
import kotlinx.android.synthetic.main.item_artist.*
import java.lang.Exception

class AlbumActivity : AppCompatActivity(), ListenerAlbum {

    val validateInternet = ValidateInternet()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)

        validateInternet()
    }

    private fun validateInternet() {
        if (validateInternet.isInternetAvailable(this)) {

            var artist: ArtistModel? = null
            intent?.let {
                artist = it.getParcelableExtra<ArtistModel>(ITEM_ARTISTA)
                Picasso.with(this).load(artist!!.image).into(imgAlbumArtist)
            }
            createThreadToGetAlbum(artist!!.id)
        } else {
            AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(R.string.error_internet)
                .setPositiveButton("Reintentar") { _, _ ->
                    validateInternet()
                }
                .setNegativeButton("Salir") { listener, _ ->
                    //listener.dismiss()
                    finish()
                }
                .create()
                .show()
        }
    }

    private fun createThreadToGetAlbum(idArtist: Int) {
        var thread = Thread(Runnable {
            getAlbumsFromRepository(idArtist)
        })
        thread.start()
    }

    private fun getAlbumsFromRepository(idArtist: Int) {
        try {
            val repository = SpotyRepository()
            val result = repository.getAlbums(idArtist)
            loadAdapter(result)


        } catch (e: Exception) {
            Toast.makeText(this, e.message ?: "Error", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadAdapter(result: List<AlbumModel>) {
        runOnUiThread {
            recyclerViewAlbums.layoutManager = GridLayoutManager(this, 2)
            recyclerViewAlbums.adapter = AlbumAdapter(result, this)
        }

    }

    override fun onClickedAlbum(bundle: Bundle?, album: AlbumModel) {
        val intent = Intent(this, SongActivity::class.java)
        intent.putExtra(ITEM_ALBUM, album)
        startActivity(intent, bundle)
    }
}
