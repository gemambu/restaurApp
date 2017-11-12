package com.gmb.restaurapp.activity

import android.app.AlertDialog
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ViewSwitcher
import com.gmb.restaurapp.R
import com.gmb.restaurapp.common.VIEW_BUTTON
import com.gmb.restaurapp.model.Allergen
import com.gmb.restaurapp.model.Dish
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.json.JSONObject
import java.net.URL
import java.util.*

class MainActivity : AppCompatActivity() {

    companion object {
        var menu: List<Dish>? = null
    }

    lateinit var viewSwitcher: ViewSwitcher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewSwitcher = findViewById(R.id.view_switcher)
        viewSwitcher.setInAnimation(this, android.R.anim.fade_in)
        viewSwitcher.setInAnimation(this, android.R.anim.fade_out)
        viewSwitcher.displayedChild = VIEW_BUTTON.LOADING.index

        if (menu == null) {
            menu = updateMenu(this)
        } else {
            viewSwitcher.displayedChild = VIEW_BUTTON.SHOW_TABLES.index
        }

    }

    private fun updateMenu(context: Context): List<Dish>? {
        var menu: List<Dish>? = null

        async(UI){

            val newMenu: Deferred<List<Dish>?> = bg {
                downloadMenu()
            }

            val downloadedMenu = newMenu.await()
            if (downloadedMenu != null){
                // Tó ha ido bien, se lo asigno al atributo forecast
                menu = downloadedMenu
                viewSwitcher.displayedChild = VIEW_BUTTON.SHOW_TABLES.index
            } else {
                // ha pasado algo
                AlertDialog.Builder(context)
                        .setTitle("Error")
                        .setMessage("No se ha podido descargar la información")
                        .setPositiveButton(getString(R.string.retry_download),  { dialog, _ ->
                            dialog.dismiss()
                            updateMenu(context)
                        })
                        .setNegativeButton(getString(R.string.cancel_download), { _, _ ->  finish()})
                        .show()
            }
        }

        return menu
    }

    private fun downloadMenu(): List<Dish>? {
        try {
            Thread.sleep(5000)
            
            val url = URL("http://www.mocky.io/v2/5a087acf3200000203137fe5");
            val jsonString = Scanner(url.openStream(), "UTF-8").useDelimiter("\\A").next()

            val jsonRoot = JSONObject(jsonString.toString())
            val dishesList = jsonRoot.getJSONArray("dishes")

            var menuList = mutableListOf<Dish>()

            for(dishObj in 0 until dishesList.length()) {

                val dish = dishesList.getJSONObject(dishObj)

                val id = dish.getInt("id")
                val name = dish.getString("name")
                val description = dish.getString("description")
                val price = dish.getDouble("price").toFloat()
                val photo = dish.getInt("photo")
                val allergensJson = dish.getJSONArray("allergens")
                val allergenList = mutableListOf<Allergen>()

                if (allergensJson != null && allergensJson.length() > 0){
                    for(allergenObj in 0 until allergensJson.length()){
                        val allergen = allergensJson.getJSONObject(allergenObj)
                        val id = allergen.getInt("id")
                        val name = allergen.getString("name")
                        val icon = allergen.getInt("icon")
                        allergenList.add(Allergen(id, name, icon))
                    }
                }

                menuList.add(Dish(id, name, description, price, photo, allergenList))
            }

            return menuList
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

        return null

    }
}
