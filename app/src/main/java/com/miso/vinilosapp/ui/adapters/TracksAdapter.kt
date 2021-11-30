package com.miso.vinilosapp.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.miso.vinilosapp.R
import com.miso.vinilosapp.databinding.TrackItemBinding
import com.miso.vinilosapp.models.Track

class TracksAdapter: RecyclerView.Adapter<TracksAdapter.TrackViewHolder>() {

    var tracks : Array<Track> = emptyArray()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TracksAdapter.TrackViewHolder {
        Log.d("track adapter" , "On create view")
        val withDataBinding: TrackItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            TracksAdapter.TrackViewHolder.LAYOUT,
            parent,
            false)
        return TracksAdapter.TrackViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.track = tracks.get(position)
        }

    }

    override fun getItemCount(): Int {
        return tracks.size
    }
    class TrackViewHolder(val viewDataBinding: TrackItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.track_item
        }
    }
}