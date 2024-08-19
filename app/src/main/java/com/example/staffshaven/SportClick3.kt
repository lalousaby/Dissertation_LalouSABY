package com.example.staffshaven

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.activityViewModels

class SportClick3 : Fragment() {
    private val viewModel: MyViewModel by activityViewModels()

    private lateinit var RArrowClick3 : ImageButton
    private lateinit var LArrowClick3 : ImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sport_click3, container, false)

        RArrowClick3 = view.findViewById(R.id.RArrowClick3)
        RArrowClick3.setOnClickListener {
            val sportClick4Fragment = SportClick4()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame_layout, sportClick4Fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        LArrowClick3 = view.findViewById(R.id.LArrowClick3)
        LArrowClick3.setOnClickListener {
            val sportClick2Fragment = SportClick2()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame_layout, sportClick2Fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        return view
    }
}