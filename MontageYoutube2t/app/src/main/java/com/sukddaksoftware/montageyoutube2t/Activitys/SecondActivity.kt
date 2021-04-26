package com.sukddaksoftware.montageyoutube2t.Activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.sukddaksoftware.montageyoutube2t.Fragments.HomeFragment
import com.sukddaksoftware.montageyoutube2t.Fragments.PlaylistFragment
import com.sukddaksoftware.montageyoutube2t.Fragments.ProfileFragment
import com.sukddaksoftware.montageyoutube2t.Fragments.SearchFragment
import com.sukddaksoftware.montageyoutube2t.Adapter.MainViewPagerAdapter
import com.sukddaksoftware.montageyoutube2t.R
import kotlinx.android.synthetic.main.activity_second.*


class SecondActivity : AppCompatActivity() {

    private val homeFragment by lazy{ HomeFragment() }
    private val playlistFragment by lazy { PlaylistFragment() }
    private val searchFragment by lazy { SearchFragment() }
    private val profileFragment by lazy { ProfileFragment() }

    private val fragments: List<Fragment> =
        listOf(homeFragment , playlistFragment, searchFragment,profileFragment )

    private val pagerAdapter: MainViewPagerAdapter by lazy {
        MainViewPagerAdapter(this, fragments) }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)


        initViewPager()
        initNavigationBar()


    }

    private fun initNavigationBar() {
        main_bawah.run { setOnNavigationItemSelectedListener{
            val page = when(it.itemId) {
                R.id.menu_home -> 0
                R.id.menu_playlist -> 1
                R.id.menu_search -> 2
                R.id.menu_profile -> 3
                else -> 0 }

            if (page != vp_main.currentItem) {
                vp_main.currentItem = page
            }
            true
        }
        selectedItemId = R.id.menu_home
        }
    }
    private fun initViewPager() {
        vp_main.run {
            adapter = pagerAdapter
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) { val navigation = when(position) {
                    0 ->R.id.menu_home
                    1 -> R.id.menu_playlist
                    2 -> R.id.menu_search
                    3 -> R.id.menu_profile
                    else -> R.id.menu_home }
                    if (main_bawah.selectedItemId != navigation) {
                        main_bawah.selectedItemId = navigation }
                }
            })
        }
    }



}

