package com.example.fish

import android.content.Intent
import android.graphics.BitmapFactory
import android.media.Image
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.component1
import androidx.core.util.component2
import com.example.fish.tflite.ClassifierWithModel
import org.w3c.dom.Text
import java.io.IOException
import java.util.*

class result : AppCompatActivity()  {
    lateinit var cls : ClassifierWithModel
    lateinit var morebtn : Button
    lateinit var resultimg : ImageView
    lateinit var resulttxt : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.result)

        cls = ClassifierWithModel(this)
        try{
            cls.init()
        }catch (e : IOException){
            e.printStackTrace()
        }

        morebtn = findViewById<Button>(R.id.morebtn)
        resultimg = findViewById<ImageView>(R.id.resultimg)
        resulttxt = findViewById<TextView>(R.id.resulttxt)

        val byteArray = intent.getByteArrayExtra("image")
        val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray!!.size)

        if(bitmap != null){
            val (first) = cls.classify(bitmap)
            val resultStr: String =
                java.lang.String.format(Locale.ENGLISH, "%s", first)
            resultimg.setImageBitmap(bitmap)
            resulttxt.setText(resultStr)

        }

        morebtn.setOnClickListener {
            val intent = Intent(applicationContext, selectimage::class.java)
            finish()
            cls.finish()
            startActivity(intent)
        }
    }
}