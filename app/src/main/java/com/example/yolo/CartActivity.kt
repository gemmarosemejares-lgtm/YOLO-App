package com.example.yolo

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yolo.adapter.CartAdapter

class CartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_cart)

        val recyclerCart =
            findViewById<RecyclerView>(R.id.recyclerCart)

        val txtTotal =
            findViewById<TextView>(R.id.txtTotal)

        val btnOrder =
            findViewById<Button>(R.id.btnOrder)

        val btnBack =
            findViewById<TextView>(R.id.btnBack)

        // BACK BUTTON

        btnBack.setOnClickListener {

            val intent =
                Intent(this, MainActivity::class.java)

            startActivity(intent)

            finish()
        }

        // RECYCLER VIEW

        recyclerCart.layoutManager =
            LinearLayoutManager(this)

        recyclerCart.adapter =
            CartAdapter(CartManager.cartItems){ total ->

                txtTotal.text = "₱$total"
            }

        // TOTAL

        var total = 0.0

        for(product in CartManager.cartItems){

            total += product.price
        }

        txtTotal.text = "₱$total"

        // ORDER

        btnOrder.setOnClickListener {

            Toast.makeText(
                this,
                "Order Successfully Placed!",
                Toast.LENGTH_SHORT
            ).show()

            // CLEAR CART

            CartManager.cartItems.clear()

            // REFRESH PAGE

            recreate()
        }
    }
}