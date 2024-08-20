package com.example.staffshaven

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2

class FoodSlideMain : Fragment() {
    private val viewModel: MyViewModel by activityViewModels()

    private lateinit var findFood: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_food_slide_main, container, false)

        val viewPager = view.findViewById<ViewPager2>(R.id.viewPagerFood)
        viewPager.adapter = FoodSlideAdapter(this)

        findFood = view.findViewById(R.id.findFood)

        // Open the findFood fragment
        findFood.setOnClickListener {
            goToFindFood()
        }

        return view
    }

    private fun goToFindFood(){
        val findFoodFragment = findFood()
        val transaction = requireActivity().supportFragmentManager.beginTransaction()

        transaction.replace(R.id.frame_layout, findFoodFragment)
        transaction.commit()
    }
}