package com.gmb.restaurapp.fragment

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.gmb.restaurapp.R
import com.gmb.restaurapp.common.getAllergenInfo
import com.gmb.restaurapp.common.getDishPhoto
import com.gmb.restaurapp.model.Dish
import com.gmb.restaurapp.model.Table
import com.gmb.restaurapp.model.Tables


class DishDetailFragment : Fragment() {

    companion object {
        val ARG_DISH_LIST = "ARG_DISH_LIST"
        val EXTRA_TABLE_POSITION = "EXTRA_TABLE_POSITION"

        fun newInstance(dish: Dish, tableNumber: Int): DishDetailFragment {
            val arguments = Bundle()

            arguments.putSerializable(ARG_DISH_LIST, dish)
            arguments.putInt(EXTRA_TABLE_POSITION, tableNumber)

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
            root = inflater.inflate(R.layout.fragment_dish_detail, container, false)

            table = Tables.get(arguments.getInt(EXTRA_TABLE_POSITION))
            dish = arguments.getSerializable(ARG_DISH_LIST) as Dish

            val imageView = root.findViewById<ImageView>(R.id.dish_detail_photo)
            val description = root.findViewById<TextView>(R.id.dish_description)
            variant = root.findViewById<EditText>(R.id.dish_variant)
            val price = root.findViewById<TextView>(R.id.dish_price)

            val allergens = mutableListOf<TextView>(
                    root.findViewById<TextView>(R.id.allergen),
                    root.findViewById<TextView>(R.id.allergen2),
                    root.findViewById<TextView>(R.id.allergen3),
                    root.findViewById<TextView>(R.id.allergen4)
            )

            val ivAllergens = mutableListOf<ImageView>(
                    root.findViewById<ImageView>(R.id.iv_allergen),
                    root.findViewById<ImageView>(R.id.iv_allergen2),
                    root.findViewById<ImageView>(R.id.iv_allergen3),
                    root.findViewById<ImageView>(R.id.iv_allergen4)
            )

            getAllergenInfo(dish, allergens, ivAllergens)


            imageView.setImageResource(getDishPhoto(dish.photo))
            price.text = getString(R.string.dish_price, dish.price)
            description.text = dish.description
            variant.setText(dish.variant)

            root.findViewById<Button>(R.id.add_dish_btn).setOnClickListener { addDish() }
            root.findViewById<Button>(R.id.cancel_dish_btn).setOnClickListener { cancelAdd() }

        }

        return root

    }

    private fun cancelAdd() {
        fragmentManager.popBackStack()

    }

    private fun addDish() {

        var dishVariant = dish.copy()
        dishVariant.updateVariant(variant.text.toString())
        table.addDish(dishVariant)

        Toast.makeText(root.context, "Añadiendo plato en la mesa: ${table.number}", Toast.LENGTH_LONG)
                .show()


    }
}