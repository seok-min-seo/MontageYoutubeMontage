package com.sukddaksoftware.montageyoutube2t.Adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

class MainViewPagerAdapter(activity: AppCompatActivity, private val fragments: List<Fragment>)
    : FragmentStateAdapter(activity)
{
    override fun getItemCount(): Int = fragments.size
    override fun createFragment(position: Int): Fragment = fragments[position]


}
