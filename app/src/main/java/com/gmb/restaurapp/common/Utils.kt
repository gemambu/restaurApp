package com.gmb.restaurapp.common

import android.widget.ImageView
import android.widget.TextView
import com.gmb.restaurapp.R
import com.gmb.restaurapp.model.Dish

fun getAllergenInfo(dish: Dish, allergens: MutableList<TextView>, icons: MutableList<ImageView>) {

    if (dish.allergens != null && dish.allergens.isNotEmpty()){
        for (i in 0 until dish.allergens.size){
            allergens[i].text = dish.allergens[i].name

            val icon = getAllergenIcon(dish.allergens[i].icon)
            icons[i].setImageResource(icon)
        }
    }
}

fun getAllergenIcon(icon: String): Int {
    return ALLERGENS[icon] ?: 0
}

fun getDishPhoto(photo: String): Int{
    return PICTURES[photo] ?: R.drawable.no_photo
}