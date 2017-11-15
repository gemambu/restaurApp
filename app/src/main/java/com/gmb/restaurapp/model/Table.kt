package com.gmb.restaurapp.model

import java.io.Serializable


class Table(val number: Int,
            val dishes: List<DishOrdered>?) : Serializable {

    val count
        get() = dishes?.size ?: 0

    fun calculateBill() : Float {

        var total = 0f

        if (dishes != null && dishes.size > 0){
            dishes?.map { total += it.price }
        }

        return total
    }

    override fun toString(): String {
        return "Mesa ${this.number}"
    }
}