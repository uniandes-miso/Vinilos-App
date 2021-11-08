package com.miso.vinilosapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.miso.vinilosapp.R
import com.miso.vinilosapp.viewModel.AlbumViewModel

class AlbumsActivity : AppCompatActivity() {

    private lateinit var viewModel: AlbumViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_albums)
    }


    fun goBackMenu(view: android.view.View) {
        startActivity(Intent(view.context, MenuActivity::class.java))
    }
}