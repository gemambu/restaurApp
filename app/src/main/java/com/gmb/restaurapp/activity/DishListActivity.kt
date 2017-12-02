package com.gmb.restaurapp.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import com.gmb.restaurapp.R
import com.gmb.restaurapp.adapter.DishRecyclerViewAdapter
import com.gmb.restaurapp.common.MENU
import com.gmb.restaurapp.common.RQ_MENU
import com.gmb.restaurapp.fragment.DishListFragment
import com.gmb.restaurapp.model.Dish
import com.gmb.restaurapp.model.Tables
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import java.io.Serializable


class DishListActivity : AppCompatActivity(), DishRecyclerViewAdapter.OnDishClickListener {

    companion object {
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

        val listDishes = intent.getSerializableExtra(EXTRA_DISH_LIST) as? MutableList<Dish>
        tablePosition = intent.getIntExtra(EXTRA_POSITION, 0)

        async(UI) {
            val fragment = DishListFragment.newInstance(listDishes, tablePosition)
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.dish_list_fragment, fragment)
                    .commit()

        }

        supportActionBar?.title = getString(R.string.table_menu_title, Tables[tablePosition].number)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onDishClicked(position: Int, dish: Dish, tableNumber: Int, view: View) {
        val intentDetail = DishDetailActivity.intent(this, dish, tablePosition)
        intentDetail.action = MENU
        startActivityForResult(intentDetail, RQ_MENU)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == RQ_MENU && resultCode == RESULT_OK) {
            finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            // se ha pulsado la flecha de back
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }


}