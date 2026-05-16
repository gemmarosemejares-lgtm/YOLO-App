package com.example.yolo

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.LinearLayout
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

        val recyclerView =
            findViewById<RecyclerView>(R.id.recyclerView)

        val btnAll =
            findViewById<Button>(R.id.btnAll)

        val btnWomen =
            findViewById<Button>(R.id.btnWomen)

        val btnMen =
            findViewById<Button>(R.id.btnMen)

        val btnSports =
            findViewById<Button>(R.id.btnSports)

        val btnAccessories =
            findViewById<Button>(R.id.btnAccessories)

        val etSearch =
            findViewById<EditText>(R.id.etSearch)
        etSearch.setOnClickListener {

            etSearch.isCursorVisible = true
        }
        val navHome =
            findViewById<LinearLayout>(R.id.navHome)
        navHome.setOnClickListener {

            startActivity(
                Intent(this, MainActivity::class.java)
            )

            finish()
        }



        val navCategory =
            findViewById<LinearLayout>(R.id.navCategory)
        navCategory.setOnClickListener {

            startActivity(
                Intent(this, CategoryActivity::class.java)
            )
        }

        val navYou =
            findViewById<LinearLayout>(R.id.navYou)

        navYou.setOnClickListener {

            startActivity(
                Intent(this, YouActivity::class.java)
            )
        }

        val navCart =
            findViewById<LinearLayout>(R.id.navCart)
        navCart.setOnClickListener {

            startActivity(
                Intent(this, CartActivity::class.java)
            )
        }
        val navLogout =
            findViewById<LinearLayout>(R.id.navLogout)
        navLogout.setOnClickListener {

            startActivity(
                Intent(this, LoginActivity::class.java)
            )

            finish()
        }

        recyclerView.layoutManager =
            GridLayoutManager(this, 2)

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

        recyclerView.adapter =
            ProductAdapter(productList)

        // ALL

        btnAll.setOnClickListener {

            recyclerView.adapter =
                ProductAdapter(productList)
        }

        // WOMEN

        btnWomen.setOnClickListener {

            val filtered =
                productList.filter {
                    it.category == "Women"
                }

            recyclerView.adapter =
                ProductAdapter(filtered)
        }

        // MEN

        btnMen.setOnClickListener {

            val filtered =
                productList.filter {
                    it.category == "Men"
                }

            recyclerView.adapter =
                ProductAdapter(filtered)
        }

        // SPORTS

        btnSports.setOnClickListener {

            val filtered =
                productList.filter {
                    it.category == "Sports"
                }

            recyclerView.adapter =
                ProductAdapter(filtered)
        }

        // ACCESSORIES

        btnAccessories.setOnClickListener {

            val filtered =
                productList.filter {
                    it.category == "Accessories"
                }

            recyclerView.adapter =
                ProductAdapter(filtered)
        }

        // SEARCH

        etSearch.addTextChangedListener(object : TextWatcher {


            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {

                val filteredList = productList.filter {

                    it.name.lowercase().contains(
                        s.toString().lowercase()
                    )
                }

                recyclerView.adapter =
                    ProductAdapter(filteredList)
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }
}