package com.gmb.restaurapp.model

import java.io.Serializable


open class Dish(val id: Int,
                val name: String,
                val description: String,
                val price: Float,
                val photo: Int,
                val allergens: List<Allergen>?,
                var variant: String?
) : Serializable {

    fun getAllergens() = allergens?.size ?: 0

    fun updateVariant(newVariant: String){
        this.variant = newVariant
    }

    fun copy() = Dish(id, name, description, price, photo, allergens, variant)



}