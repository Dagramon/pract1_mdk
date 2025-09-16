package com.example.myapplication

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.content.edit

class Login : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button

    private lateinit var sharedPref: SharedPreferences

    private val validEmail = "ects"
    private val validPassword = "ects2025"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        sharedPref = getSharedPreferences("LoginPrefs", MODE_PRIVATE)

        etEmail = findViewById(R.id.email)
        etPassword = findViewById(R.id.password)

        val savedEmail = sharedPref.getString("email", "")
        val savedPassword = sharedPref.getString("password", "")

        if (!savedEmail.isNullOrEmpty() && !savedPassword.isNullOrEmpty()) {
            etEmail.setText(savedEmail)
            etPassword.setText(savedPassword)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun Click(view: View) {

        val email = etEmail.text.toString().trim()
        val password = etPassword.text.toString().trim()

        if (email.isEmpty() || password.isEmpty()) {
            AlertDialog.Builder(this)
                .setMessage("Поля должны быть заполнены")
                .create()
                .show()
            return
        }

        if (email == validEmail && password == validPassword) {

            with(sharedPref.edit()) {
                putString("email", email)
                putString("password", password)
                apply()
            }
            val intent = Intent(this, Chats::class.java)
            startActivity(intent)
            finish()
        }
        else
        {
            AlertDialog.Builder(this)
                .setMessage("Неверный пароль или email")
                .create()
                .show()
        }
    }
}