package com.gmb.restaurapp.fragment

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.gmb.restaurapp.R
import com.gmb.restaurapp.common.TABLE
import com.gmb.restaurapp.common.getAllergenInfo
import com.gmb.restaurapp.common.getDishPhoto
import com.gmb.restaurapp.model.Dish
import com.gmb.restaurapp.model.Table
import com.gmb.restaurapp.model.Tables


class DishDetailFragment : Fragment() {

    companion object {
        val ARG_DISH_LIST = "ARG_DISH_LIST"
        val EXTRA_TABLE_POSITION = "EXTRA_TABLE_POSITION"

        var dishDetailListener: DetailDishListener? = null

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
    private lateinit var table: Table
    private lateinit var root: View
    private lateinit var variant: EditText

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        if (inflater != null) {
            root = inflater.inflate(R.layout.fragment_dish_detail, container, false)

            table = Tables[arguments.getInt(EXTRA_TABLE_POSITION)]
            dish = arguments.getSerializable(ARG_DISH_LIST) as Dish

            val imageView = root.findViewById<ImageView>(R.id.dish_detail_photo)
            val description = root.findViewById<TextView>(R.id.dish_description)
            variant = root.findViewById(R.id.dish_variant)
            variant.inputType = InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS
            val price = root.findViewById<TextView>(R.id.dish_price)

            val allergens = mutableListOf<TextView>(
                    root.findViewById(R.id.allergen),
                    root.findViewById(R.id.allergen2),
                    root.findViewById(R.id.allergen3),
                    root.findViewById(R.id.allergen4)
            )

            val ivAllergens = mutableListOf<ImageView>(
                    root.findViewById(R.id.iv_allergen),
                    root.findViewById(R.id.iv_allergen2),
                    root.findViewById(R.id.iv_allergen3),
                    root.findViewById(R.id.iv_allergen4)
            )

            getAllergenInfo(dish, allergens, ivAllergens)


            imageView.setImageResource(getDishPhoto(dish.photo))
            price.text = getString(R.string.dish_price, dish.price)
            description.text = dish.description
            variant.setText(dish.variant)


            var buttonText = getString(R.string.add_dish_btn)

            when (activity.intent.action) {
                TABLE -> buttonText = getString(R.string.update_dish_btn)
            }

            root.findViewById<Button>(R.id.add_dish_btn).text = buttonText


            root.findViewById<Button>(R.id.add_dish_btn).setOnClickListener {
                dishDetailListener?.onSavePressed(root.rootView, dish, variant.text.toString())
            }

            root.findViewById<Button>(R.id.cancel_dish_btn).setOnClickListener {
                dishDetailListener?.onCancelPressed(root.rootView)
            }

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
        if (context is DetailDishListener) {
            dishDetailListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()

        // Si la actividad se "desengancha" de este fragment ya no tiene sentido guardar una referencia a ella, ya no le vamos
        // a avisar de nada, lo ponemos a null
        dishDetailListener = null
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.home) {
            fragmentManager.popBackStack()
        }

        return true
    }
}

interface DetailDishListener {
    fun onSavePressed(view: View, dish: Dish, variant: String)
    fun onCancelPressed(view: View)
}