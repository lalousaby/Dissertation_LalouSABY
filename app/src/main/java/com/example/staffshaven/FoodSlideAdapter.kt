package com.example.staffshaven

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class FoodSlideAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3 // Replace with the number of cards

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FoodSlide1()
            1 -> FoodSlide2()
            2 -> FoodSlide3()
            else -> throw IllegalStateException("Invalid card position")
        }
    }
}