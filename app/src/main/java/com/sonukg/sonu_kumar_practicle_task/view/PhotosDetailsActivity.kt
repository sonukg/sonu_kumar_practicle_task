package com.sonukg.sonu_kumar_practicle_task.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.sonukg.sonu_kumar_practicle_task.R


class PhotosDetailsActivity : AppCompatActivity() {
    private var imageView:ImageView?=null
    private var urls:String?=null
    private var sharedPreferences:SharedPreferences?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photos_details)
        imageView=findViewById(R.id.imageView)
        sharedPreferences=getSharedPreferences("login", Context.MODE_PRIVATE)
        val extras = intent.extras
        if (extras != null) {
            urls= extras.getString("url")
        }
        Glide.with(this).load(urls).into(imageView!!)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.logout_menu, menu);
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.logout -> {
                val editor:SharedPreferences.Editor= sharedPreferences!!.edit()
                editor.clear()
                editor.commit()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}