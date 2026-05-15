package com.karunada.karunadakoteguide1

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_register)

        sharedPreferences =
            getSharedPreferences("USER_DATA", MODE_PRIVATE)

        val name =
            findViewById<EditText>(R.id.registerName)

        val email =
            findViewById<EditText>(R.id.registerEmail)

        val password =
            findViewById<EditText>(R.id.registerPassword)

        val registerBtn =
            findViewById<Button>(R.id.registerBtn)

        registerBtn.setOnClickListener {

            sharedPreferences.edit()
                .putString("name", name.text.toString())
                .putString("email", email.text.toString())
                .putString("password", password.text.toString())
                .apply()

            Toast.makeText(
                this,
                "Registration Successful",
                Toast.LENGTH_SHORT
            ).show()

            startActivity(
                Intent(this, LoginActivity::class.java)
            )

            finish()
        }
    }
}