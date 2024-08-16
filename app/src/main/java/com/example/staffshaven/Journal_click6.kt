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

        updateButtonColors()

        submitJournalingClick = view.findViewById(R.id.submitJournalingClick)
        submitJournalingClick.setOnClickListener {
            viewModel._selectedOptionsJournaling.clear()

            val dayTextJournalClick = viewModel.dayTextJournalClick.value
            if (!dayTextJournalClick.isNullOrEmpty()) {
                viewModel._selectedOptionsJournaling.add("Text: $dayTextJournalClick")
            }
            val positive1JournalClick = viewModel.positive1JournalClick.value
            if (!positive1JournalClick.isNullOrEmpty()) {
                viewModel._selectedOptionsJournaling.add("Positive1: $positive1JournalClick")
            }
            val positive2JournalClick = viewModel.positive2JournalClick.value
            if (!positive2JournalClick.isNullOrEmpty()) {
                viewModel._selectedOptionsJournaling.add("Positive2: $positive2JournalClick")
            }
            val positive3JournalClick = viewModel.positive3JournalClick.value
            if (!positive3JournalClick.isNullOrEmpty()) {
                viewModel._selectedOptionsJournaling.add("Positive3: $positive3JournalClick")
            }

            when (viewModel.selectedStudyBtn) {
                R.id.btnStudyYes -> viewModel._selectedOptionsJournaling.add("Study Yes")
                R.id.btnStudyNo -> viewModel._selectedOptionsJournaling.add("Study No")
            }
            when (viewModel.selectedRadioButtonId.value) {
            R.id.radioBtn1_click -> viewModel._selectedOptionsJournaling.add("1 meal")
            R.id.radioBtn2_click -> viewModel._selectedOptionsJournaling.add("2 meals")
            R.id.radioBtn3_click -> viewModel._selectedOptionsJournaling.add("3 meals!")
            R.id.radioBtnSnacks_click -> viewModel._selectedOptionsJournaling.add("Only snacks")
            }
            when (viewModel.sleepRatingClick.value) {
                0.5f -> viewModel._selectedOptionsJournaling.add("0.5 stars")
                1.0f -> viewModel._selectedOptionsJournaling.add("1 star")
                1.5f -> viewModel._selectedOptionsJournaling.add("1.5 stars")
                2.0f -> viewModel._selectedOptionsJournaling.add("2 stars")
                2.5f -> viewModel._selectedOptionsJournaling.add("2.5 stars")
                3.0f -> viewModel._selectedOptionsJournaling.add("3 stars")
                3.5f -> viewModel._selectedOptionsJournaling.add("3.5 stars")
                4.0f -> viewModel._selectedOptionsJournaling.add("4 stars")
                4.5f -> viewModel._selectedOptionsJournaling.add("4.5 stars")
                5.0f -> viewModel._selectedOptionsJournaling.add("5 stars")
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

        val selectedButtonId = viewModel.selectedVeggiesBtn
        btnVeggiesYes.isSelected = selectedButtonId == R.id.btnVeggiesYes
        btnVeggiesNo.isSelected = selectedButtonId == R.id.btnVeggiesNo

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