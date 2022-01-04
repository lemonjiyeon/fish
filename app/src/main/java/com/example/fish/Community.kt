package com.example.fish

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fish.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Community: AppCompatActivity()  {

    lateinit var backbtn : FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.community)

        backbtn = findViewById<FloatingActionButton>(R.id.backbtn)

        backbtn.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            finish()
            startActivity(intent)
        }
    }
}