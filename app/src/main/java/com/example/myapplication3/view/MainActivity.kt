package com.example.myapplication3.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication3.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun btnStartRecyclerClick(view: android.view.View) {
        val intent = Intent(this, RecyclerViewActivity::class.java)
        startActivity(intent)
    }
}