package com.gmb.restaurapp.fragment

import android.app.Activity
import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gmb.restaurapp.R
import com.gmb.restaurapp.adapter.DishRecyclerViewAdapter
import com.gmb.restaurapp.model.Dish
import com.gmb.restaurapp.model.Table
import com.gmb.restaurapp.model.Tables
import java.io.Serializable


class DishListFragment : Fragment() {

    companion object {
        val ARG_DISH_LIST = "ARG_DISH_LIST"
        val EXTRA_TABLE_NUMBER = "EXTRA_TABLE_POSITION"
        val EXTRA_LISTENER = "EXTRA_LISTENER"

        var listData: MutableList<Dish>? = null

        lateinit var dishList: RecyclerView
        lateinit var root: View
        lateinit var table: Table

        fun newInstance(dishList: MutableList<Dish>?, tableNumber: Int, listener: DishRecyclerViewAdapter.OnDishClickListener): DishListFragment {
            val arguments = Bundle()

            arguments.putSerializable(ARG_DISH_LIST, dishList as? Serializable)
            arguments.putInt(EXTRA_TABLE_NUMBER, tableNumber)
            arguments.putSerializable(EXTRA_LISTENER, listener as Serializable)

            val fragment = DishListFragment()
            fragment.arguments = arguments

            return fragment
        }
    }



    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {

        if (inflater != null) {
            root = inflater?.inflate(R.layout.fragment_dish_list, container, false)

            listData = arguments.getSerializable(ARG_DISH_LIST) as? MutableList<Dish>
            table = Tables.get(arguments.getInt(EXTRA_TABLE_NUMBER))
            val listener = arguments.getSerializable(EXTRA_LISTENER) as? DishRecyclerViewAdapter.OnDishClickListener

            val dishListAdapter = DishRecyclerViewAdapter(listData, table.number, listener)

            dishList = root.findViewById<RecyclerView>(R.id.recycler_view_dish)
            dishList.adapter = dishListAdapter
            dishList.layoutManager = GridLayoutManager(activity, 1)
            dishList.itemAnimator = DefaultItemAnimator()

        }

        return root

    }

}