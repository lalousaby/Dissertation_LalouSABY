package com.example.staffshaven

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.activityViewModels


class Journal_click3 : Fragment() {
    private val viewModel: MyViewModel by activityViewModels()

    private lateinit var RArrowClick3 : ImageButton
    private lateinit var LArrowClick3 : ImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_journal_click3, container, false)

        RArrowClick3 = view.findViewById(R.id.RArrowClick3)
        RArrowClick3.setOnClickListener {
            val journalClick4Fragment = Journal_click4()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame_layout, journalClick4Fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        LArrowClick3 = view.findViewById(R.id.LArrowClick3)
        LArrowClick3.setOnClickListener {
            val journalClick2Fragment = Journal_click2()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame_layout, journalClick2Fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        return view
    }
}