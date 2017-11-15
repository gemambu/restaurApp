package com.gmb.restaurapp.model

import java.io.Serializable


object Tables : Serializable {

    private var tables = mutableListOf<Table>()

    fun initTables(number: Int) {
        for(position in 1..number){
            tables.add(Table(position, null))
        }

    }

    val count
        get() = tables.size

    operator fun get(i: Int) = tables[i]

    fun toArray() = tables.toTypedArray()

    fun addTable() {
        tables.add(Table(count+1, null))
    }

}