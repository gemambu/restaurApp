package com.gmb.restaurapp.fragment

import android.app.Activity
import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.gmb.restaurapp.R
import com.gmb.restaurapp.activity.TableDetailActivity
import com.gmb.restaurapp.model.Dish
import com.gmb.restaurapp.model.Table


class DishDetailFragment : Fragment() {

    companion object {
        val ARG_DISH_LIST = "ARG_DISH_LIST"
        val EXTRA_TABLE = "EXTRA_TABLE"

        fun newInstance(dish: Dish, table: Table): DishDetailFragment {
            val arguments = Bundle()

            arguments.putSerializable(ARG_DISH_LIST, dish as Dish)
            arguments.putSerializable(EXTRA_TABLE, table as Table)

            val fragment = DishDetailFragment()
            fragment.arguments = arguments
            return fragment
        }
    }
    lateinit var dish: Dish
    lateinit var table: Table
    lateinit var root: View
    lateinit var variant: EditText

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        if (inflater != null) {
            root = inflater?.inflate(R.layout.fragment_dish_detail, container, false)


            table = arguments.getSerializable(EXTRA_TABLE) as Table
            dish = arguments.getSerializable(ARG_DISH_LIST) as Dish

            val imageView = root.findViewById<ImageView>(R.id.dish_detail_photo)
            val description = root.findViewById<TextView>(R.id.dish_description)
            variant = root.findViewById<EditText>(R.id.dish_variant)
            val price = root.findViewById<TextView>(R.id.dish_price)
            val allergen = root.findViewById<TextView>(R.id.allergen)

            //TO-DO manage the allergen list to display with the correct info
            val allergen2 = root.findViewById<TextView>(R.id.allergen2)
            val allergen3 = root.findViewById<TextView>(R.id.allergen3)
            val allergen4 = root.findViewById<TextView>(R.id.allergen4)

            root.findViewById<Button>(R.id.add_dish_btn).setOnClickListener { addDish() }
            root.findViewById<Button>(R.id.cancel_dish_btn).setOnClickListener { cancelAdd() }


            imageView.setImageResource(R.drawable.dim_sum)
            allergen.text = "gluten"
            description.text = dish?.description ?: ""
            variant.setText(dish?.variant ?: "")
            price.text = dish?.price.toString() ?: "0.0"
        }

        return root

    }

    private fun cancelAdd() {

        // TO-DO: close the fragment and come back to the menu/table detail

    }

    private fun addDish() {

        dish.updateVariant(variant.text.toString() ?: "")
        table.addDish(dish)

        Toast.makeText(root.context, "Añadiendo plato en la mesa: ${table.number}", Toast.LENGTH_LONG)
                .show()


    }
}