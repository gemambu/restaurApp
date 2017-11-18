package com.gmb.restaurapp.activity

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.gmb.restaurapp.R
import com.gmb.restaurapp.adapter.DishRecyclerViewAdapter
import com.gmb.restaurapp.fragment.DishListFragment
import com.gmb.restaurapp.model.Dish
import com.gmb.restaurapp.model.Table


class TableDetailActivity : AppCompatActivity() {

    companion object {
        var table: Table? = null
    }

    lateinit var dishList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table_detail)

        val name = findViewById<TextView>(R.id.table_detail_title)

        findViewById<View>(R.id.calculate_bill_btn).setOnClickListener { showBill() }
        findViewById<View>(R.id.show_menu_btn).setOnClickListener { showMenu() }

        val intent = intent

        table = intent.getSerializableExtra("EXTRA_TABLE") as? Table
        name.text = getString(R.string.detail_table_number, table?.number ?: 1)

        table?.addDish(Dish(1, "Dim sum", "dilisius dim sum", 12.5f, 3, null, "con extra de picante"))
        table?.addDish(Dish(2, "Dim sum 2", "dilisius dim sum 2", 14.05f, 3, null, null))
        table?.addDish(Dish(3, "Sopa miso", "sopita pal invierniqui", 9.95f, 3, null, null))

        val currentFragment = fragmentManager
                .findFragmentById(R.id.fragmentDishList)

        if (currentFragment == null){
            val fragment = DishListFragment.newInstance(table?.dishes, table)
            fragmentManager
                    .beginTransaction()
                    .add(R.id.fragmentDishList, fragment)
                    .commit()
        }
    }

    private fun showMenu() {

        var intent = Intent(this, DishListActivity::class.java)
        var bundle = Bundle()
        bundle.putSerializable("ARG_DISH_LIST", MainActivity?.menu?.toTypedArray())
        bundle.putSerializable("ARG_TABLE", table)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    private fun showBill() {
        AlertDialog.Builder(this)
                .setTitle("Cuenta de la mesa: ${table?.number}")
                .setMessage("Total a pagar: ${table?.calculateBill() ?: 0} euros" )
                .setPositiveButton(getString(android.R.string.ok),  { dialog, _ ->
                    dialog.dismiss()

                })
                .show()
    }
}