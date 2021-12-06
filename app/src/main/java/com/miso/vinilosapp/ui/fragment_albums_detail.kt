    package com.miso.vinilosapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.miso.vinilosapp.R
import com.miso.vinilosapp.databinding.FragmentAlbumsDetailBinding
import com.miso.vinilosapp.models.Track
import com.miso.vinilosapp.ui.adapters.TracksAdapter
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
    private var albumId: Int = 0
    private var albumName: String? = null
    private var albumCover: String? = null
    private var albumRelease: String? = null
    private var albumDescription: String? = null
    private var albumGenre: String? = null
    private var albumRecord: String? = null
    private var albumTracks: Array<Track>? = null

    private var _binding: FragmentAlbumsDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView

    private lateinit var viewModel: AlbumViewModel
    private var trackAdapter: TracksAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            albumId = it.getInt("albumId",0)
            albumName = it.getString("name")
            albumCover = it.getString("cover")
            albumRelease = it.getString("releaseDate")
            albumDescription = it.getString("description")
            albumGenre = it.getString("genre")
            albumRecord = it.getString("recordLabel")
            albumTracks = it.getParcelableArray("albumTracks") as Array<Track>?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAlbumsDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        trackAdapter = TracksAdapter()
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.albumTracksRv
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = trackAdapter

            Log.d("Albums Detail_" , albumId.toString())

        Picasso.get().load(albumCover).into(view.findViewById<ImageView>(R.id.iVCoverAlbum))

        binding.textAlbumName.text = albumName
        binding.textAlbumDescription.text = albumDescription
        binding.textAlbumGenre.text = albumGenre
        binding.textAlbumRecordLabel.text = albumRecord
        binding.textAlbumReleaseDate.text = albumRelease

    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        trackAdapter!!.tracks = albumTracks!!

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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