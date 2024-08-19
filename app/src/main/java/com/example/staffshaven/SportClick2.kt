package com.example.staffshaven

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageButton
import androidx.fragment.app.activityViewModels

class SportClick2 : Fragment() {
    private val viewModel: MyViewModel by activityViewModels()

    private lateinit var RArrowClick2 : ImageButton
    private lateinit var LArrowClick2 : ImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sport_click2, container, false)

        RArrowClick2 = view.findViewById(R.id.RArrowClick2)
        RArrowClick2.setOnClickListener {
            val sportClick3Fragment = SportClick3()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame_layout, sportClick3Fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        LArrowClick2 = view.findViewById(R.id.LArrowClick2)
        LArrowClick2.setOnClickListener {
            val sportClick1Fragment = SportClick1()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame_layout, sportClick1Fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    return view
    }
}