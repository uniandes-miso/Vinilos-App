package com.miso.vinilosapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.miso.vinilosapp.R
import com.miso.vinilosapp.databinding.MusicianAlbumItemBinding
import com.miso.vinilosapp.databinding.MusicianItemBinding
import com.miso.vinilosapp.models.Album
import com.miso.vinilosapp.models.Musician
import com.miso.vinilosapp.models.Track
import com.squareup.picasso.Picasso

class MusiciansAlbumsAdapter: RecyclerView.Adapter<MusiciansAlbumsAdapter.MusiciansAlbumsViewHolder>() {

    var albumes : Array<Album> = emptyArray()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    class MusiciansAlbumsViewHolder(val viewDataBinding: MusicianAlbumItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.musician_album_item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusiciansAlbumsViewHolder {
        val withDataBinding: MusicianAlbumItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            MusiciansAlbumsAdapter.MusiciansAlbumsViewHolder.LAYOUT,
            parent, false)
        return MusiciansAlbumsAdapter.MusiciansAlbumsViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: MusiciansAlbumsViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.album = albumes.get(position)
        }
    }

    override fun getItemCount(): Int {
        return albumes.size
    }
}