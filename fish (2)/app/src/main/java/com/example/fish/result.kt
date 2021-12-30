package com.example.fish

import android.content.Intent
import android.graphics.BitmapFactory
import android.media.Image
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class result : AppCompatActivity()  {

    lateinit var morebtn : Button
    lateinit var resultimg : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.result)

        morebtn = findViewById<Button>(R.id.morebtn)
        resultimg = findViewById<ImageView>(R.id.resultimg)

        val extras = intent.extras
        val i = extras!!.getInt("integer")
        val d = extras!!.getDouble("double")
        val byteArray = intent.getByteArrayExtra("image")
        val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray!!.size)
        resultimg.setImageBitmap(bitmap)

        morebtn.setOnClickListener {
            val intent = Intent(applicationContext, selectimage::class.java)
            startActivity(intent)
        }
    }
}