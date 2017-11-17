package com.gmb.restaurapp.activity

import android.app.AlertDialog
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
import com.gmb.restaurapp.model.DishOrdered
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

        table?.addDish(DishOrdered(1, "Dim sum", "dilisius dim sum", 12.5f, 3, null, "con extra de picante"))
        table?.addDish(DishOrdered(2, "Dim sum 2", "dilisius dim sum 2", 14.05f, 3, null, null))
        table?.addDish(DishOrdered(3, "Sopa miso", "sopita pal invierniqui", 9.95f, 3, null, null))

        dishList = findViewById<RecyclerView>(R.id.recycler_view_dish)
        val dishListAdapter = DishRecyclerViewAdapter(table?.dishes)
        dishList.adapter = dishListAdapter

        dishList.layoutManager = GridLayoutManager(this, 1)
        dishList.itemAnimator = DefaultItemAnimator()
    }

    private fun showMenu() {

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