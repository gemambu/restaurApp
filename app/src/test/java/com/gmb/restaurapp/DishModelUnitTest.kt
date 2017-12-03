package com.gmb.restaurapp

import com.gmb.restaurapp.model.Dish
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class DishModelUnitTest {

    private lateinit var dish: Dish

    @Before
    fun setup() {
        dish = Dish(null, "plato 1", "mi desc", 22.22f, "miphoto", null, null)
    }

    @Test
    fun createDish_isCorrect() {
        Assert.assertNull(dish.id)
        Assert.assertNull(dish.allergens)
        Assert.assertNull(dish.variant)
        Assert.assertEquals("plato 1", dish.name)
        Assert.assertEquals(22.22f, dish.price)
        Assert.assertEquals("mi desc", dish.description)
        Assert.assertEquals("miphoto", dish.photo)
    }

    @Test
    fun updateVariant_isCorrect() {
        Assert.assertNull(dish.variant)
        dish.updateVariant("new variant")
        Assert.assertEquals("new variant", dish.variant)
    }

    @Test
    fun updateId_isCorrect() {
        Assert.assertNull(dish.id)
        dish.updateId(2)
        Assert.assertEquals(2, dish.id)
    }

    @Test
    fun copyDish_isCorrect() {
        val dishCopy = dish.copy()

        Assert.assertEquals(dish.id, dishCopy.id)
        Assert.assertEquals(dish.allergens, dishCopy.allergens)
        Assert.assertEquals(dish.variant, dishCopy.variant)
        Assert.assertEquals(dish.name, dishCopy.name)
        Assert.assertEquals(dish.price, dishCopy.price)
        Assert.assertEquals(dish.description, dishCopy.description)
        Assert.assertEquals(dish.photo, dishCopy.photo)
    }


}