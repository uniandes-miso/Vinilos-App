package com.miso.vinilosapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.miso.vinilosapp.R
import com.miso.vinilosapp.databinding.FragmentCollectorsDetailBinding
import com.miso.vinilosapp.databinding.FragmentMusiciansDetailBinding
import com.miso.vinilosapp.models.Comment
import com.miso.vinilosapp.ui.adapters.CommentsAdapter
import com.miso.vinilosapp.ui.adapters.MusiciansAlbumsAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [collectors_detail.newInstance] factory method to
 * create an instance of this fragment.
 */
class collectors_detail : Fragment() {
    // TODO: Rename and change types of parameters
    private var collectorId: Int = 0
    private var name: String? = null
    private var telephone: String? = null
    private var email: String? = null
    private var comments: Array<Comment>? = null
    private var commentsAdapter: CommentsAdapter? = null
    private var _binding: FragmentCollectorsDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerViewComments: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            collectorId = it.getInt("id")
            name = it.getString("name")
            telephone = it.getString("telephone")
            email = it.getString("email")
            comments = it.getParcelableArray("comments") as Array<Comment>?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("OncreateView Collectors detail", comments.toString())
        _binding = FragmentCollectorsDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        commentsAdapter = CommentsAdapter()
        return view //inflater.inflate(R.layout.fragment_musicians_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerViewComments = binding.collectorCommentRv
        recyclerViewComments.layoutManager = LinearLayoutManager(context)
        recyclerViewComments.adapter = commentsAdapter

        super.onViewCreated(view, savedInstanceState)
        var tCollectorName : TextView = view.findViewById<TextView>(R.id.textCollectorName)
        tCollectorName.setText(name.toString())
        var tCollectorEmail : TextView = view.findViewById<TextView>(R.id.textCollectorEmail)
        tCollectorEmail.setText(email.toString())
        var tCollectorPhone : TextView = view.findViewById<TextView>(R.id.textCollectorTelephone)
        tCollectorPhone.setText(telephone.toString())

        Log.d("Collectors detail", comments?.size.toString())
        //commentsAdapter!!.comments = comments!!
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
         * @return A new instance of fragment collectors_detail.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            collectors_detail().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}