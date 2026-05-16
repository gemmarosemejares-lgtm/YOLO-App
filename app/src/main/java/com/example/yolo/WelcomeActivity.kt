package com.example.yolo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnSignup = findViewById<Button>(R.id.btnSignup)

        btnLogin.setOnClickListener {

            startActivity(
                Intent(this, LoginActivity::class.java)
            )
        }

        btnSignup.setOnClickListener {

            startActivity(
                Intent(this, SignUpActivity::class.java)
            )
        }
    }
}