package com.example.karamovb2_v3

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.core.graphics.drawable.toDrawable

private lateinit var sharedPreferences: SharedPreferences
public lateinit var listView: ListView
private lateinit var adapter : ArrayAdapter<String>

private var items = mutableListOf<String>()

class Chats : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chats)
        sharedPreferences = getSharedPreferences("Pref", MODE_PRIVATE)
        items.add("John Joshua\nHello")
        items.add("Chinonso James\nHello")
        items.add("Ralph Ron\nHello")
        items.add("Jon Ezekief\nHello")
        listView = findViewById(R.id.listview1)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        listView.adapter = adapter
    }

    fun Back(view: View) {

        val intent = Intent(this, LogIn::class.java)
        with(sharedPreferences.edit())
        {
            putString(Key_email, "user")
            putString(Key_password, "password")
            apply()
        }
        startActivity(intent)

    }

    fun UserClick(view: View) {
        listView.selectedItem.toString()
        val intent = Intent(this, LogIn::class.java)
        startActivity(intent)
    }
}