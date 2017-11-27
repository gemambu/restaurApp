package com.gmb.restaurapp

import com.gmb.restaurapp.model.Tables
import org.junit.Assert
import org.junit.Test

class TablesModelUnitTest {


    @Test
    fun createTables_isCorrect() {
        Tables.initTables(10)
        Assert.assertEquals(10, Tables.count)
    }

    @Test
    fun addTable_isCorrect(){
        Tables.addTable()
        Assert.assertEquals(11, Tables.count)
    }


}