package com.gmb.restaurapp.adapter

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.gmb.restaurapp.R
import com.gmb.restaurapp.common.getAllergenInfo
import com.gmb.restaurapp.common.getDishPhoto
import com.gmb.restaurapp.model.Dish


class DishRecyclerViewAdapter(val dishList: List<Dish>?, val tableNumber: Int, val listener: OnDishClickListener?) : RecyclerView.Adapter<DishRecyclerViewAdapter.DishViewHolder>() {

    private var onDishClickListener: OnDishClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DishViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.content_dish, parent, false)
        onDishClickListener = listener
        return DishViewHolder(view)
    }

    override fun onBindViewHolder(holder: DishViewHolder?, position: Int) {
        if (dishList != null) {
            holder?.bindDish(dishList[position], position)
        }
    }


    override fun getItemCount() = dishList?.size ?: 0

    inner class DishViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val dishName = itemView.findViewById<TextView>(R.id.dish)
        val dishPrice = itemView.findViewById<TextView>(R.id.dish_price)
        val dishImage = itemView.findViewById<ImageView>(R.id.dish_image)


        private val allergens = mutableListOf<TextView>(
                itemView.findViewById<TextView>(R.id.allergen),
                itemView.findViewById<TextView>(R.id.allergen2),
                itemView.findViewById<TextView>(R.id.allergen3),
                itemView.findViewById<TextView>(R.id.allergen4)
        )

        private val ivAllergens = mutableListOf<ImageView>(
                itemView.findViewById<ImageView>(R.id.iv_allergen),
                itemView.findViewById<ImageView>(R.id.iv_allergen2),
                itemView.findViewById<ImageView>(R.id.iv_allergen3),
                itemView.findViewById<ImageView>(R.id.iv_allergen4)
        )


        fun bindDish(dish: Dish, position: Int) {

            // actualizamos la vista (itemView, que es la tarjeta) con el modelo
            dishName.text = dish.name
            dishPrice.text = itemView.context.getString(R.string.dish_price, dish.price)
            dishImage.setImageResource(getDishPhoto(dish.photo))

            getAllergenInfo(dish, allergens, ivAllergens)

            itemView.setOnClickListener {
                onDishClickListener?.onDishClicked(position, dish, tableNumber, itemView);
            }

        }


    }

    interface OnDishClickListener {
        fun onDishClicked(position: Int, dish: Dish, tableNumber: Int, view: View)
    }
}