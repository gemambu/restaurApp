package com.gmb.restaurapp.fragment

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gmb.restaurapp.R
import com.gmb.restaurapp.activity.TableDetailActivity
import com.gmb.restaurapp.adapter.DishRecyclerViewAdapter
import com.gmb.restaurapp.model.Dish
import com.gmb.restaurapp.model.Table
import java.io.Serializable


class DishListFragment : Fragment() {

    companion object {
        val ARG_DISH_LIST = "ARG_DISH_LIST"
        val EXTRA_TABLE = "EXTRA_TABLE"
        val EXTRA_LISTENER = "EXTRA_LISTENER"

        var listData: MutableList<Dish>? = null

        fun newInstance(dishList: MutableList<Dish>?, table: Table?, listener: TableDetailActivity): DishListFragment {
            val arguments = Bundle()

            arguments.putSerializable(ARG_DISH_LIST, dishList as? Serializable)
            arguments.putSerializable(EXTRA_TABLE, table as? Table)
            arguments.putSerializable(EXTRA_LISTENER, listener as TableDetailActivity)

            val fragment = DishListFragment()
            fragment.arguments = arguments
            return fragment
        }
    }

    lateinit var dishList: RecyclerView
    lateinit var root: View

    lateinit var table: Table

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {

        if (inflater != null) {
            root = inflater?.inflate(R.layout.fragment_dish_list, container, false)

            listData = arguments.getSerializable(ARG_DISH_LIST) as? MutableList<Dish>
            table = arguments.getSerializable(EXTRA_TABLE) as Table
            val listener = arguments.getSerializable(EXTRA_LISTENER) as? TableDetailActivity

            val dishListAdapter = DishRecyclerViewAdapter(listData, table, listener)

            dishList = root.findViewById<RecyclerView>(R.id.recycler_view_dish)
            dishList.adapter = dishListAdapter
            dishList.layoutManager = GridLayoutManager(activity, 1)
            dishList.itemAnimator = DefaultItemAnimator()

        }

        return root

    }

}