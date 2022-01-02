package com.example.fish

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.github.edsergeev.TextFloatingActionButton

class MainActivity : AppCompatActivity() {

    lateinit var start : Button
    lateinit var combtn : TextFloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        start = findViewById<Button>(R.id.start)
        combtn = findViewById<TextFloatingActionButton>(R.id.combtn)

        start.setOnClickListener {
            val intent = Intent(applicationContext, selectimage::class.java)
            startActivity(intent)
        }

        combtn.setOnClickListener {
            val intent = Intent(applicationContext, Community::class.java)
            startActivity(intent)
        }
    }
}