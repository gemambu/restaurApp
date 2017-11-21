package com.gmb.restaurapp.activity

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ViewSwitcher
import com.gmb.restaurapp.R
import com.gmb.restaurapp.common.VIEW_MAIN
import com.gmb.restaurapp.fragment.TableListFragment
import com.gmb.restaurapp.model.Allergen
import com.gmb.restaurapp.model.Dish
import com.gmb.restaurapp.model.Table
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.json.JSONObject
import java.net.URL
import java.util.*

class MainActivity : AppCompatActivity(), TableListFragment.OnTableSelectedListener{

    companion object {
        var menu: List<Dish>? = null
        val TABLE_NUMBER = "TABLE_NUMBER"
    }

    lateinit var viewSwitcher: ViewSwitcher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewSwitcher = findViewById(R.id.view_switcher)
        viewSwitcher.setInAnimation(this, android.R.anim.fade_in)
        viewSwitcher.setInAnimation(this, android.R.anim.fade_out)
        viewSwitcher.displayedChild = VIEW_MAIN.LOADING.index

        if (menu == null) {
            menu = updateMenu(this)
        } else {

            showTableList(this)

        }

    }

    private fun showTableList(context: Context) {

        viewSwitcher.displayedChild = VIEW_MAIN.SHOW_TABLES.index

        val currentFragment = fragmentManager
                .findFragmentById(R.id.fragmentContainer)


        if(currentFragment == null)
            fragmentManager
                    .beginTransaction()
                    .add(R.id.fragmentContainer, TableListFragment())
                    .commit()
    }

    private fun updateMenu(context: Context): List<Dish>? {

        async(UI){

            val newMenu: Deferred<List<Dish>?> = bg {
                downloadMenu()
            }

            val downloadedMenu = newMenu.await()
            if (downloadedMenu != null){
                // Tó ha ido bien, se lo asigno al atributo forecast
                menu = downloadedMenu

                showTableList(context)

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
            Thread.sleep(2000)

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

                menuList.add(Dish(id, name, description, price, photo, allergenList, null))
            }

            return menuList
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

        return null

    }

    override fun onTableSelected(position: Int) {
        var intent = Intent(this, TableDetailActivity::class.java)
        intent.putExtra(TABLE_NUMBER, position)
        startActivity(intent)
    }
}
