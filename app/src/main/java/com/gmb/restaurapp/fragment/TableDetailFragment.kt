package com.gmb.restaurapp.fragment

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.gmb.restaurapp.R
import com.gmb.restaurapp.model.DishOrdered
import com.gmb.restaurapp.model.Table


class TableDetailFragment : Fragment(){

    companion object {

        private val EXTRA_TABLE = "EXTRA_TABLE"

        fun newInstance(table: Table?): TableDetailFragment {
            val fragment = TableDetailFragment()
            val arguments = Bundle()
            arguments.putSerializable(TableDetailFragment.EXTRA_TABLE, table)
            fragment.arguments = arguments
            return fragment
        }
    }

    lateinit var root: View
    //private var onDishSelectedListener: TableDetailFragment.OnDishSelectedListener? = null

    var table: Table? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        if (inflater != null){
            root = inflater?.inflate(R.layout.fragment_table_detail, container, false)

            val name = root.findViewById<TextView>(R.id.table_detail_title)
            // val dishList = root.findViewById<ListView>(R.id.table_dishes_list)
            val calculateBill = root.findViewById<Button>(R.id.calculate_bill_btn)


            if (arguments != null) {
                table = arguments.getSerializable(EXTRA_TABLE) as Table?
            }

           /* val adapter = ArrayAdapter<Table>(activity, android.R.layout.simple_list_item_1, table.)
            dishList.adapter = adapter

            //nos enteramos de que se ha pulsado un elemento de la lista
            dishList.setOnItemClickListener{ parent, view, position, id ->
                // notifico al listener
                onDishSelectedListener?.onDishSelected(table.getDish(position), position)
            }*/
        }

        return root

    }

    /*
    interface OnDishSelectedListener {
        fun onDishSelected(dish: DishOrdered?, position: Int)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    @Suppress("OverridingDeprecatedMember", "DEPRECATION")
    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
    }

    override fun onDetach() {
        super.onDetach()
    }

*/
}