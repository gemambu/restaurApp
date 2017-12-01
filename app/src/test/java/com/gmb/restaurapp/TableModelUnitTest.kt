package com.gmb.restaurapp

import com.gmb.restaurapp.model.Dish
import com.gmb.restaurapp.model.Table
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before


class TableModelUnitTest {

    private lateinit var table: Table
    private lateinit var emptyTable: Table

    @Before
    fun setup() {
        val dish = Dish(1,
                "plato1",
                "desc plato 1",
                22.3f,
                "aa",
                null,
                "variante cliente 1")

        val dish2 = Dish(2,
                "plato2",
                "desc plato 2",
                30.0f,
                "bb",
                null,
                null)

        val dishes: MutableMap<Int, Dish> = mutableMapOf(1 to dish, 2 to dish2)
        table = Table(1, dishes)

        emptyTable = Table(2, null)
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
}
