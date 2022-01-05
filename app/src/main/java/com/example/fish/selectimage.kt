package com.example.fish

import android.Manifest
import android.Manifest.permission.CAMERA
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.graphics.drawable.BitmapDrawable
import android.media.Image
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.util.component1
import androidx.core.util.component2
import com.example.fish.tflite.ClassifierWithModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class selectimage : AppCompatActivity() {
    var getGalleryImg: Int = 200
    lateinit var selectimg : ImageView
    lateinit var resultbtn : Button
    lateinit var selectedImgUri: Uri
   // val REQUEST_IMAGE_CAPTURE = 1
    lateinit var currentPhotoPath : String



    lateinit var home : FloatingActionButton



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.selectimage)
       // settingPermission() // 권한체크 시작


        selectimg = findViewById<ImageView>(R.id.selectimg)
        resultbtn = findViewById<Button>(R.id.resultbtn)

        home = findViewById<View>(R.id.fab) as FloatingActionButton






//        이미지 클릭 시 사진촬영, 앨범선택 중 선택가능
        selectimg.setOnClickListener {

            if(checkPermission()) {


                var dlglogin = AlertDialog.Builder(this)
                dlglogin.setTitle("이미지 가져오기")
                dlglogin.setPositiveButton("사진촬영") { dialog, which ->
                    //    startCapture()
                    dispatchTakePictureIntent()

                }



                dlglogin.setNegativeButton("앨범선택") { dialog, which ->
                    var intent = Intent(Intent.ACTION_GET_CONTENT)
                    intent.setType("image/*")
                    startActivityForResult(intent, getGalleryImg)
                }
                dlglogin.setNeutralButton("취소") { dialog, which ->
                    null
                }
                dlglogin.show()
            }
            else{
            requestPermission()
        }

        }


        home.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }






        resultbtn.setOnClickListener {



            val stream = ByteArrayOutputStream()
            val bitmap = (selectimg!!.drawable as BitmapDrawable).bitmap
            val scale = (1024 / bitmap.width.toFloat())
            val image_w = (bitmap.width * scale).toInt()
            val image_h = (bitmap.height * scale).toInt()
            val resize = Bitmap.createScaledBitmap(bitmap, image_w, image_h, true)
            resize.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            val byteArray = stream.toByteArray()
            val intent = Intent(this@selectimage, result::class.java)
            intent.putExtra("image", byteArray)
            startActivity(intent)

        }
    }


    private fun requestPermission(){
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,CAMERA),1)
    }
    private fun checkPermission():Boolean{
        return (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,
            Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) }
    @Override
    override fun onRequestPermissionsResult
                ( requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if( requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "권한 설정 OK", Toast.LENGTH_SHORT).show()
        }
        else
        { Toast.makeText(this, "권한 허용 안됨", Toast.LENGTH_SHORT).show()
        }
    }

    private val REQUEST_IMAGE_CAPTURE = 2
    private fun dispatchTakePictureIntent() {
        val intent:Intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)

    }

    @Override override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    { super.onActivityResult(requestCode, resultCode, data)
        if( resultCode == Activity.RESULT_OK) {
            if (requestCode == getGalleryImg) {
                var ImnageData: Uri? = data?.data
                Toast.makeText(this, ImnageData.toString(), Toast.LENGTH_SHORT).show()
                try {
                    val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, ImnageData)
                    selectimg.setImageBitmap(bitmap)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } else if( requestCode == REQUEST_IMAGE_CAPTURE)
            { val imageBitmap :Bitmap? = data?.extras?.get("data") as Bitmap
                selectimg.setImageBitmap(imageBitmap)
            }
        }
    }















    //    사진 촬영 권한 주기

}



