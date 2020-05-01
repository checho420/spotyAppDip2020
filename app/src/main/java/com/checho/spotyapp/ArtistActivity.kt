package com.checho.spotyapp

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.checho.spotyapp.listener.ListenerArtist
import com.checho.spotyapp.models.ArtistModel
import com.checho.spotyapp.repository.SpotyRepository
import com.checho.spotyapp.utils.ITEM_ALBUM
import com.checho.spotyapp.utils.ITEM_ARTISTA
import com.checho.spotyapp.utils.ValidateInternet
import kotlinx.android.synthetic.main.activity_artist.*
import java.lang.Exception

class ArtistActivity : AppCompatActivity(), ListenerArtist {

    val validateInternet = ValidateInternet()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artist)

        validateInternet()

    }

    private fun validateInternet() {
        if (validateInternet.isInternetAvailable(this)) {
            createThreadToGetArtist()
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

    private fun createThreadToGetArtist() {
        var thread = Thread(Runnable {
            getArtistFromRepository()
        })
        thread.start()
    }

    private fun getArtistFromRepository() {
        try {
            val repository = SpotyRepository()
            val result = repository.getArtist()
            loadAdapter(result)


        } catch (e: Exception) {
            Toast.makeText(this, e.message ?: "Error", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadAdapter(result: List<ArtistModel>) {
        runOnUiThread {
            rvArtist.layoutManager = GridLayoutManager(this, 2)
            rvArtist.adapter = ArtistAdapter(result, this)
        }

    }

    override fun onClickedArtist(bundle: Bundle?, artist: ArtistModel) {
        val intent = Intent(this, AlbumActivity::class.java)
        intent.putExtra(ITEM_ARTISTA, artist)
        startActivity(intent, bundle)
    }
}
