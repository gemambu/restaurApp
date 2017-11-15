package com.gmb.restaurapp.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.gmb.restaurapp.R
import com.gmb.restaurapp.fragment.TableDetailFragment
import com.gmb.restaurapp.model.Table
import kotlinx.android.synthetic.main.fragment_table_detail.view.*


class TableDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_table_detail)

        if (fragmentManager.findFragmentById(R.id.table_detail_fragment) == null) {
            val table = this.intent.getSerializableExtra("EXTRA_TABLE") as? Table
            val fragment = TableDetailFragment.newInstance(table)
            fragmentManager.beginTransaction()
                    .add(R.id.table_detail_fragment, fragment)
                    .commit()
        }
    }
}