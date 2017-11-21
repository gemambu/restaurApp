package com.gmb.restaurapp.activity

import android.app.AlertDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import com.gmb.restaurapp.R
import com.gmb.restaurapp.activity.MainActivity.Companion.TABLE_NUMBER
import com.gmb.restaurapp.adapter.DishRecyclerViewAdapter
import com.gmb.restaurapp.fragment.DishDetailFragment
import com.gmb.restaurapp.fragment.DishListFragment
import com.gmb.restaurapp.model.Dish
import com.gmb.restaurapp.model.Table
import com.gmb.restaurapp.model.Tables
import java.io.Serializable


class TableDetailActivity : AppCompatActivity(), DishRecyclerViewAdapter.OnDishClickListener, Serializable {

    companion object {
        lateinit var table: Table
        var tablePosition: Int = 0
    }


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table_detail)

        val name = findViewById<TextView>(R.id.table_detail_title)

        findViewById<View>(R.id.calculate_bill_btn).setOnClickListener { showBill() }
        findViewById<View>(R.id.show_menu_btn).setOnClickListener { showMenu() }

        val intent = intent

        tablePosition = intent.getIntExtra(TABLE_NUMBER, 1)
        table = Tables.get(tablePosition)
        name.text = getString(R.string.detail_table_number, table.number ?: 1)

        showDishes()
    }

    private fun showDishes() {
        // dummy data
        //table.addDish(Dish(1, "Dim sum", "dilisius dim sum", 12.5f, 3, null, "con extra de picante"))

  //      val currentFragment = fragmentManager
    //            .findFragmentById(R.id.dish_list_fragment)

      //  if (currentFragment == null) {
            val fragment = DishListFragment.newInstance(table.dishes, table.number, this)

            fragmentManager
                    .beginTransaction()
                    .replace(R.id.dish_list_fragment, fragment)
                    .commit()

        //}

    }

    private fun showMenu() {

        if (findViewById<View>(R.id.dish_list_fragment) != null){

                val fragment = DishListFragment.newInstance(MainActivity?.menu as MutableList<Dish>, table.number, this)
                fragmentManager.beginTransaction()
                        .replace(R.id.dish_list_fragment, fragment)
                        .addToBackStack(null)
                        .commit()
        }

    }

    private fun showBill() {
        AlertDialog.Builder(this)
                .setTitle("Cuenta de la mesa: ${table.number}")
                .setMessage("Total a pagar: ${table.calculateBill() ?: 0} euros" )
                .setPositiveButton(getString(android.R.string.ok),  { dialog, _ ->
                    dialog.dismiss()

                })
                .show()
    }

    override fun onDishClicked(position: Int, dish: Dish, tableNumber: Int, view: View) {


            val fragment = DishDetailFragment.newInstance(dish, tablePosition)
            fragmentManager.beginTransaction()
                    .replace(R.id.dish_list_fragment, fragment)
                    .addToBackStack(null)
                    .commit()
    }
}