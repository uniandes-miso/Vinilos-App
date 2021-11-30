package com.miso.vinilosapp.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.miso.vinilosapp.R
import com.miso.vinilosapp.databinding.AlbumItemBinding
import com.miso.vinilosapp.models.Album
import com.miso.vinilosapp.models.Track
import com.miso.vinilosapp.ui.albumDirections
import com.squareup.picasso.Picasso


class AlbumsAdapter : RecyclerView.Adapter<AlbumsAdapter.AlbumViewHolder>(){

    var albums :List<Album> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        Log.d("Albums adapter" , "On create view")
        val withDataBinding: AlbumItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            AlbumViewHolder.LAYOUT,
            parent,
            false)
        return AlbumViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.album = albums[position]
        }
        holder.bind(albums[position])
        holder.viewDataBinding.root.setOnClickListener {
            val action = albumDirections.actionAlbumFragmentToFragmentAlbumsDetail(
                albums[position].albumId,
                albums[position].name,
                albums[position].cover,
                albums[position].releaseDate,
                albums[position].description,
                albums[position].genre,
                albums[position].recordLabel,
                albums[position].tracks!!.toTypedArray()
            )
            // Navigate using that action
            holder.viewDataBinding.root.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return albums.size
    }


    class AlbumViewHolder(val viewDataBinding: AlbumItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.album_item
        }

        fun bind(album: Album){
            Picasso.get().load(album.cover).into(viewDataBinding.iVCoverAlbum)
        }
    }


}