package com.miso.vinilosapp.view

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.android.volley.Response
import com.miso.vinilosapp.R
import com.miso.vinilosapp.repository.NetworkServiceAdapter
import com.miso.vinilosapp.viewModel.AlbumViewModel


class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun showVinilosAppMenu(view: android.view.View) {
        startActivity(Intent(view.context, MenuActivity::class.java))
    }
}