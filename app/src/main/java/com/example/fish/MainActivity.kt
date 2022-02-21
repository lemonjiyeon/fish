package com.example.fish

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    lateinit var start : Button
    lateinit var combtn : FloatingActionButton
    lateinit var phone : FloatingActionButton
    lateinit var call_text : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        start = findViewById<Button>(R.id.start)
        combtn = findViewById<FloatingActionButton>(R.id.combtn)
        phone = findViewById<FloatingActionButton>(R.id.phone)
//        call_text = findViewById<TextView>(R.id.call_text)
//
//        call_text.movementMethod(ScrollingMovementMethod())

        start.setOnClickListener {
            val intent = Intent(applicationContext, selectimage::class.java)
            startActivity(intent)
        }

        combtn.setOnClickListener {
            val intent = Intent(applicationContext, Community::class.java)
            startActivity(intent)
        }

        phone.setOnClickListener {
            Toast.makeText(this, "기관전화번호", Toast.LENGTH_SHORT).show()
            var dialog = layoutInflater.inflate(R.layout.call,null)
            var dlglogin = AlertDialog.Builder(this)
            dlglogin.setView(dialog)

            dlglogin.setPositiveButton("확인") { dialog, which ->
                null
            }
            dlglogin.show()
        }

    }


}

//private fun TextView.movementMethod(scrollingMovementMethod: ScrollingMovementMethod) {
//
//}