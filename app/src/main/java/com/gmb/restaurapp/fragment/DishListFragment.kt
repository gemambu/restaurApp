package com.gmb.restaurapp.fragment

import android.app.Activity
import android.app.Fragment
import android.content.Context
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
        val ARG_TABLE_NUMBER = "ARG_TABLE_POSITION"

        var listData: MutableList<Dish>? = null
        var onDishClickListener: DishRecyclerViewAdapter.OnDishClickListener? = null


        lateinit var table: Table

        fun newInstance(dishList: MutableList<Dish>?, tableNumber: Int): DishListFragment {
            val arguments = Bundle()

            arguments.putSerializable(ARG_DISH_LIST, dishList as? Serializable)
            arguments.putInt(ARG_TABLE_NUMBER, tableNumber)

            val fragment = DishListFragment()
            fragment.arguments = arguments

            return fragment
        }
    }

    private lateinit var dishList: RecyclerView
    private lateinit var root: View

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {

        if (inflater != null) {
            root = inflater.inflate(R.layout.fragment_dish_list, container, false)

            listData = arguments.getSerializable(ARG_DISH_LIST) as? MutableList<Dish>
            table = Tables[arguments.getInt(ARG_TABLE_NUMBER)]

            val dishListAdapter = DishRecyclerViewAdapter(listData, table.number, onDishClickListener)

            dishList = root.findViewById(R.id.recycler_view_dish)
            dishList.adapter = dishListAdapter
            dishList.layoutManager = GridLayoutManager(activity, 1)
            dishList.itemAnimator = DefaultItemAnimator()

        }

        return root

    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        commonOnAttach(context)
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        commonOnAttach(activity)
    }

    private fun commonOnAttach(context: Context?) {
        // Aquí nos llaman cuando el fragment "se engancha" a la actividad, y por tanto ya pertence a ella
        // Lo que vamos a hacer es quedarnos con la referencia a esa actividad para cuando tengamos que avisarle de "cosas"
        if (context is DishRecyclerViewAdapter.OnDishClickListener) {
            onDishClickListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()

        // Si la actividad se "desengancha" de este fragment ya no tiene sentido guardar una referencia a ella, ya no le vamos
        // a avisar de nada, lo ponemos a null
        onDishClickListener = null
    }

}