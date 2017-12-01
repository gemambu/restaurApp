package com.gmb.restaurapp.model

import java.io.Serializable


open class Dish(var id: Int?,
                val name: String,
                val description: String,
                val price: Float,
                val photo: String,
                val allergens: List<Allergen>?,
                var variant: String?
) : Serializable {

    fun updateVariant(newVariant: String?){
        this.variant = newVariant
    }

    fun updateId(newId: Int) {
        this.id = newId
    }

    fun copy() = Dish(null, name, description, price, photo, allergens, variant)



}