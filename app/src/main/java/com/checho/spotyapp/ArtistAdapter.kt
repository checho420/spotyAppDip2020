package com.checho.spotyapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.RecyclerView
import com.checho.spotyapp.listener.ListenerAlbum
import com.checho.spotyapp.listener.ListenerArtist
import com.checho.spotyapp.models.AlbumModel
import com.checho.spotyapp.models.ArtistModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_album.view.*
import kotlinx.android.synthetic.main.item_album.view.txtTitle
import kotlinx.android.synthetic.main.item_artist.view.*

class ArtistAdapter(val data: List<ArtistModel>, val listener: ListenerArtist) :
    RecyclerView.Adapter<ArtistAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_artist, parent, false)
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bindItem(data[position])
    }

    inner class Holder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bindItem(itemArtistModel: ArtistModel) {
            view.txtTitleArtist.text = itemArtistModel.name
            Picasso.with(view.context).load(itemArtistModel.image).into(view.imgArtist)
            view.setOnClickListener {
                val intent = Intent(view.context, AlbumActivity::class.java)
                val params = ArrayList<androidx.core.util.Pair<View, String>>()
                params.add(androidx.core.util.Pair(view.imgArtist, "transitionArtistImage"))
                params.add(androidx.core.util.Pair(view.txtTitleArtist, "transitionArtistTitle"))

                val paramsArray: Array<androidx.core.util.Pair<View, String>> =
                    params.toTypedArray()
                val animation: ActivityOptionsCompat =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                        view.context as Activity,
                        *paramsArray
                    )
                //view.context.startActivity(intent, animation.toBundle())
                listener.onClickedArtist(animation.toBundle(), itemArtistModel)

            }
        }
    }
}