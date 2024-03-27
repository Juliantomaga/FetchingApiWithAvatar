package com.example.fetchingapiwithavatar

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.fetchingapiwithavatar.databinding.ActivityDetailBinding
import com.squareup.picasso.Picasso
import java.net.HttpURLConnection
import java.net.URL


private lateinit var binding: ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val avatarImageView = binding.avatarImage
        val firstnameText = binding.firsNametext
        val lastnameText = binding.lastNametext
        val emailText = binding.emailtext
        val avatarText =  binding.linkAvatar
        val image = binding.avatarImage

        val firstname = intent.getStringExtra("firsname")
        val lastname = intent.getStringExtra("lastname")
        val email = intent.getStringExtra("email")
        val avatarLink = intent.getStringExtra("avatarLink")

        firstnameText.text = " FirstName : $firstname"
        lastnameText.text = " LastName : $lastname"
        emailText.text = " Email : $email"
        avatarText.text = " Avatar Link: $avatarLink"

        Thread{
            try {
                val url = URL(avatarLink)
                val conn = url.openConnection() as HttpURLConnection
                conn.doInput = true

                val inputStream = conn.inputStream
                val bitmap = BitmapFactory.decodeStream(inputStream)
                runOnUiThread {
                    image.setImageBitmap(bitmap)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.start()


    }
}