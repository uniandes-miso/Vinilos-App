package com.miso.vinilosapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.miso.vinilosapp.R

class activity_menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
    }

    fun showAlbums(view: android.view.View) {
        startActivity(Intent(view.context, activity_content::class.java))
    }
/*
    fun showArtists(view: android.view.View) {
        startActivity(Intent(view.context, MusiciansActivity::class.java))
    }*/

}