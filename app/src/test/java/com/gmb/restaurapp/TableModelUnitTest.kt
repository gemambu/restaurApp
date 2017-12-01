package com.gmb.restaurapp

import com.gmb.restaurapp.model.Dish
import com.gmb.restaurapp.model.Table
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before


class TableModelUnitTest {

    private lateinit var table: Table
    private lateinit var emptyTable: Table
    private lateinit var dish: Dish
    private lateinit var dish2: Dish

    @Before
    fun setup() {
         dish = Dish(1,
                "plato1",
                "desc plato 1",
                22.3f,
                "aa",
                null,
                "variante cliente 1")

         dish2 = Dish(2,
                "plato2",
                "desc plato 2",
                30.0f,
                "bb",
                null,
                null)

        val dishes: MutableMap<Int, Dish> = mutableMapOf(1 to dish, 2 to dish2)
        table = Table(1, dishes)

        emptyTable = Table(2, null)

        addDishes()
    }

    private fun addDishes() {
        table.addDish(dish)
        table.addDish(dish2)
    }

    @Test
    fun emptyTableBill_isCorrect() {
        assertEquals(0f, emptyTable.calculateBill())
    }

    @Test
    fun tableBill_isCorrect() {
        assertEquals(52.3f, table.calculateBill())
    }



    @Test
    fun emptyTableDishesCount_isCorrect() {
        assertEquals(0, emptyTable.count)
    }

    @Test
    fun tableDishesCount_isCorrect() {
        assertEquals(2, table.count)
    }

    @Test
    fun getValuesFullTable_isCorrect(){
        assertEquals(2, table.getValues().size)
    }

    @Test
    fun getValuesEmptyTable_isCorrect(){
        assertEquals(0, emptyTable.getValues().size)
    }

    @Test
    fun addDishInEmptyTable_isCorrect(){
        assertEquals(0, emptyTable.getValues().size)
        emptyTable.addDish(Dish(1, "aa","desc", 33f, "photo", null, null))
        assertEquals(1, emptyTable.getValues().size)
    }

    @Test
    fun addDishInFullTable_isCorrect(){
        assertEquals(2, table.getValues().size)
        table.addDish(Dish(3, "aa","desc", 33f, "photo", null, null))
        assertEquals(3, table.getValues().size)
    }

    @Test
    fun removeDishInEmptyTable_isCorrect(){
        assertEquals(0, emptyTable.getValues().size)
        emptyTable.removeDish(1)
        assertEquals(0, emptyTable.getValues().size)
    }

    @Test
    fun removeDishInFullTable_isCorrect(){
        assertEquals(2, table.getValues().size)
        table.removeDish(1)
        assertEquals(1, table.getValues().size)
    }

    @Test
    fun getNextId_isCorrect(){
        assertEquals(0, table.getNextId())
    }



}
