package com.gmb.restaurapp.fragment

import android.app.Activity
import android.app.Fragment
import android.app.FragmentManager
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.gmb.restaurapp.R
import com.gmb.restaurapp.common.PREVIOUS_ACTIITY
import com.gmb.restaurapp.common.PREV_ACT
import com.gmb.restaurapp.common.getAllergenInfo
import com.gmb.restaurapp.common.getDishPhoto
import com.gmb.restaurapp.model.Dish
import com.gmb.restaurapp.model.Table
import com.gmb.restaurapp.model.Tables


class DishDetailFragment : Fragment() {

    companion object {
        val ARG_DISH_LIST = "ARG_DISH_LIST"
        val EXTRA_TABLE_POSITION = "EXTRA_TABLE_POSITION"

        var onSaveButtonPressed: OnSaveButtonPressedListener? = null

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

            root.findViewById<Button>(R.id.add_dish_btn).setOnClickListener { saveDish() }
            root.findViewById<Button>(R.id.cancel_dish_btn).setOnClickListener { cancelAdd() }

            val supportActionBar = (activity as? AppCompatActivity)?.supportActionBar
            supportActionBar?.title = dish.name
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

        }

        return root

    }

    private fun cancelAdd() {
        //fragmentManager.popBackStack()
        fragmentManager.popBackStack()
    }

    private fun saveDish() {
        var message = ""

        when(PREVIOUS_ACTIITY){
            PREV_ACT.MENU -> {
                var dishVariant = dish.copy()
                dishVariant.updateVariant(variant.text.toString())
                table.addDish(dishVariant)

                message ="Añadiendo plato en la mesa: ${table.number}"

                onSaveButtonPressed?.onSavePressed(root.rootView)

            }
            PREV_ACT.DETAIL -> {
                dish.updateVariant(variant.text.toString())
                message ="Actualizando plato en la mesa: ${table.number}"
            }
        }

    /*    Snackbar.make(root.findViewById(R.id.dish_detail_layout),
                message,
                Snackbar.LENGTH_LONG)
*/

        Toast.makeText(root.context, message, Toast.LENGTH_LONG).show()

    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        commonOnAttach(context)
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        commonOnAttach(activity)
    }

    fun commonOnAttach(context: Context?) {
        // Aquí nos llaman cuando el fragment "se engancha" a la actividad, y por tanto ya pertence a ella
        // Lo que vamos a hacer es quedarnos con la referencia a esa actividad para cuando tengamos que avisarle de "cosas"
        if (context is OnSaveButtonPressedListener) {
            onSaveButtonPressed = context
        }
    }

    override fun onDetach() {
        super.onDetach()

        // Si la actividad se "desengancha" de este fragment ya no tiene sentido guardar una referencia a ella, ya no le vamos
        // a avisar de nada, lo ponemos a null
        onSaveButtonPressed = null
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.home) {
            fragmentManager.popBackStack()
        }

        return true
    }
}

interface OnSaveButtonPressedListener {
    fun onSavePressed(view: View)
}