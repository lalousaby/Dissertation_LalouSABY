package com.example.staffshaven

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.content.ContextCompat
import android.widget.TextView


class Journaling : Fragment() {
    private lateinit var selectedButtonId: Button
    private lateinit var buttonStudyYes: Button
    private lateinit var buttonStudyNo: Button
    private lateinit var selectedButtonIdEat: Button
    private lateinit var buttonEatYes: Button
    private lateinit var buttonEatNo: Button
    private lateinit var submitJournaling: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for journaling fragment
        return inflater.inflate(R.layout.fragment_journaling, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonStudyYes = view.findViewById(R.id.btnStudyYes)
        buttonStudyNo = view.findViewById(R.id.btnStudyNo)
        buttonEatYes = view.findViewById(R.id.btnVeggiesYes)
        buttonEatNo = view.findViewById(R.id.btnVeggiesNo)
        submitJournaling = view.findViewById(R.id.submitJournaling)

        submitJournaling.setOnClickListener {

            val journalingFrag = Journaling()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()

            transaction.replace(R.id.frame_layout, journalingFrag)
            transaction.commit()
        }

        buttonStudyYes.setOnClickListener {
            selectedButtonId = buttonStudyYes
            updateButtonColors()
        }

        buttonStudyNo.setOnClickListener {
            selectedButtonId = buttonStudyNo
            updateButtonColors()
        }

        buttonEatYes.setOnClickListener {
            selectedButtonIdEat = buttonEatYes
            updateButtonColorsEat()
        }

        buttonEatNo.setOnClickListener {
            selectedButtonIdEat = buttonEatNo
            updateButtonColorsEat()
        }

    }

    private fun updateButtonColors() {
        buttonStudyYes = requireView().findViewById(R.id.btnStudyYes)
        buttonStudyNo = requireView().findViewById(R.id.btnStudyNo)

        val typedArray = requireContext().theme.obtainStyledAttributes(
            intArrayOf(android.R.attr.colorAccent, android.R.attr.colorBackground)
        )
        val accentColour = typedArray.getColor(0, 0) // 0 is the index of colorAccent
        val backgroundColour = typedArray.getColor(1, 0) // 1 is the index of colorBackground
        typedArray.recycle()


        // Change background colors of buttons based on selected button
        buttonStudyYes.setBackgroundColor(
            if (selectedButtonId == buttonStudyYes) backgroundColour else accentColour
        )
        buttonStudyNo.setBackgroundColor(
            if (selectedButtonId == buttonStudyNo) backgroundColour else accentColour
        )
    }
    private fun updateButtonColorsEat() {
        buttonEatYes = requireView().findViewById(R.id.btnVeggiesYes)
        buttonEatNo = requireView().findViewById(R.id.btnVeggiesNo)

        val typedArray = requireContext().theme.obtainStyledAttributes(
            intArrayOf(android.R.attr.colorAccent, android.R.attr.colorBackground)
        )
        val accentColor = typedArray.getColor(0, 0) // 0 is the index of colorAccent
        val backgroundColor = typedArray.getColor(1, 0) // 1 is the index of colorBackground
        typedArray.recycle()

        // Change background colors of buttons based on selected button
        buttonEatYes.setBackgroundColor(
            if (selectedButtonIdEat == buttonEatYes) backgroundColor else accentColor
        )
        buttonEatNo.setBackgroundColor(
            if (selectedButtonIdEat == buttonEatNo) backgroundColor else accentColor
        )
    }

}