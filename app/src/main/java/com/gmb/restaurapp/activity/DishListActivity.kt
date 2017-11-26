package com.gmb.restaurapp.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import com.gmb.restaurapp.fragment.DishListFragment
import com.gmb.restaurapp.R
import com.gmb.restaurapp.adapter.DishRecyclerViewAdapter
import com.gmb.restaurapp.common.PREVIOUS_ACTIITY
import com.gmb.restaurapp.common.PREV_ACT
import com.gmb.restaurapp.fragment.DishDetailFragment
import com.gmb.restaurapp.fragment.OnSaveButtonPressedListener
import com.gmb.restaurapp.model.Dish
import com.gmb.restaurapp.model.Tables
import java.io.Serializable


class DishListActivity: AppCompatActivity(),  DishRecyclerViewAdapter.OnDishClickListener, OnSaveButtonPressedListener {

    companion object {
        val EXTRA_RESULT_DATA = "EXTRA_RESULT_DATA"
        val EXTRA_DISH_LIST = "EXTRA_DISH_LIST"
        val EXTRA_POSITION = "EXTRA_POSITION"


        var tablePosition = 0


        fun intent(context: Context, dishList: MutableList<Dish>?, number: Int): Intent {

            val intent = Intent(context, DishListActivity::class.java)

            intent.putExtra(EXTRA_DISH_LIST, dishList as? Serializable)
            intent.putExtra(EXTRA_POSITION, number)

            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dish_list)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        var listDishes =  intent.getSerializableExtra(EXTRA_DISH_LIST) as? MutableList<Dish>
        tablePosition= intent.getIntExtra(EXTRA_POSITION, 0)

        val fragment = DishListFragment.newInstance(listDishes, tablePosition)
        fragmentManager
                .beginTransaction()
                .replace(R.id.dish_list_fragment, fragment)
                .commit()

        supportActionBar?.title = "Menú para mesa: ${Tables[tablePosition].number}"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onDishClicked(position: Int, dish: Dish, tableNumber: Int, view: View) {

        PREVIOUS_ACTIITY = PREV_ACT.MENU
        val fragment = DishDetailFragment.newInstance(dish, tablePosition)
        fragmentManager.beginTransaction()
                .replace(R.id.dish_list_fragment, fragment)
                .commit()
    }

    override fun onSavePressed(view: View) {
        //finish()


    }


}