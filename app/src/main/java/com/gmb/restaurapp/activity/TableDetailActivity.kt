package com.gmb.restaurapp.activity

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.gmb.restaurapp.R
import com.gmb.restaurapp.activity.MainActivity.Companion.EXTRA_TABLE_NUMBER
import com.gmb.restaurapp.adapter.DishRecyclerViewAdapter
import com.gmb.restaurapp.common.PREVIOUS_ACTIITY
import com.gmb.restaurapp.common.PREV_ACT
import com.gmb.restaurapp.fragment.DishDetailFragment
import com.gmb.restaurapp.fragment.DishListFragment
import com.gmb.restaurapp.model.Dish
import com.gmb.restaurapp.model.Table
import com.gmb.restaurapp.model.Tables
import kotlinx.android.synthetic.main.activity_table_detail.*


class TableDetailActivity : AppCompatActivity(), DishRecyclerViewAdapter.OnDishClickListener {

    companion object {
        lateinit var table: Table
        var tablePosition: Int = 0
    }

    lateinit var context: Context


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table_detail)


        context = baseContext

        // findViewById<View>(R.id.calculate_bill_btn).setOnClickListener { showBill() }
        show_menu_btn.setOnClickListener { _ -> showMenu() }

        val intent = intent
        tablePosition = intent.getIntExtra(EXTRA_TABLE_NUMBER, 1)

        table = Tables.get(tablePosition)
    }

    override fun onResume() {
        super.onResume()
        showDishes()
    }


    private fun showDishes() {

        val fragment = DishListFragment.newInstance(table.dishes, tablePosition)
        fragmentManager
                .beginTransaction()
                .replace(R.id.dish_list_fragment, fragment)
                .commit()
    }

    private fun showMenu() {
        startActivity(DishListActivity.intent(context,
                MainActivity.menu as MutableList<Dish>,
                tablePosition))

    }

    private fun showBill() {

        val total = table.calculateBill()
        var title = "Cuenta de la mesa ${table.number}"
        var message = "Total a pagar: ${total} euros"


        when (total) {
            0f -> message = "No hay platos añadidos."
        }

        AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(getString(android.R.string.ok), { dialog, _ ->
                    dialog.dismiss()
                })
                .show()
    }

    override fun onDishClicked(position: Int, dish: Dish, tableNumber: Int, view: View) {

        PREVIOUS_ACTIITY = PREV_ACT.DETAIL
        val fragment = DishDetailFragment.newInstance(dish, tablePosition)
        fragmentManager.beginTransaction()
                .replace(R.id.dish_list_fragment, fragment)
                .addToBackStack(null)
                .commit()
    }
}