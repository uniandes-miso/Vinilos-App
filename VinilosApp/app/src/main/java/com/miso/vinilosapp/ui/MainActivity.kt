package com.miso.vinilosapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.miso.vinilosapp.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun showVinilosAppMenu(view: android.view.View) {
        startActivity(Intent(view.context, MenuActivity::class.java))
    }
}