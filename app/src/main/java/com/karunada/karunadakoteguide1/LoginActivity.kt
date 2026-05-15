package com.karunada.karunadakoteguide1

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        sharedPreferences =
            getSharedPreferences("USER_DATA", MODE_PRIVATE)

        val email =
            findViewById<EditText>(R.id.loginEmail)

        val password =
            findViewById<EditText>(R.id.loginPassword)

        val loginBtn =
            findViewById<Button>(R.id.loginBtn)

        val registerText =
            findViewById<TextView>(R.id.goRegister)

        loginBtn.setOnClickListener {

            val savedEmail =
                sharedPreferences.getString("email", "")

            val savedPassword =
                sharedPreferences.getString("password", "")

            if (
                email.text.toString() == savedEmail &&
                password.text.toString() == savedPassword
            ) {

                Toast.makeText(
                    this,
                    "Login Successful",
                    Toast.LENGTH_SHORT
                ).show()

                startActivity(
                    Intent(this, HomeActivity::class.java)
                )

                finish()

            } else {

                Toast.makeText(
                    this,
                    "Invalid Credentials",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        registerText.setOnClickListener {

            startActivity(
                Intent(this, RegisterActivity::class.java)
            )
        }
    }
}