package com.example.yolo

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CategoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        val navHome =
            findViewById<LinearLayout>(R.id.navHome)

        val navCategory =
            findViewById<LinearLayout>(R.id.navCategory)

        val navYou =
            findViewById<LinearLayout>(R.id.navYou)

        val navCart =
            findViewById<LinearLayout>(R.id.navCart)

        val navLogout =
            findViewById<LinearLayout>(R.id.navLogout)

        // HOME

        navHome.setOnClickListener {

            startActivity(
                Intent(this, MainActivity::class.java)
            )

            finish()
        }

        // CATEGORY

        navCategory.setOnClickListener {

            Toast.makeText(
                this,
                "Already in Categories",
                Toast.LENGTH_SHORT
            ).show()
        }

        // YOU

        navYou.setOnClickListener {

            Toast.makeText(
                this,
                "YOLO User",
                Toast.LENGTH_SHORT
            ).show()
        }

        // CART

        navCart.setOnClickListener {

            startActivity(
                Intent(this, CartManager::class.java)
            )
        }

        // LOGOUT

        navLogout.setOnClickListener {

            startActivity(
                Intent(this, LoginActivity::class.java)
            )

            finish()
        }
    }
}