package com.josty.genshin.main

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.josty.genshin.R
import com.josty.genshin.characters.CharactersFragment
import com.josty.genshin.databinding.ActivityMainBinding
import com.josty.genshin.dictionary.ui.DictionaryFragment
import com.josty.genshin.wishes.WishesFragment


const val DARK = "DARK"
const val LIGHT = "LIGHT"


class MainActivity : AppCompatActivity() {
    private val pagerAdapter: FragmentStateAdapter = ViewPagerAdapter(this)
    private var currentFragment: Int = 0
    private lateinit var binding: ActivityMainBinding
    private lateinit var prefs: SharedPreferences
    private lateinit var currentTheme: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

        binding.pager.adapter = pagerAdapter
        binding.pager.setCurrentItem(currentFragment, false)
        binding.pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                currentFragment = position
                Log.d("POS", position.toString())
            }
        })
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