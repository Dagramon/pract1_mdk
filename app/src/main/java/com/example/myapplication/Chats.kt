package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Chats : AppCompatActivity() {

    lateinit var lvContacts : ListView

    private val contacts = listOf(
        "John Joshua - Thanks for your service",
        "Chinonso James - Alright, I will be waiting", //Список контактов
        "Raph Ron - Thanks for your service",
        "Joy Ezekiel - Thanks for your service"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_chats)

        lvContacts = findViewById(R.id.listview1)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, contacts)
        lvContacts.adapter = adapter

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        lvContacts.setOnItemClickListener { _, _, position, _ ->
            val selectedContact = contacts[position]
            val intent = Intent(this, Contact::class.java) //Отправка выбраного контакта на следующий экран
            intent.putExtra("CONTACT_INFO", selectedContact)
            startActivity(intent)
        }

    }

    fun BackToLogin(view: View) { //Нажатие на стрелку переносит на предыдущий экран

        val intent = Intent(this, Login::class.java)
        startActivity(intent)
        finish()
    }

}