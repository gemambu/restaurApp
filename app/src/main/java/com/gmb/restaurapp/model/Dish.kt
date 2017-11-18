package com.gmb.restaurapp.model

import java.io.Serializable


open class Dish(val id: Int,
                val name: String,
                val description: String,
                val price: Float,
                val photo: Int,
                val allergens: List<Allergen>?, val variant: String?
) : Serializable {

    fun getAllergens() = allergens?.size ?: 0


}