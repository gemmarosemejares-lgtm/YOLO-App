package com.example.yolo

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yolo.adapter.ProductAdapter
import com.example.yolo.models.Product

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView?>(R.id.recyclerView)

        val btnAll = findViewById<Button?>(R.id.btnAll)
        val btnWomen = findViewById<Button?>(R.id.btnWomen)
        val btnMen = findViewById<Button?>(R.id.btnMen)
        val btnSports = findViewById<Button?>(R.id.btnSports)
        val btnAccessories = findViewById<Button?>(R.id.btnAccessories)

        val etSearch = findViewById<EditText?>(R.id.etSearch)

        val navHome = findViewById<LinearLayout?>(R.id.navHome)
        val navCategory = findViewById<LinearLayout?>(R.id.navCategory)
        val navYou = findViewById<LinearLayout?>(R.id.navYou)
        val navCart = findViewById<LinearLayout?>(R.id.navCart)
        val navLogout = findViewById<LinearLayout?>(R.id.navLogout)

        // SEARCH CLICK
        etSearch?.setOnClickListener {
            etSearch.isCursorVisible = true
        }

        // NAVIGATION
        navHome?.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        navCategory?.setOnClickListener {
            startActivity(Intent(this, CategoryActivity::class.java))
        }

        navYou?.setOnClickListener {
            startActivity(Intent(this, YouActivity::class.java))
        }

        navCart?.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }

        navLogout?.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        // ONLY RUN IF RECYCLER EXISTS
        recyclerView?.let { rv ->

            rv.layoutManager = GridLayoutManager(this, 2)

            val productList = listOf(

                // PHONE CASES

                Product(
                    "Strawberry Cake Case",
                    299.0,
                    R.drawable.case1,
                    "Accessories"
                ),

                Product(
                    "MagSafe Black Case",
                    199.0,
                    R.drawable.case2,
                    "Accessories"
                ),

                Product(
                    "Flower Phone Case",
                    249.0,
                    R.drawable.case3,
                    "Accessories"
                ),

                Product(
                    "Cat Pink Case",
                    229.0,
                    R.drawable.case4,
                    "Accessories"
                ),

                Product(
                    "Cartoon Yellow Case",
                    279.0,
                    R.drawable.case5,
                    "Accessories"
                ),

                // WOMEN TOPS

                Product(
                    "Green Tank Top",
                    249.0,
                    R.drawable.women1,
                    "Women"
                ),

                Product(
                    "Black Fitted Top",
                    299.0,
                    R.drawable.women2,
                    "Women"
                ),

                Product(
                    "Gray Sleeveless",
                    199.0,
                    R.drawable.women3,
                    "Women"
                ),

                // MEN TOPS

                Product(
                    "Gray Muscle Shirt",
                    349.0,
                    R.drawable.men1,
                    "Men"
                ),

                Product(
                    "Blue Sports Tank",
                    399.0,
                    R.drawable.men2,
                    "Men"
                ),

                Product(
                    "Army Green Tank",
                    429.0,
                    R.drawable.men3,
                    "Men"
                ),

                // BAGS

                Product(
                    "Beige Gucci Bag",
                    1299.0,
                    R.drawable.bag1,
                    "Women"
                ),

                Product(
                    "Pink Chanel Tote",
                    1499.0,
                    R.drawable.bag2,
                    "Women"
                ),

                Product(
                    "LV Brown Tote",
                    1599.0,
                    R.drawable.bag3,
                    "Women"
                ),

                Product(
                    "Black LV Bag",
                    1399.0,
                    R.drawable.bag4,
                    "Women"
                ),

                // SHOES

                Product(
                    "Street White Shoes",
                    899.0,
                    R.drawable.shoes1,
                    "Men"
                ),

                Product(
                    "New Balance White",
                    1199.0,
                    R.drawable.shoes2,
                    "Men"
                ),

                Product(
                    "Cream NB Sneakers",
                    1099.0,
                    R.drawable.shoes3,
                    "Women"
                ),

                Product(
                    "Asics Running Shoes",
                    1299.0,
                    R.drawable.shoes4,
                    "Sports"
                ),

                // ACCESSORIES

                Product(
                    "Pink Flower Keychain",
                    149.0,
                    R.drawable.accessory1,
                    "Accessories"
                ),

                Product(
                    "Black Leather Belt",
                    299.0,
                    R.drawable.accessory2,
                    "Accessories"
                ),

                Product(
                    "Pearl Necklace",
                    249.0,
                    R.drawable.accessory3,
                    "Accessories"
                ),

                Product(
                    "Blue Flower Earrings",
                    129.0,
                    R.drawable.accessory4,
                    "Accessories"
                ),

                Product(
                    "Pastel Headband",
                    399.0,
                    R.drawable.accessory5,
                    "Accessories"
                )
            )


            rv.adapter = ProductAdapter(productList)

            // BUTTON FILTERS
            btnAll?.setOnClickListener {
                rv.adapter = ProductAdapter(productList)
            }

            btnWomen?.setOnClickListener {
                rv.adapter = ProductAdapter(productList.filter { it.category == "Women" })
            }

            btnMen?.setOnClickListener {
                rv.adapter = ProductAdapter(productList.filter { it.category == "Men" })
            }

            btnSports?.setOnClickListener {
                rv.adapter = ProductAdapter(productList.filter { it.category == "Sports" })
            }

            btnAccessories?.setOnClickListener {
                rv.adapter = ProductAdapter(productList.filter { it.category == "Accessories" })
            }

            // SEARCH FILTER
            etSearch?.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val filtered = productList.filter {
                        it.name.lowercase().contains(s.toString().lowercase())
                    }
                    rv.adapter = ProductAdapter(filtered)
                }

                override fun afterTextChanged(s: Editable?) {}
            })
        }
    }
}