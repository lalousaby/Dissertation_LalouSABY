package com.example.staffshaven

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.activityViewModels
import com.example.staffshaven.Journal_click3


class Journal_click4 : Fragment() {
    private val viewModel: MyViewModel by activityViewModels()

    private lateinit var RArrowClick4 : ImageButton
    private lateinit var LArrowClick4 : ImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_journal_click4, container, false)

        RArrowClick4 = view.findViewById(R.id.RArrowClick4)
        RArrowClick4.setOnClickListener {
            val journalClick5Fragment = Journal_click5()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame_layout, journalClick5Fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        LArrowClick4 = view.findViewById(R.id.LArrowClick4)
        LArrowClick4.setOnClickListener {
            val journalClick3Fragment = Journal_click3()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame_layout, journalClick3Fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        return view
    }
}