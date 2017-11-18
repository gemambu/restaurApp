package com.gmb.restaurapp.adapter

import android.app.AlertDialog
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.gmb.restaurapp.R
import com.gmb.restaurapp.activity.DishDetailActivity
import com.gmb.restaurapp.activity.DishDetailActivity.Companion.EXTRA_DISH_DETAIL
import com.gmb.restaurapp.activity.DishDetailActivity.Companion.EXTRA_TABLE_DETAIL
import com.gmb.restaurapp.activity.TableDetailActivity
import com.gmb.restaurapp.model.Dish
import com.gmb.restaurapp.model.Table


class DishRecyclerViewAdapter(val dishList: List<Dish>?, val table: Table) : RecyclerView.Adapter<DishRecyclerViewAdapter.DishViewHolder>() {

    var onClickListener: View.OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DishViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.content_dish, parent, false)
        view.setOnClickListener(onClickListener)
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
        val allergen = itemView.findViewById<TextView>(R.id.allergen)
        val allergen2 = itemView.findViewById<TextView>(R.id.allergen2)
        val allergen3 = itemView.findViewById<TextView>(R.id.allergen3)
        val allergen4 = itemView.findViewById<TextView>(R.id.allergen4)

        fun bindDish(dish: Dish, position: Int) {
            // accedemos al contexto
            val context = itemView.context

            // actualizamos la vista (itemView, que es la tarjeta) con el modelo
            dishName.text = dish.name
            dishPrice.text = dish.price.toString()
            dishImage.setImageResource(R.drawable.dim_sum)

            allergen.text = "gluten"

            itemView.setOnClickListener {
                var intent = Intent(context, DishDetailActivity::class.java)
                intent.putExtra(EXTRA_DISH_DETAIL, dish)
                intent.putExtra(EXTRA_TABLE_DETAIL, table)

                val root = itemView.context
                root.startActivity(intent)

            }
        }


    }
}