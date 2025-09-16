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

        sharedPref = getSharedPreferences("LoginPrefs", MODE_PRIVATE) //Получение SharedPreferences

        etEmail = findViewById(R.id.email)
        etPassword = findViewById(R.id.password) //Получение View

        val savedEmail = sharedPref.getString("email", "") //Получение сохранённых строк
        val savedPassword = sharedPref.getString("password", "")

        if (!savedEmail.isNullOrEmpty() && !savedPassword.isNullOrEmpty()) { //Проверка на сохранённые строки
            etEmail.setText(savedEmail)
            etPassword.setText(savedPassword) //Сохранённые строки устанавливаются в editText
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

        if (email.isEmpty() || password.isEmpty()) { //Проверка на пустые поля
            AlertDialog.Builder(this)
                .setMessage("Поля должны быть заполнены")
                .create()
                .show()
            return
        }

        if (email == validEmail && password == validPassword) { //Проверка на правильность ввода

            with(sharedPref.edit()) {
                putString("email", email)
                putString("password", password)
                apply()
            }
            val intent = Intent(this, Chats::class.java) //Переход на следующий экран
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