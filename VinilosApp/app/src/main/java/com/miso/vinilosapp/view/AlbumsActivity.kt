package com.miso.vinilosapp.view

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.miso.vinilosapp.R
import com.miso.vinilosapp.model.Album
import com.miso.vinilosapp.viewModel.AlbumViewModel

class AlbumsActivity : AppCompatActivity() {

    private lateinit var viewModel: AlbumViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_albums)
        viewModel = AlbumViewModel(this.applicationContext as Application)
        val getResultTextView : TextView = findViewById(R.id.textListAlbums)
        getResultTextView.text = "Response is: baaaaadddd  ${viewModel.albums.value?.get(0)?.name}"

    }


    fun goBackMenu(view: android.view.View) {
        startActivity(Intent(view.context, MenuActivity::class.java))
    }
}