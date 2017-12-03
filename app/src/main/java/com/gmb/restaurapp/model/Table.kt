package com.gmb.restaurapp.model

import java.io.Serializable


class Table(val number: Int,
            private var dishes: MutableMap<Int, Dish>?) : Serializable {

    companion object {
        var index: Int = 0
    }

    init {
        dishes = mutableMapOf()
    }

    fun getValues() : MutableList<Dish> {
        val listDishes = mutableListOf<Dish>()

        if(dishes != null && !dishes!!.isEmpty()){
            listDishes.addAll(dishes!!.values)
        }

        return listDishes
    }

    val count
        get() = dishes?.size ?: 0

    fun addDish(dish: Dish) {
        dishes?.put(dish.id ?: 0, dish)
    }


    fun updateDish(dishId: Int, newVariant: String?) = dishes?.get(dishId)?.updateVariant(newVariant)


    fun removeDish(dishId: Int) = dishes?.remove(dishId)


    fun getNextId() : Int = index++

    fun calculateBill() : Float {

        var total = 0f

        if (dishes != null && dishes!!.isNotEmpty()){
            dishes?.forEach { (_, value) ->
                total += value.price }
        }

        return total
    }

    override fun toString(): String {
        return "Mesa ${this.number}"
    }
}