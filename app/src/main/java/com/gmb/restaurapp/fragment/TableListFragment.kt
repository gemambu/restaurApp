package com.gmb.restaurapp.fragment


import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import com.gmb.restaurapp.model.Table
import com.gmb.restaurapp.model.Tables
import com.gmb.restaurapp.R


class TableListFragment : Fragment() {

    companion object {

        private val EXTRA_TABLE = "EXTRA_TABLE"

        fun newInstance(): TableListFragment {
            val fragment = TableListFragment()
            return fragment
        }
    }

    lateinit var root: View
    private var onTableSelectedListener: OnTableSelectedListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        if (inflater != null){
            root = inflater?.inflate(R.layout.fragment_table_list, container, false)
            val list = root.findViewById<ListView>(R.id.table_list_fragment)

            // Quizá pueda meter como setup el número de mesas
            Tables.initTables(10)


            val adapter = ArrayAdapter<Table>(activity, android.R.layout.simple_list_item_1, Tables.toArray())
            list.adapter = adapter

            //nos enteramos de que se ha pulsado un elemento de la lista
            list.setOnItemClickListener{ parent, view, position, id ->
                // notifico al listener
                onTableSelectedListener?.onTableSelected(Tables.get(position), position)
            }
        }

        return root

    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        commonAttach(context)


    }

    @Suppress("OverridingDeprecatedMember", "DEPRECATION")
    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        commonAttach(activity)
    }

    override fun onDetach() {
        super.onDetach()
        onTableSelectedListener = null
    }

    fun commonAttach(listener: Any?){
        if (listener is OnTableSelectedListener) {
            onTableSelectedListener = listener
        }
    }

    interface OnTableSelectedListener {
        fun onTableSelected(table: Table?, position: Int)
    }
}