package com.example.staffshaven

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class DashboardSlideAdapter(fragment: Fragment) : FragmentStateAdapter(fragment)  {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> DashboardSlide1()
            1 -> DashboardSlide2()
            2 -> DashboardSlide3()
            else -> throw IllegalStateException("Invalid card position")
        }
    }
}