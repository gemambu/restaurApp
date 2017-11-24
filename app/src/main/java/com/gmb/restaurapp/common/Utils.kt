package com.gmb.restaurapp.common

import android.widget.TextView
import com.gmb.restaurapp.model.Dish

fun getAllergenInfo(dish: Dish, allergens: MutableList<TextView>) {

    if (dish.allergens != null && dish.allergens.isNotEmpty()){
        for (i in 0 until dish.allergens.size){
            allergens[i].text = dish.allergens[i].name
        }
    }
}