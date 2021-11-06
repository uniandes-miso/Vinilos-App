package com.miso.vinilosapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.miso.vinilosapp.R

class ArtistsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artists)
    }

    fun goBackMenu(view: android.view.View) {
        startActivity(Intent(view.context, MenuActivity::class.java))
    }
}