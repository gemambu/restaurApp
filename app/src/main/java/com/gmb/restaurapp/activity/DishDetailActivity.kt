package com.gmb.restaurapp.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import com.gmb.restaurapp.R
import com.gmb.restaurapp.common.PREVIOUS_ACTIITY
import com.gmb.restaurapp.common.PREV_ACT
import com.gmb.restaurapp.fragment.DishDetailFragment
import com.gmb.restaurapp.fragment.DetailDishListener
import com.gmb.restaurapp.model.Dish
import com.gmb.restaurapp.model.Tables
import java.io.Serializable

class DishDetailActivity : AppCompatActivity(), DetailDishListener {


    companion object {
        val EXTRA_DISH = "EXTRA_DISH"
        val EXTRA_TABLE_POSITION = "EXTRA_TABLE_POSITION"

        fun intent(context: Context, dish: Dish, position: Int): Intent {

            val intent = Intent(context, DishDetailActivity::class.java)
            intent.putExtra(EXTRA_DISH, dish as? Serializable)
            intent.putExtra(EXTRA_TABLE_POSITION, position)
            return intent
        }
    }

    lateinit var dish: Dish
    var tablePosition: Int = 0
    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dish_detail)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.title = getString(R.string.detail_table_number, Tables[tablePosition].number)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        context = baseContext

        dish = intent.getSerializableExtra(EXTRA_DISH) as Dish
        tablePosition = intent.getIntExtra(EXTRA_TABLE_POSITION, 0)

        val fragment = DishDetailFragment.newInstance(dish, tablePosition)
        fragmentManager.beginTransaction()
                .add(R.id.dish_detail_fragment, fragment)
                .addToBackStack(null)
                .commit()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            // se ha pulsado la flecha de back
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSavePressed(view: View) {

        finish()
    }
    

    override fun onCancelPressed(view: View) {

//        when(PREVIOUS_ACTIITY){
//            PREV_ACT.MENU -> {
//                startActivity(DishListActivity.intent(context,
//                        MainActivity.menu as MutableList<Dish>,
//                        TableDetailActivity.tablePosition))
//
//            }
//            PREV_ACT.TABLE_DETAIL -> {
//                val intent = Intent(this, TableDetailActivity::class.java)
//                intent.putExtra(MainActivity.EXTRA_TABLE_NUMBER, tablePosition)
//                startActivity(intent)
//            }
//        }
//
//        finish()
    }
}
