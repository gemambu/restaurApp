package com.gmb.restaurapp.activity

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.*
import com.gmb.restaurapp.R
import com.gmb.restaurapp.model.Dish
import com.gmb.restaurapp.model.Table


class DishDetailActivity : AppCompatActivity() {

    companion object {
        val EXTRA_DISH_DETAIL = "EXTRA_DISH_DETAIL"
        val EXTRA_TABLE_DETAIL = "EXTRA_TABLE_DETAIL"

        fun intent(context: Context, dish: Dish, table: Table): Intent {
            val intent = Intent(context, DishDetailActivity::class.java)
            intent.putExtra(EXTRA_DISH_DETAIL, dish)
            intent.putExtra(EXTRA_TABLE_DETAIL, table)
            return intent
        }
    }

    lateinit var table: Table

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.fragment_dish_detail)

        table = intent.getSerializableExtra(EXTRA_TABLE_DETAIL) as Table
        val imageView = findViewById<ImageView>(R.id.dish_detail_photo)
        val description = findViewById<TextView>(R.id.dish_description)
        val variant = findViewById<EditText>(R.id.dish_variant)
        val price = findViewById<TextView>(R.id.dish_price)
        val allergen = findViewById<TextView>(R.id.allergen)
        val allergen2 = findViewById<TextView>(R.id.allergen2)
        val allergen3 = findViewById<TextView>(R.id.allergen3)
        val allergen4 = findViewById<TextView>(R.id.allergen4)
        findViewById<Button>(R.id.add_dish_btn).setOnClickListener { addDish() }
        findViewById<Button>(R.id.cancel_dish_btn).setOnClickListener { cancelAdd() }

        val dish = intent.getSerializableExtra(EXTRA_DISH_DETAIL) as? Dish

        imageView.setImageResource(R.drawable.dim_sum)
        allergen.text = "gluten"
        description.text = dish?.description ?: ""
        variant.setText(dish?.variant ?: "")
        price.text = dish?.price.toString() ?: "0.0"

    }

    private fun cancelAdd() {
        setResult(Activity.RESULT_CANCELED)
        finish()
    }

    private fun addDish() {
        setResult(Activity.RESULT_OK)
        finish()
        Toast.makeText(this, "Añadiendo plato en la mesa: ${table.number}", Toast.LENGTH_LONG)
                .show()

    }
}