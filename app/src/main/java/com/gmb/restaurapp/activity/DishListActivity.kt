package com.gmb.restaurapp.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.gmb.restaurapp.R
import com.gmb.restaurapp.fragment.DishListFragment
import com.gmb.restaurapp.model.Dish
import com.gmb.restaurapp.model.Table
import kotlinx.android.synthetic.main.activity_table_detail.view.*


class DishListActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_dish_list)

        if (findViewById<View>(R.id.dish_list_fragment) != null){

            if (fragmentManager.findFragmentById(R.id.dish_list_fragment) == null) {
                val table = intent.getSerializableExtra("ARG_TABLE") as Table
                val fragment = DishListFragment.newInstance(MainActivity?.menu as MutableList<Dish>, table)
                fragmentManager.beginTransaction()
                        .add(R.id.dish_list_fragment, fragment)
                        .commit()
            }
        }



    }
}