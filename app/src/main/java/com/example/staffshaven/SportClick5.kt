package com.example.staffshaven

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.activityViewModels

class SportClick5 : Fragment() {
    private val viewModel: MyViewModel by activityViewModels()

    private lateinit var LArrowClick5 : ImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sport_click5, container, false)

        LArrowClick5 = view.findViewById(R.id.LArrowClick5)
        LArrowClick5.setOnClickListener {
            val sportClick4Fragment = SportClick4()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame_layout, sportClick4Fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        return view
    }
}