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
import com.gmb.restaurapp.R
import com.gmb.restaurapp.model.Table
import com.gmb.restaurapp.model.Tables


class TableListFragment : Fragment() {

    private lateinit var list: ListView
    private lateinit var root: View
    private var onTableSelectedListener: OnTableSelectedListener? = null

    companion object {
        fun newInstance() = TableListFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_table_list, container, false)
        list = root.findViewById(R.id.table_list_fragment)


        val adapter = ArrayAdapter<Table>(activity, android.R.layout.simple_list_item_1, Tables.toArray())
        list.adapter = adapter

        //nos enteramos de que se ha pulsado un elemento de la lista
        list.setOnItemClickListener{ _, _, position, _ ->
            // notifico al listener
            onTableSelectedListener?.onTableSelected(position)
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

    private fun commonAttach(listener: Any?){
        if (listener is OnTableSelectedListener) {
            onTableSelectedListener = listener
        }
    }

    interface OnTableSelectedListener {
        fun onTableSelected(position: Int)
    }
}