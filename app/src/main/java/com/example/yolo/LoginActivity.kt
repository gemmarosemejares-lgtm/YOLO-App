package com.example.yolo

import android.content.Intent
import android.os.Bundle
import android.text.InputType
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

class LoginActivity : AppCompatActivity() {

    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        // EMAIL

        val edtEmail =
            findViewById<EditText>(R.id.edtEmail)

        // PASSWORD

        val edtPassword =
            findViewById<EditText>(R.id.edtPassword)

        // EYE ICON

        val imgEye =
            findViewById<ImageView>(R.id.imgEye)

        imgEye.setOnClickListener {

            if (isPasswordVisible) {

                edtPassword.inputType =
                    InputType.TYPE_CLASS_TEXT or
                            InputType.TYPE_TEXT_VARIATION_PASSWORD

                isPasswordVisible = false

            } else {

                edtPassword.inputType =
                    InputType.TYPE_CLASS_TEXT or
                            InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD

                isPasswordVisible = true
            }

            edtPassword.setSelection(
                edtPassword.text.length
            )
        }

        // LOGIN BUTTON

        val btnLogin =
            findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {

            val email =
                edtEmail.text.toString().trim()

            val password =
                edtPassword.text.toString().trim()

            // CHECK EMAIL

            if (email.isEmpty()) {

                edtEmail.error = "Enter Email"
                edtEmail.requestFocus()
                return@setOnClickListener
            }

            // CHECK PASSWORD

            if (password.isEmpty()) {

                edtPassword.error = "Enter Password"
                edtPassword.requestFocus()
                return@setOnClickListener
            }

            val url = "http://10.0.2.2:5000/login"

            val stringRequest =
                object : StringRequest(
                    Request.Method.POST,
                    url,

                    { response ->

                        if (
                            response.contains(
                                "Login Success"
                            )
                        ) {

                            // SAVE USERNAME

                            val sharedPref =
                                getSharedPreferences(
                                    "UserData",
                                    MODE_PRIVATE
                                )

                            sharedPref.edit()
                                .putString(
                                    "username",
                                    email.substringBefore("@")
                                )
                                .apply()

                            Toast.makeText(
                                this,
                                "Login Success",
                                Toast.LENGTH_SHORT
                            ).show()

                            startActivity(
                                Intent(
                                    this,
                                    MainActivity::class.java
                                )
                            )

                            finish()

                        } else {

                            Toast.makeText(
                                this,
                                "Invalid Account",
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

        // SIGN UP TEXT

        val txtSignup =
            findViewById<TextView>(R.id.txtSignup)

        txtSignup.setOnClickListener {

            startActivity(
                Intent(this, SignUpActivity::class.java)
            )
        }
    }
}