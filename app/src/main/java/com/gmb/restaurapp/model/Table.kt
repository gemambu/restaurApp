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

    fun updateDish(dishId: Int, newVariant: String?){
        dishes?.forEach {
            if (dishId == it.id){
                it.updateVariant(newVariant)
            }
        }
    }

    fun removeDish(dishId: Int){
        dishes?.forEach {
            if (dishId == it.id){
                dishes?.remove(it)
            }
        }
    }

    fun getNextId() : Int{
        var lastIndex = 1
        dishes?.forEach {
            if (it.id != null && lastIndex < it.id!!) lastIndex++
        }

        return lastIndex
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