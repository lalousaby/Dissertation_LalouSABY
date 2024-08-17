package com.example.staffshaven

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class JournalSlideAdapter(fragment: Fragment) : FragmentStateAdapter (fragment) {
    override fun getItemCount(): Int = 6 // Replace with the number of cards

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> JournalSlide1()
            1 -> JournalSlide2()
            2 -> JournalSlide3()
            3 -> JournalSlide4()
            4 -> JournalSlide5()
            5 -> JournalSlide6()
            else -> throw IllegalStateException("Invalid card position")
        }
    }
}