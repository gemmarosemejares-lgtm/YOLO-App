package com.example.yolo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.yolo.R
import com.example.yolo.models.Product

class CartAdapter(
    private val cartList: MutableList<Product>,
    private val onTotalChanged: (Double) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    private val quantityMap = mutableMapOf<Int, Int>()

    class CartViewHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView){

        val imgCart =
            itemView.findViewById<ImageView>(R.id.imgCart)

        val txtCartName =
            itemView.findViewById<TextView>(R.id.txtCartName)

        val txtCartPrice =
            itemView.findViewById<TextView>(R.id.txtCartPrice)

        val txtQuantity =
            itemView.findViewById<TextView>(R.id.txtQuantity)

        val btnPlus =
            itemView.findViewById<TextView>(R.id.btnPlus)

        val btnMinus =
            itemView.findViewById<TextView>(R.id.btnMinus)

        val btnRemove =
            itemView.findViewById<Button>(R.id.btnRemove)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CartViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart, parent, false)

        return CartViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: CartViewHolder,
        position: Int
    ) {

        val product = cartList[position]

        holder.txtCartName.text = product.name

        holder.imgCart.setImageResource(product.image)

        // DEFAULT QUANTITY

        if (!quantityMap.containsKey(position)) {

            quantityMap[position] = 1
        }

        var quantity = quantityMap[position] ?: 1

        fun updatePrice(){

            val totalPrice = product.price * quantity

            holder.txtCartPrice.text = "₱$totalPrice"

            holder.txtQuantity.text = quantity.toString()

            computeTotal()
        }

        // PLUS

        holder.btnPlus.setOnClickListener {

            quantity++

            quantityMap[position] = quantity

            updatePrice()
        }

        // MINUS

        holder.btnMinus.setOnClickListener {

            if(quantity > 1){

                quantity--

                quantityMap[position] = quantity

                updatePrice()
            }
        }

        // REMOVE ITEM

        holder.btnRemove.setOnClickListener {

            cartList.removeAt(position)

            notifyDataSetChanged()

            computeTotal()
        }

        updatePrice()
    }

    override fun getItemCount(): Int {

        return cartList.size
    }

    // TOTAL

    private fun computeTotal(){

        var total = 0.0

        for(i in cartList.indices){

            val qty = quantityMap[i] ?: 1

            total += cartList[i].price * qty
        }

        onTotalChanged(total)
    }
}