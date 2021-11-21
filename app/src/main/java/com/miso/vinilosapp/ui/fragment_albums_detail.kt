package com.miso.vinilosapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.miso.vinilosapp.R
import com.miso.vinilosapp.models.Album
import com.miso.vinilosapp.ui.adapters.AlbumsAdapter
import com.miso.vinilosapp.viewmodels.AlbumViewModel
import com.squareup.picasso.Picasso

// TODO: Rename parameter arguments, choose names that match
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val ARG_PARAM3 = "param3"
private const val ARG_PARAM4 = "param4"
private const val ARG_PARAM5 = "param5"
private const val ARG_PARAM6 = "param6"
private const val ARG_PARAM7 = "param7"
/**
 * A simple [Fragment] subclass.
 * Use the [fragment_albums_detail.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_albums_detail : Fragment() {
    // TODO: Rename and change types of parameters
    private var albumId: Int? = null
    private var albumName: String? = null
    private var albumCover: String? = null
    private var albumRelease: String? = null
    private var albumDescription: String? = null
    private var albumGenre: String? = null
    private var albumRecord: String? = null

    private var albumSelected: Album? = null
    private lateinit var viewModel: AlbumViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            albumId = it.getInt("albumId")
            albumName = it.getString("name")
            albumCover = it.getString("cover")
            albumRelease = it.getString("releaseDate")
            albumDescription = it.getString("description")
            albumGenre = it.getString("genre")
            albumRecord = it.getString("recordLabel")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        viewModel = ViewModelProvider(this, AlbumViewModel.Factory(activity.application)).get(AlbumViewModel::class.java)
        //albumSelected =
        Log.d("Album detail Size albums ", listOf(viewModel.albums).size.toString())
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_albums_detail, container, false)
        //textAlbumName
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("Albums Detail" , albumId.toString())

        Picasso.get().load(albumCover).into(view.findViewById<ImageView>(R.id.iVCoverAlbum))

        var tAlbumName : TextView = view.findViewById<TextView>(R.id.textAlbumName)
        tAlbumName.setText(albumName)
        var tAlbumDescription : TextView = view.findViewById<TextView>(R.id.textAlbumDescription)
        tAlbumDescription.setText(albumDescription)
        var tAlbumGenre : TextView = view.findViewById<TextView>(R.id.textAlbumGenre)
        tAlbumGenre.setText(albumGenre)
        var tAlbumRecordLabel : TextView = view.findViewById<TextView>(R.id.textAlbumRecordLabel)
        tAlbumRecordLabel.setText(albumRecord)
        var tAlbumRelease : TextView = view.findViewById<TextView>(R.id.textAlbumReleaseDate)
        tAlbumRelease.setText(albumRelease)


    }
    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(albumId: Int,name:String,cover:String,releaseDate:String,description:String,genre:String,recordLabel:String) =
            fragment_albums_detail().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, albumId.toString())
                    putString(ARG_PARAM2, name )
                    putString(ARG_PARAM3, cover )
                    putString(ARG_PARAM4, releaseDate)
                    putString(ARG_PARAM5, description)
                    putString(ARG_PARAM6, genre)
                    putString(ARG_PARAM7, recordLabel)
                }
            }
    }
}