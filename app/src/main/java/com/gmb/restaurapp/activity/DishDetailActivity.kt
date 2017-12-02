package com.gmb.restaurapp.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import com.gmb.restaurapp.R
import com.gmb.restaurapp.fragment.DishDetailFragment
import com.gmb.restaurapp.fragment.DetailDishListener
import com.gmb.restaurapp.model.Dish
import com.gmb.restaurapp.model.Tables
import java.io.Serializable
import android.app.Activity
import android.support.design.widget.Snackbar
import android.view.Menu
import com.gmb.restaurapp.common.MENU
import com.gmb.restaurapp.common.TABLE
import com.gmb.restaurapp.model.Table


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
    lateinit var table: Table
    private var tablePosition: Int = 0
    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dish_detail)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        context = baseContext

        dish = intent.getSerializableExtra(EXTRA_DISH) as Dish
        tablePosition = intent.getIntExtra(EXTRA_TABLE_POSITION, 0)
        table = Tables[tablePosition]

        val fragment = DishDetailFragment.newInstance(dish, tablePosition)
        fragmentManager.beginTransaction()
                .add(R.id.dish_detail_fragment, fragment)
                .addToBackStack(null)
                .commit()

        supportActionBar?.title = dish.name
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        if (dish.id != null) {
            super.onCreateOptionsMenu(menu)
            menuInflater?.inflate(R.menu.delete_dish, menu)

        }

        return true

    }


    override fun onSavePressed(view: View, dish: Dish, variant: String) {

        var message = ""

        when (intent.action) {
            MENU -> {
                val dishVariant = dish.copy()
                dishVariant.updateVariant(variant)
                dishVariant.updateId(table.getNextId())
                table.addDish(dishVariant)
                message = getString(R.string.message_dish_added, table.number)


            }
            TABLE -> {
                table.updateDish(dish.id!!, variant)
                message = getString(R.string.message_dish_updated, table.number)
            }
        }

        Snackbar.make(findViewById(android.R.id.content),
                message, Snackbar.LENGTH_LONG)
                .show()


        val newIntent = Intent()
        newIntent.putExtra("result", 0)
        finalizeActivity(RESULT_OK, newIntent)
    }

    override fun onCancelPressed(view: View) {
        finalizeActivity(Activity.RESULT_CANCELED, Intent())
    }

    private fun finalizeActivity(result: Int, intent: Intent) {
        setResult(result, intent)
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            // se ha pulsado la flecha de back
            finalizeActivity(Activity.RESULT_CANCELED, Intent())
            return true
        } else if (item?.itemId == R.id.delete_dish) {
            removeDish()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun removeDish() {

        table.removeDish(dish.id ?: 0)

        val intent = Intent()
        intent.putExtra("result", 0)
        finalizeActivity(RESULT_OK, intent)

        Snackbar.make(findViewById(android.R.id.content),
                getString(R.string.message_dish_removed), Snackbar.LENGTH_LONG)
                .show()
    }


    override fun onBackPressed() {
        super.onBackPressed()
        finalizeActivity(Activity.RESULT_CANCELED, Intent())
    }
}
