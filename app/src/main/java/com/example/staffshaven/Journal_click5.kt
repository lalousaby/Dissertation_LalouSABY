package com.example.staffshaven

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.activityViewModels

class Journal_click5 : Fragment() {
    private val viewModel: MyViewModel by activityViewModels()

    private lateinit var RArrowClick5 : ImageButton
    private lateinit var LArrowClick5 : ImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_journal_click5, container, false)

        RArrowClick5 = view.findViewById(R.id.RArrowClick5)
        RArrowClick5.setOnClickListener {
            val journalClick6Fragment = Journal_click6()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame_layout, journalClick6Fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        LArrowClick5 = view.findViewById(R.id.LArrowClick5)
        LArrowClick5.setOnClickListener {
            val journalClick4Fragment = Journal_click4()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame_layout, journalClick4Fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        return view

    }
}