package com.checho.spotyapp

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.checho.spotyapp.listener.ListenerSong
import com.checho.spotyapp.models.SongModel
import kotlinx.android.synthetic.main.item_song.view.*

class SongHolder(val view: View, val listener: ListenerSong) : RecyclerView.ViewHolder(view) {
    fun bindSong(songModel: SongModel) {
        view.txtTitleSong.text = songModel.name
        view.txtDurationSong.text = calculateTime(songModel.time)
        view.setOnClickListener {
            listener.onClickedSong(songModel.url)


        }
    }

    private fun calculateTime(time: String): String {
        val min = time.toInt() / 1000 / 60
        val sec = time.toInt() / 1000 % 60
        return "$min:${if (sec < 10) "0$sec" else sec}"
    }
}