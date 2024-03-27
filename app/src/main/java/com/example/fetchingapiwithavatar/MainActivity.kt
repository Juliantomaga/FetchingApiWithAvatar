package com.example.fetchingapiwithavatar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.widget.Toast
import com.example.fetchingapiwithavatar.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val checkPass = binding.checkbox

        binding.btnLogin.setOnClickListener {
            val username = binding.txtUsername.text.toString()
            val password = binding.txtPassword.text.toString()

            if(username == "" && password == "") {
                Toast.makeText(this, "username dan password tidak boleh kosong", Toast.LENGTH_SHORT).show()
            } else if(username == "admin" && password == "12345"){
                Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, DataActivity::class.java )
                startActivity(intent)
            } else {
                Toast.makeText(this, "Login gagal periksa kembali username dan password anda", Toast.LENGTH_SHORT).show()
            }
        }


        checkPass.setOnCheckedChangeListener{_, isChecked ->

            binding.txtPassword.transformationMethod = if(isChecked) null else android.text.method.PasswordTransformationMethod.getInstance()
        }
    }
}