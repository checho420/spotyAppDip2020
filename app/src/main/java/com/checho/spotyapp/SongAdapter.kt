package com.checho.spotyapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.checho.spotyapp.listener.ListenerSong
import com.checho.spotyapp.models.SongModel

class SongAdapter(
    val listSong: List<SongModel>,
    val songListener: ListenerSong
) : RecyclerView.Adapter<SongHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongHolder {
        return SongHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_song,
                parent,
                false
            ), songListener
        )
    }

    override fun getItemCount(): Int {
        return listSong.size
    }

    override fun onBindViewHolder(holder: SongHolder, position: Int) {
        holder.bindSong(listSong[position])
    }

}