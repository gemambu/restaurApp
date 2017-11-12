package com.gmb.restaurapp.model

import java.io.Serializable


open class Dish(val id: Int,
           val name: String,
           val description: String,
           val price: Float,
           val photo: Int,
           val allergens: List<Allergen>?
           ) : Serializable {

    fun getAllergens() = allergens?.size ?: 0
}

class DishOrdered(id: Int,
                  name: String,
                  description: String,
                  price: Float,
                  photo: Int,
                  allergens: List<Allergen>?,
                  variant: String?) : Dish(id, name, description, price, photo, allergens) {

}