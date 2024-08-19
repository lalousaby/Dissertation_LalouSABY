package com.example.staffshaven

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SportSlideAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 5 // Replace with the number of cards

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> SportSlide1()
            1 -> SportSlide2()
            2 -> SportSlide3()
            3 -> SportSlide4()
            4 -> SportSlide5()
            else -> throw IllegalStateException("Invalid card position")
        }
    }
}