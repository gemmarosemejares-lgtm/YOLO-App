package com.example.yolo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
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