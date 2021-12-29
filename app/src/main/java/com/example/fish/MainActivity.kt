package com.example.fish

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var start : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        start = findViewById<Button>(R.id.start)

        start.setOnClickListener {
            val intent = Intent(applicationContext, selectimage::class.java)
            startActivity(intent)
        }
    }
}