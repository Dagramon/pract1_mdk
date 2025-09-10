package com.example.karamovb2_v3

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

private lateinit var sharedPreferences: SharedPreferences

val Key_email = "user"
val Key_password = "password"

class LogIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.log_in)
        sharedPreferences = getSharedPreferences("Pref", MODE_PRIVATE)
    }
    fun Click(view: View) {

        val emailText = findViewById<EditText>(R.id.email)
        val passwordText = findViewById<EditText>(R.id.password)

        var email = sharedPreferences.getString(Key_email, "")
        var password = sharedPreferences.getString(Key_password, "")

        if (email == "ects" && password == "ects2025")
        {
            val intent = Intent(this, Chats::class.java)
            startActivity(intent)
            return
        }
        if (emailText.text.toString() != "" && passwordText.text.toString() != "") {
            if (emailText.text.toString() == "ects" && passwordText.text.toString() == "ects2025") {
                val intent = Intent(this, Chats::class.java)
                with(sharedPreferences.edit())
                {
                    putString(Key_email, emailText.text.toString())
                    putString(Key_password, passwordText.text.toString())
                    apply()
                }
                startActivity(intent)
            }
            else {
                AlertDialog.Builder(this)
                    .setTitle("Ошибка")
                    .setMessage("Почта или пароль неверны")
                    .show()
            }
        }
        else
        {
            AlertDialog.Builder(this)
                .setTitle("Ошибка")
                .setMessage("Поля пусты")
                .show()
        }
    }
}