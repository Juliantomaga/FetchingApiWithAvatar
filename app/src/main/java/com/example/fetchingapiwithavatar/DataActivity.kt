package com.example.fetchingapiwithavatar

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

private lateinit var adapter : DataAdapter
private lateinit var datalayout: ListView

class DataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)

        datalayout = findViewById(R.id.datalayout)
        adapter = DataAdapter(this) {data ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("firsname", data[0])
            intent.putExtra("lastname", data[1])
            intent.putExtra("email", data[2])
            intent.putExtra("avatarLink", data[3])
            startActivity(intent)
        }
        datalayout.adapter = adapter
        fetchingApi()
    }

    private fun fetchingApi() {

        AsyncTask.execute {
            try {
                val url = URL("https://reqres.in/api/users?page=1")
                val conn = url.openConnection() as HttpURLConnection
                conn.requestMethod = "GET"

                val responcode = conn.responseCode
                if(responcode == HttpURLConnection.HTTP_OK) {
                    val reader = BufferedReader(InputStreamReader(conn.inputStream))
                    val response = StringBuilder()
                    var line : String?

                    while(reader.readLine().also { line = it } != null) {
                        response.append(line)
                    }

                    reader.close()

                    val jsonObject = JSONObject(response.toString())
                    val jsonArray = jsonObject.getJSONArray("data")
                    if (jsonArray.length() > 0) {
                        for (i in 0 until jsonArray.length()) {
                            val jsonObject = jsonArray.getJSONObject(i)
                            val firstName = jsonObject.getString("first_name")
                            val lastName = jsonObject.getString("last_name")
                            val avatarLink = jsonObject.getString("avatar")
                            val fullName = "$firstName $lastName"
                            val email = jsonObject.getString("email")

                            runOnUiThread {
                                adapter.addData(listOf(firstName,lastName, email, avatarLink))
                            }
                        }
                    } else {
                        runOnUiThread {
                         showToast("gagal cok")
                        }
                    }
                }
            } catch (e: Exception) {
                runOnUiThread {
                    showToast("ana masalah pas fetching ${e.message}")
                }
            }
        }
    }

    private fun showToast(message: String) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}