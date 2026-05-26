package com.example.yolo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.yolo.CartManager
import com.example.yolo.R
import com.example.yolo.models.Product

class ProductAdapter(
    private val productList: List<Product>
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView) {

        val imgProduct =
            itemView.findViewById<ImageView>(R.id.imgProduct)

        val txtName =
            itemView.findViewById<TextView>(R.id.txtName)

        val txtPrice =
            itemView.findViewById<TextView>(R.id.txtPrice)

        val btnAddCart =
            itemView.findViewById<Button>(R.id.btnAddCart)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)

        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ProductViewHolder,
        position: Int
    ) {

        val product = productList[position]

        holder.txtName.text = product.name
        holder.txtPrice.text = "₱${product.price}"

        // PRODUCT IMAGE
        holder.imgProduct.setImageResource(product.image)

        // ADD TO CART
        holder.btnAddCart.setOnClickListener {

            CartManager.cartItems.add(product)

            // VOLLEY API

            val url =
                "http://10.0.2.2:5000/add_cart"

            val request =
                object : StringRequest(

                    Request.Method.POST,
                    url,

                    {

                        Toast.makeText(
                            holder.itemView.context,
                            "${product.name} added to database",
                            Toast.LENGTH_SHORT
                        ).show()
                    },

                    {

                        Toast.makeText(
                            holder.itemView.context,
                            "Database Error",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                ) {

                    override fun getParams(): MutableMap<String, String> {

                        val params = HashMap<String, String>()

                        params["product_name"] = product.name.toString()
                        params["price"] = product.price.toString()
                        params["quantity"] = "1"

                        return params
                    }
                }

            val queue: RequestQueue =
                Volley.newRequestQueue(
                    holder.itemView.context
                )

            queue.add(request)

            Toast.makeText(
                holder.itemView.context,
                "${product.name} added to cart",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}