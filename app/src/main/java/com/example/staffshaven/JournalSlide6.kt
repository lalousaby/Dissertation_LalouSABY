package com.example.staffshaven

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.activityViewModels


class JournalSlide6 : Fragment() {

    private val viewModel: MyViewModel by activityViewModels()
    private lateinit var submitJournalingSlide : Button
    private lateinit var btnVeggiesYesSlide: Button
    private lateinit var btnVeggiesNoSlide: Button
    private lateinit var selectedVeggiesButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_journal_slide6, container, false)

        btnVeggiesYesSlide = view.findViewById(R.id.btnVeggiesYesSlide)
        btnVeggiesYesSlide.setOnClickListener {
            viewModel.selectedVeggiesBtnSlide = R.id.btnVeggiesYesSlide
            updateButtonColors()
        }
        btnVeggiesNoSlide = view.findViewById(R.id.btnVeggiesNoSlide)
        btnVeggiesNoSlide.setOnClickListener {
            viewModel.selectedVeggiesBtnSlide = R.id.btnVeggiesNoSlide
            updateButtonColors()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateButtonColors()

        submitJournalingSlide = view.findViewById(R.id.submitJournalingSlide)
        submitJournalingSlide.setOnClickListener {
            viewModel._selectedOptionsJournalingSlide.clear()

            val dayTextJournalSlide = viewModel.dayTextJournalSlide.value
            if (!dayTextJournalSlide.isNullOrEmpty()) {
                viewModel._selectedOptionsJournalingSlide.add("Text: $dayTextJournalSlide")
            }
            val positive1JournalSlide = viewModel.positive1JournalSlide.value
            if (!positive1JournalSlide.isNullOrEmpty()) {
                viewModel._selectedOptionsJournalingSlide.add("Positive1: $positive1JournalSlide")
            }
            val positive2JournalSlide = viewModel.positive2JournalSlide.value
            if (!positive2JournalSlide.isNullOrEmpty()) {
                viewModel._selectedOptionsJournalingSlide.add("Positive2: $positive2JournalSlide")
            }
            val positive3JournalSlide = viewModel.positive3JournalSlide.value
            if (!positive3JournalSlide.isNullOrEmpty()) {
                viewModel._selectedOptionsJournalingSlide.add("Positive3: $positive3JournalSlide")
            }

            when (viewModel.selectedStudyBtnSlide) {
                R.id.btnStudyYesSlide -> viewModel._selectedOptionsJournalingSlide.add("Study Yes")
                R.id.btnStudyNoSlide -> viewModel._selectedOptionsJournalingSlide.add("Study No")
            }
            when (viewModel.selectedRadioButtonIdSlide.value) {
                R.id.radioBtn1_slide -> viewModel._selectedOptionsJournalingSlide.add("1 meal")
                R.id.radioBtn2_slide -> viewModel._selectedOptionsJournalingSlide.add("2 meals")
                R.id.radioBtn3_slide -> viewModel._selectedOptionsJournalingSlide.add("3 meals!")
                R.id.radioBtnSnacks_slide -> viewModel._selectedOptionsJournalingSlide.add("Only snacks")
            }
            when (viewModel.sleepRatingSlide.value) {
                0.5f -> viewModel._selectedOptionsJournalingSlide.add("0.5 stars")
                1.0f -> viewModel._selectedOptionsJournalingSlide.add("1 star")
                1.5f -> viewModel._selectedOptionsJournalingSlide.add("1.5 stars")
                2.0f -> viewModel._selectedOptionsJournalingSlide.add("2 stars")
                2.5f -> viewModel._selectedOptionsJournalingSlide.add("2.5 stars")
                3.0f -> viewModel._selectedOptionsJournalingSlide.add("3 stars")
                3.5f -> viewModel._selectedOptionsJournalingSlide.add("3.5 stars")
                4.0f -> viewModel._selectedOptionsJournalingSlide.add("4 stars")
                4.5f -> viewModel._selectedOptionsJournalingSlide.add("4.5 stars")
                5.0f -> viewModel._selectedOptionsJournalingSlide.add("5 stars")
            }
            when (viewModel.selectedVeggiesBtnSlide) {
                R.id.btnVeggiesYesSlide -> viewModel._selectedOptionsJournalingSlide.add("Veggies Yes")
                R.id.btnVeggiesNoSlide -> viewModel._selectedOptionsJournalingSlide.add("Veggies No")
            }


            val selectedOptionsJournalingSlide = viewModel.selectedOptionsJournalingSlide
            val summary = "Selected Options Journaling: ${selectedOptionsJournalingSlide.joinToString(", ")}"
            Toast.makeText(requireContext(), summary, Toast.LENGTH_SHORT).show()
        }

    }

    private fun updateButtonColors() {

        val selectedButtonId = viewModel.selectedVeggiesBtnSlide
        btnVeggiesYesSlide.isSelected = selectedButtonId == R.id.btnVeggiesYesSlide
        btnVeggiesNoSlide.isSelected = selectedButtonId == R.id.btnVeggiesNoSlide

        val typedArray = requireContext().theme.obtainStyledAttributes(
            intArrayOf(android.R.attr.colorAccent, android.R.attr.colorBackground)
        )
        val accentColor = typedArray.getColor(0, 0)
        val backgroundColor = typedArray.getColor(1,0)
        typedArray.recycle()

        btnVeggiesYesSlide.setBackgroundColor(
            if (viewModel.selectedVeggiesBtnSlide == R.id.btnVeggiesYesSlide) backgroundColor else accentColor
        )
        btnVeggiesNoSlide.setBackgroundColor(
            if (viewModel.selectedVeggiesBtnSlide == R.id.btnVeggiesNoSlide) backgroundColor else accentColor
        )
    }
}