package com.example.fish

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.Image
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.component1
import androidx.core.util.component2
import com.example.fish.tflite.ClassifierWithModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.w3c.dom.Text
import java.io.IOException
import java.util.*

class result : AppCompatActivity() , View.OnClickListener {
    lateinit var cls : ClassifierWithModel
    lateinit var morebtn : Button
    lateinit var resultimg : ImageView
    lateinit var resulttxt : TextView
    private var fab_open: Animation? = null
    private var fab_close: Animation? = null
    private var isFabOpen = false
    lateinit var fab : FloatingActionButton
    lateinit var fab1 : FloatingActionButton
    lateinit var fab2 : FloatingActionButton
    lateinit var home : FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.result)

        cls = ClassifierWithModel(this)
        try{
            cls.init()
        }catch (e : IOException){
            e.printStackTrace()
        }
        home = findViewById<View>(R.id.home) as FloatingActionButton
        morebtn = findViewById<Button>(R.id.morebtn)
        resultimg = findViewById<ImageView>(R.id.resultimg)
        resulttxt = findViewById<TextView>(R.id.resulttxt)

        fab_open = AnimationUtils.loadAnimation(applicationContext, R.anim.fab_open)
        fab_close = AnimationUtils.loadAnimation(applicationContext, R.anim.fab_close)
        fab = findViewById<View>(R.id.fab) as FloatingActionButton
        fab1 = findViewById<View>(R.id.fab1) as FloatingActionButton
        fab2 = findViewById<View>(R.id.fab2) as FloatingActionButton
        fab!!.setOnClickListener(this)
        fab1!!.setOnClickListener(this)
        fab2!!.setOnClickListener(this)


        val byteArray = intent.getByteArrayExtra("image")
        val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray!!.size)

        if(bitmap != null){
            val (first) = cls.classify(bitmap)
            val resultStr: String =
                java.lang.String.format(Locale.ENGLISH, "%s", first)
            resultimg.setImageBitmap(bitmap)
            resulttxt.setText(resultStr)

        }

        home.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
       }

        morebtn.setOnClickListener {
            val intent = Intent(applicationContext, selectimage::class.java)
            finish()
            cls.finish()
            startActivity(intent)
        }


    }

    override fun onClick(v: View) {
        val id = v.id
        when (id) {
            R.id.fab -> {
                anim()
            }
            R.id.fab1 -> {
                anim()
                Toast.makeText(this, "능성어의 특징", Toast.LENGTH_SHORT).show()
                var dialog = layoutInflater.inflate(R.layout.neungsunga,null)
                var dlglogin = AlertDialog.Builder(this)
                dlglogin.setView(dialog)

                dlglogin.setPositiveButton("확인") { dialog, which ->
                    null
                }
                dlglogin.show()
            }
            R.id.fab2 -> {
                anim()
                Toast.makeText(this, "다금바리의 특징", Toast.LENGTH_SHORT).show()
                var dialog = layoutInflater.inflate(R.layout.dagumbari,null)
                var dlglogin = AlertDialog.Builder(this)
                dlglogin.setView(dialog)

                dlglogin.setPositiveButton("확인") { dialog, which ->
                    null
                }
                dlglogin.show()
            }
        }
    }

    fun anim() {
        if (isFabOpen) {
            fab1!!.startAnimation(fab_close)
            fab2!!.startAnimation(fab_close)
            fab1!!.isClickable = false
            fab2!!.isClickable = false
            isFabOpen = false
        } else {
            fab1!!.startAnimation(fab_open)
            fab2!!.startAnimation(fab_open)
            fab1!!.isClickable = true
            fab2!!.isClickable = true
            isFabOpen = true
        }
    }
}