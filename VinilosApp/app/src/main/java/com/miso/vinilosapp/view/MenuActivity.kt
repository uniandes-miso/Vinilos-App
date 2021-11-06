package com.miso.vinilosapp.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.miso.vinilosapp.R


class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
    }

    fun showAlbums(view: android.view.View) {
        startActivity(Intent(view.context, AlbumsActivity::class.java))
    }

    fun showArtists(view: android.view.View) {
        startActivity(Intent(view.context, MusiciansActivity::class.java))
    }
}
