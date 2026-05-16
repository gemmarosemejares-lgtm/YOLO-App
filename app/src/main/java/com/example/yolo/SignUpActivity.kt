package com.example.yolo

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_sign_up)

        val etLastName =
            findViewById<EditText>(R.id.etLastName)

        val etFirstName =
            findViewById<EditText>(R.id.etFirstName)

        val etEmail =
            findViewById<EditText>(R.id.etEmail)

        val etPassword =
            findViewById<EditText>(R.id.etPassword)

        val btnSignup =
            findViewById<Button>(R.id.btnSignup)

        val txtLogin =
            findViewById<TextView>(R.id.txtLogin)

        val imgEyeSignup =
            findViewById<ImageView>(R.id.imgEyeSignup)

        var isPasswordVisible = false

        btnSignup.setOnClickListener {

            val lastName =
                etLastName.text.toString().trim()

            val firstName =
                etFirstName.text.toString().trim()

            val email =
                etEmail.text.toString().trim()

            val password =
                etPassword.text.toString().trim()

            // CHECK EMPTY FIELDS

            if (lastName.isEmpty()) {

                etLastName.error = "Enter Last Name"
                etLastName.requestFocus()
                return@setOnClickListener
            }

            if (firstName.isEmpty()) {

                etFirstName.error = "Enter First Name"
                etFirstName.requestFocus()
                return@setOnClickListener
            }

            if (email.isEmpty()) {

                etEmail.error = "Enter Email"
                etEmail.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()) {

                etPassword.error = "Enter Password"
                etPassword.requestFocus()
                return@setOnClickListener
            }

            val url =
                "http://10.0.2.2:5000/signup"

            val stringRequest =
                object : StringRequest(
                    Request.Method.POST,
                    url,

                    { response ->

                        if (
                            response.contains(
                                "Signup Success"
                            )
                        ) {

                            Toast.makeText(
                                this,
                                "Signup Success",
                                Toast.LENGTH_SHORT
                            ).show()

                            startActivity(
                                Intent(
                                    this,
                                    LoginActivity::class.java
                                )
                            )

                            finish()

                        } else {

                            Toast.makeText(
                                this,
                                "Signup Failed",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    },

                    {

                        Toast.makeText(
                            this,
                            "Connection Error",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                ) {

                    override fun getParams():
                            MutableMap<String, String> {

                        val params =
                            HashMap<String, String>()

                        params["name"] =
                            "$firstName $lastName"

                        params["email"] =
                            email

                        params["password"] =
                            password

                        return params
                    }
                }

            val queue: RequestQueue =
                Volley.newRequestQueue(this)

            queue.add(stringRequest)
        }

        txtLogin.setOnClickListener {

            startActivity(
                Intent(this, LoginActivity::class.java)
            )
        }

        imgEyeSignup.setOnClickListener {

            if (isPasswordVisible) {

                etPassword.transformationMethod =
                    PasswordTransformationMethod.getInstance()

                isPasswordVisible = false

            } else {

                etPassword.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()

                isPasswordVisible = true
            }

            etPassword.setSelection(etPassword.text.length)
        }
    }
}