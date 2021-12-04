package com.miso.vinilosapp.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.miso.vinilosapp.R
import com.miso.vinilosapp.databinding.CommentItemBinding
import com.miso.vinilosapp.databinding.TrackItemBinding
import com.miso.vinilosapp.models.Comment
import com.miso.vinilosapp.models.Track


class CommentsAdapter: RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder>() {

    var comments : Array<Comment> = emptyArray()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsAdapter.CommentsViewHolder{
        Log.d("comments adapter" , "On create view")
        val withDataBinding: CommentItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            CommentsAdapter.CommentsViewHolder.LAYOUT,
            parent,
            false)
        return CommentsAdapter.CommentsViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: CommentsAdapter.CommentsViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.comment = comments.get(position)
        }

    }

    override fun getItemCount(): Int {
        return comments.size
    }

    class CommentsViewHolder(val viewDataBinding: CommentItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.comment_item
        }
    }

}