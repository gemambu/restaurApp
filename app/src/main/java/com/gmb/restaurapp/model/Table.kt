package com.gmb.restaurapp.model

import java.io.Serializable


class Table(val number: Int,
            var dishes: MutableList<Dish>?) : Serializable {

    init {
        dishes = mutableListOf()
    }

    val count
        get() = dishes?.size ?: 0

    fun addDish(dish: Dish) {
        dishes?.add(dish)
    }

    fun calculateBill() : Float {

        var total = 0f

        if (dishes != null && dishes!!.size > 0){
            dishes?.map { total += it.price }
        }

        return total
    }

    override fun toString(): String {
        return "Mesa ${this.number}"
    }
}