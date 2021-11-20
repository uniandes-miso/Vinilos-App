package com.miso.vinilosapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.miso.vinilosapp.R
import com.miso.vinilosapp.models.Album
import com.miso.vinilosapp.ui.adapters.AlbumsAdapter
import com.miso.vinilosapp.viewmodels.AlbumViewModel

// TODO: Rename parameter arguments, choose names that match
private const val ARG_PARAM1 = "param1"

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_albums_detail.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_albums_detail : Fragment() {
    // TODO: Rename and change types of parameters
    private var albumId: Int? = null
    private var albumSelected: Album? = null
    private lateinit var viewModel: AlbumViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            albumId = it.getInt("albumId")
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
        var tAlbumName : TextView = view.findViewById<TextView>(R.id.textAlbumName)
        tAlbumName.setText("Si yo supiera que me voy a morir mañana")
        var tAlbumDescription : TextView = view.findViewById<TextView>(R.id.textAlbumDescription)
        tAlbumDescription.setText("Album creado por silvestre")
        var tAlbumGenre : TextView = view.findViewById<TextView>(R.id.textAlbumGenre)
        tAlbumGenre.setText("Vallenato")
        var tAlbumRecordLabel : TextView = view.findViewById<TextView>(R.id.textAlbumRecordLabel)
        tAlbumGenre.setText("Sony music")


    }
    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(albumId: Int) =
            fragment_albums_detail().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, albumId.toString())
                }
            }
    }
}