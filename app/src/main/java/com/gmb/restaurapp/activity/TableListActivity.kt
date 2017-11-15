package com.gmb.restaurapp.activity

import android.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import com.gmb.restaurapp.R
import com.gmb.restaurapp.fragment.TableListFragment
import com.gmb.restaurapp.model.Table
import kotlinx.android.synthetic.main.activity_main.view.*


class TableListActivity : AppCompatActivity(), TableListFragment.OnTableSelectedListener {


    override fun onTableSelected(table: Table?, position: Int) {
       // val tableFragment = fragmentManager.findFragmentById(R.id.fragmentContainer) as? TableListFragment

        AlertDialog.Builder(this)
                .setTitle("Nueva pantalla")
                .setMessage("Aqui saldria el detalle de mesa: ${table?.number}")
                .setPositiveButton("OK",  { dialog, _ ->
                    dialog.dismiss()
                })
                .show()

    }
}