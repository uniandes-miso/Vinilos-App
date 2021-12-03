package com.miso.vinilosapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.miso.vinilosapp.R
import com.miso.vinilosapp.databinding.FragmentMusiciansDetailBinding
import com.miso.vinilosapp.models.Album
import com.miso.vinilosapp.ui.adapters.MusiciansAlbumsAdapter
import com.squareup.picasso.Picasso

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_musicians_detail.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_musicians_detail : Fragment() {
    // TODO: Rename and change types of parameters
    private var musicianId: Int = 0
    private var musicianName: String? = null
    private var musicianImage: String? = null
    private var musicianDescription: String? = null
    private var musicianAlbums: Array<Album>? = null

    private var _binding: FragmentMusiciansDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView

    private var musicianAlbumsAdapter: MusiciansAlbumsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            musicianId = it.getInt("Id")
            musicianName = it.getString("name")
            musicianImage = it.getString("image")
            musicianDescription = it.getString("description")
            musicianAlbums = it.getParcelableArray("musicianalbums") as Array<Album>?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMusiciansDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        musicianAlbumsAdapter = MusiciansAlbumsAdapter()
        return view //inflater.inflate(R.layout.fragment_musicians_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.musicianAlbumRv
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = musicianAlbumsAdapter

            Picasso.get().load(musicianImage).into(view.findViewById<ImageView>(R.id.musicianImage))

        var tMusicianId : TextView = view.findViewById<TextView>(R.id.textMusicianId)
        tMusicianId.setText(musicianId.toString())

        var tMusicianName : TextView = view.findViewById<TextView>(R.id.textMusicianName)
        tMusicianName.setText(musicianName.toString())

        var tMusicianDescription : TextView = view.findViewById<TextView>(R.id.textMusicianDescription)
        tMusicianDescription.setText(musicianDescription.toString())

        musicianAlbumsAdapter!!.albumes = musicianAlbums!!

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment fragment_musicians_detail.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragment_musicians_detail().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}