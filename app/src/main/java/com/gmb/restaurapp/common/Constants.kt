package com.gmb.restaurapp.common

import com.gmb.restaurapp.R

enum class VIEW_MAIN(val index: Int){
    LOADING(0),
    SHOW_TABLES(1)
}

val ALLERGENS: Map<String, Int> = mapOf(
        "a_1" to R.drawable.a_1,
        "a_2" to R.drawable.a_2,
        "a_3" to R.drawable.a_3,
        "a_4" to R.drawable.a_4
)

val PICTURES: Map<String, Int> = mapOf(
        "d_1" to R.drawable.d_1,
        "d_2" to R.drawable.d_2,
        "d_3" to R.drawable.d_3,
        "d_4" to R.drawable.d_4,
        "d_5" to R.drawable.d_5
)