package com.example.staffshaven

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.staffshaven.Journal_click3


class Journal_click6 : Fragment() {
    private val viewModel: MyViewModel by activityViewModels()

    private lateinit var LArrowClick6 : ImageButton
    private lateinit var submitJournalingClick : Button
    private lateinit var btnVeggiesYes: Button
    private lateinit var btnVeggiesNo: Button
    private lateinit var selectedVeggiesButton: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_journal_click6, container, false)

        LArrowClick6 = view.findViewById(R.id.LArrowClick6)
        LArrowClick6.setOnClickListener {
            val journalClick5Fragment = Journal_click5()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame_layout, journalClick5Fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        btnVeggiesYes = view.findViewById(R.id.btnVeggiesYes)
        btnVeggiesYes.setOnClickListener {
            viewModel.selectedVeggiesBtn = R.id.btnVeggiesYes
            updateButtonColors()
        }
        btnVeggiesNo = view.findViewById(R.id.btnVeggiesNo)
        btnVeggiesNo.setOnClickListener {
            viewModel.selectedVeggiesBtn = R.id.btnVeggiesNo
            updateButtonColors()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        submitJournalingClick = view.findViewById(R.id.submitJournalingClick)
        submitJournalingClick.setOnClickListener {
            viewModel._selectedOptionsJournaling.clear()

            when (viewModel.selectedStudyBtn) {
                R.id.btnStudyYes -> viewModel._selectedOptionsJournaling.add("Study Yes")
                R.id.btnStudyNo -> viewModel._selectedOptionsJournaling.add("Study No")
            }
            when (viewModel.selectedVeggiesBtn) {
                R.id.btnVeggiesYes -> viewModel._selectedOptionsJournaling.add("Veggies Yes")
                R.id.btnVeggiesNo -> viewModel._selectedOptionsJournaling.add("Veggies No")
            }

            val selectedOptionsJournaling = viewModel.selectedOptionsJournaling
            val summary = "Selected Options Journaling: ${selectedOptionsJournaling.joinToString(", ")}"
            Toast.makeText(requireContext(), summary, Toast.LENGTH_SHORT).show()
        }

    }

    private fun updateButtonColors() {
        val typedArray = requireContext().theme.obtainStyledAttributes(
            intArrayOf(android.R.attr.colorAccent, android.R.attr.colorBackground)
        )
        val accentColor = typedArray.getColor(0, 0)
        val backgroundColor = typedArray.getColor(1,0)
        typedArray.recycle()

        btnVeggiesYes.setBackgroundColor(
            if (viewModel.selectedVeggiesBtn == R.id.btnVeggiesYes) backgroundColor else accentColor
        )
        btnVeggiesNo.setBackgroundColor(
            if (viewModel.selectedVeggiesBtn == R.id.btnVeggiesNo) backgroundColor else accentColor
        )
    }
}