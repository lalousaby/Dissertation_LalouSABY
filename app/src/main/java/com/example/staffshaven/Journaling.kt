package com.example.staffshaven

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.RadioGroup
import android.widget.RatingBar
import androidx.core.content.ContextCompat
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels


class Journaling : Fragment() {

    private val viewModel: MyViewModel by activityViewModels()

    private lateinit var selectedButtonId: Button
    private lateinit var buttonStudyYes: Button
    private lateinit var buttonStudyNo: Button
    private lateinit var selectedButtonIdEat: Button
    private lateinit var buttonEatYes: Button
    private lateinit var buttonEatNo: Button
    private lateinit var submitJournaling: Button
    private lateinit var frameLayoutAnimationYes: FrameLayout
    private lateinit var frameLayoutAnimationNo: FrameLayout


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
            viewModel._selectedOptionsJournaling.clear()

            val dayTextJournal = viewModel.dayTextJournal.value
            if (!dayTextJournal.isNullOrEmpty()) {
                viewModel._selectedOptionsJournaling.add("Text: $dayTextJournal")
            }

            val positiveJournal = viewModel.positiveJournal.value
            if (!positiveJournal.isNullOrEmpty()) {
                viewModel._selectedOptionsJournaling.add("Positive: $positiveJournal")
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

            when (viewModel.selectedRadioButtonIdSwipe.value) {
                R.id.radioBtn1 -> viewModel._selectedOptionsJournaling.add("1 meal")
                R.id.radioBtn2 -> viewModel._selectedOptionsJournaling.add("2 meals")
                R.id.radioBtn3 -> viewModel._selectedOptionsJournaling.add("3 meals!")
                R.id.radioBtnSnacks -> viewModel._selectedOptionsJournaling.add("Only snacks")
            }

            val selectedOptionsJournaling = viewModel.selectedOptions
            val summary = "Selected Options Journaling: ${selectedOptionsJournaling.joinToString(", ")}"
            Toast.makeText(requireContext(), summary, Toast.LENGTH_SHORT).show()

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

        val editDayTxt = view.findViewById<EditText>(R.id.editDayTxt)
        editDayTxt.addTextChangedListener (object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                viewModel.onTextChanged(s.toString())
            }
        })

        viewModel.dayTextJournal.observe(viewLifecycleOwner) { text ->if (editDayTxt.text.toString() != text) { // Check if text needs to be updated
            editDayTxt.setText(text)
        }
        }


        val positive1 = view.findViewById<EditText>(R.id.editDayPositiveTxt)

        positive1.addTextChangedListener (object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                viewModel.onPositiveTextChanged(s.toString())
            }
        })

        viewModel.positiveJournal.observe(viewLifecycleOwner) { text1 ->if (positive1.text.toString() != text1) { // Check if text needs to be updated
            positive1.setText(text1)
        }
        }

        val ratingBar = view.findViewById<RatingBar>(R.id.sleepRatingBar)
        ratingBar.onRatingBarChangeListener = RatingBar.OnRatingBarChangeListener { _, rating, _ ->
            viewModel.onRatingChangedSwipe(rating)
        }

        viewModel.sleepRating.observe(viewLifecycleOwner) { rating ->
            ratingBar.rating = rating
        }

        val radioGroup = view.findViewById<RadioGroup>(R.id.radioGroupJournal)

        viewModel.selectedRadioButtonIdSwipe.observe(viewLifecycleOwner) { checkedId ->
            if (checkedId != -1) {
                radioGroup.check(checkedId)
            }
        }

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            viewModel.onRadioButtonSelectedSwipe(checkedId)
        }

        frameLayoutAnimationYes = view.findViewById(R.id.frameLayoutAnimationYes)
        frameLayoutAnimationYes.setOnClickListener {
            viewModel.selectedStudyBtnSwipe = R.id.btnStudyYes
            viewModel.onStudyYesSelectedSwipe()
            updateAnimationSelection()
        }
        frameLayoutAnimationNo = view.findViewById(R.id.frameLayoutAnimationNo)
        frameLayoutAnimationNo.setOnClickListener {
            viewModel.selectedStudyBtnSwipe = R.id.btnStudyNo
            viewModel.onStudyNoSelectedSwipe()
            updateAnimationSelection()
        }
    }

    private fun updateAnimationSelection() {
        val selectedStudyBtnIdSwipe = viewModel.selectedStudyBtnIdSwipe

        frameLayoutAnimationYes.isSelected = viewModel.isStudyYesSelectedSwipe
        frameLayoutAnimationNo.isSelected = viewModel.isStudyNoSelectedSwipe
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