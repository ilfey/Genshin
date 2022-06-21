package com.josty.genshin.main

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.josty.genshin.R
import com.josty.genshin.characters.ui.CharactersFragment
import com.josty.genshin.databinding.ActivityMainBinding
import com.josty.genshin.dictionary.ui.DictionaryFragment
import com.josty.genshin.wishes.ui.WishesFragment
import com.newrelic.agent.android.NewRelic


const val DARK = "DARK"
const val LIGHT = "LIGHT"


class MainActivity : AppCompatActivity(), PopupMenu.OnMenuItemClickListener {
    private val pagerAdapter: FragmentStateAdapter = ViewPagerAdapter(this)
    private var currentFragment: Int = 0
    private lateinit var binding: ActivityMainBinding
    private lateinit var prefs: SharedPreferences
    private lateinit var currentTheme: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefs = getSharedPreferences("LocalStorage", Context.MODE_PRIVATE);

        if (!prefs.getBoolean("isVisited", false)) {
            // Создаем запись в префсах о том, что апк уже открывалось ранее
            val editor: SharedPreferences.Editor = prefs.edit()
            editor.putBoolean("isVisited", true)
            editor.apply()
        }

        currentTheme = prefs.getString("Theme", DARK)!!
        currentFragment = prefs.getInt("Fragment", 0)

        when (currentTheme) {
            DARK -> setTheme(R.style.AppTheme_Dark, DARK)
            LIGHT -> setTheme(R.style.AppTheme_Light, LIGHT)
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding.pager){
            adapter = pagerAdapter
            setCurrentItem(currentFragment, false)
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    currentFragment = position
                    Log.d("POS", position.toString())
                }
            })
        }

        binding.sort.setOnClickListener {
            val popup = PopupMenu(this, it)
            popup.menuInflater.inflate(R.menu.sorting, popup.menu)
            popup.setOnMenuItemClickListener { this@MainActivity.onMenuItemClick(it) }
            popup.show()
        }
//        TODO fix BottomNavigationView
        /*binding.navigation.setOnNavigationItemReselectedListener { item ->
            val id = when (item.itemId) {
                R.id.nav_characters -> 0
                R.id.nav_dictionary -> 1
                R.id.nav_wishes -> 2
                else -> 0
            }
            currentFragment = id
            binding.pager.setCurrentItem(id, true)
        }*/

        NewRelic.withApplicationToken(
            "AA5f9fdbde573b65e65e7c21b31f6bd29763a6d812-NRMA"
        ).start(applicationContext)
    }

    private fun setTheme(id: Int, theme: String) {
        setTheme(id)
        currentTheme = theme
    }

    override fun onStop() {
        val edit = prefs.edit()
        edit.putString("Theme", currentTheme)
        edit.putInt("LastFragment", currentFragment)
        edit.apply()
        super.onStop()
    }

//    TODO add sorting
    override fun onMenuItemClick(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.from_rare_to_less -> {
                Log.d("[Popup]", "Clicked id: from_rare_to_less")
                Toast.makeText(this, R.string.from_rare_to_less, Toast.LENGTH_SHORT).show()
                return true
            }

            R.id.from_less_rare -> {
                Log.d("[Popup]", "Clicked id: from_less_rare")
                Toast.makeText(this, R.string.from_less_rare, Toast.LENGTH_SHORT).show()
                return true
            }

            R.id.alphabetically -> {
                Log.d("[Popup]", "Clicked id: alphabetically")
                Toast.makeText(this, R.string.alphabetically, Toast.LENGTH_SHORT).show()
                return true
            }

            R.id.from_the_end_of_the_alphabet -> {
                Log.d("[Popup]", "Clicked id: from_the_end_of_the_alphabet")
                Toast.makeText(this, R.string.from_the_end_of_the_alphabet, Toast.LENGTH_SHORT)
                    .show()
                return true
            }

            R.id.by_the_elements -> {
                Log.d("[Popup]", "Clicked id: by_the_elements")
                Toast.makeText(this, R.string.by_the_elements, Toast.LENGTH_SHORT).show()
                return true
            }

            R.id.by_id_in_bd -> {
                Log.d("[Popup]", "Clicked id: by_id_in_bd")
                Toast.makeText(this, R.string.by_id_in_bd, Toast.LENGTH_SHORT).show()
                return true
            }
            else -> return false
        }
    }

    private inner class ViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

        override fun getItemCount(): Int = 3

        override fun createFragment(position: Int): Fragment = when (position) {
            0 -> CharactersFragment()
            1 -> DictionaryFragment()
            2 -> WishesFragment()
            else -> CharactersFragment()
        }
    }
}