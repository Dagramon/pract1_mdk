package com.example.karamovb2_v3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class UserClick : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_click)
        var text = findViewById<TextView>(R.id.text8)
        listView.selectedItem.toString()
        text.text = listView.selectedItem.toString()
    }
    fun GoBack(view: View) {

        val intent = Intent(this, Chats::class.java)
        startActivity(intent)

    }
}