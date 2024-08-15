package com.example.staffshaven

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.activityViewModels

class Questionnaire : Fragment() {

    private val viewModel: MyViewModel by activityViewModels()

    private lateinit var RArrow : ImageButton
    private lateinit var checkbox2_blue: CheckBox
    private lateinit var checkbox2_pink: CheckBox
    private lateinit var checkbox2_green: CheckBox
    private lateinit var checkbox2_orange: CheckBox
    private lateinit var checkbox2_grey: CheckBox
    private var selectedCount = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_questionnaire, container, false)

        RArrow = view.findViewById(R.id.RArrow2)
        RArrow.setOnClickListener {
            val questionnaire2Fragment = Questionnaire2()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.questionnaireFrag, questionnaire2Fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //to keep the checkboxes checked even when changing fragments
        checkbox2_blue = view.findViewById(R.id.checkboxBlue)
        checkbox2_pink = view.findViewById(R.id.checkboxPink)
        checkbox2_green = view.findViewById(R.id.checkboxGreen)
        checkbox2_orange = view.findViewById(R.id.checkboxOrange)
        checkbox2_grey = view.findViewById(R.id.checkboxGrey)

        if (checkbox2_blue.isChecked) selectedCount++
        if (checkbox2_pink.isChecked) selectedCount++
        if (checkbox2_green.isChecked) selectedCount++
        if (checkbox2_orange.isChecked) selectedCount++
        if (checkbox2_grey.isChecked) selectedCount++

        if (selectedCount >= 1){
            if (!checkbox2_blue.isChecked) checkbox2_blue.isEnabled = false
            if (!checkbox2_pink.isChecked) checkbox2_pink.isEnabled = false
            if (!checkbox2_green.isChecked) checkbox2_green.isEnabled = false
            if (!checkbox2_orange.isChecked) checkbox2_orange.isEnabled = false
            if (!checkbox2_grey.isChecked) checkbox2_grey.isEnabled = false
        }

        checkbox2_blue.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setCheckBoxState("Question1_checkbox0", isChecked)
            if (isChecked) {
                selectedCount++
                if (selectedCount > 1) {
                    checkbox2_blue.isChecked = false
                    selectedCount--
                    Toast.makeText(requireContext(), "You can only select 1 option", Toast.LENGTH_SHORT).show()
                } else {
                    checkbox2_pink.isEnabled = false
                    checkbox2_green.isEnabled = false
                    checkbox2_orange.isEnabled = false
                    checkbox2_grey.isEnabled = false
                }
            } else {
                selectedCount--
                checkbox2_pink.isEnabled = true
                checkbox2_green.isEnabled = true
                checkbox2_orange.isEnabled = true
                checkbox2_grey.isEnabled = true
            }
        }
        checkbox2_pink.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setCheckBoxState("Question1_checkbox1", isChecked)
            if (isChecked){
                selectedCount++
                if (selectedCount > 1){
                    checkbox2_pink.isChecked = false
                    selectedCount--
                    Toast.makeText(requireContext(), "You can only select 1 option", Toast.LENGTH_SHORT).show()
                } else {
                    checkbox2_blue.isEnabled = false
                    checkbox2_green.isEnabled = false
                    checkbox2_orange.isEnabled = false
                    checkbox2_grey.isEnabled = false
                }
            } else {
                selectedCount--
                checkbox2_blue.isEnabled = true
                checkbox2_green.isEnabled = true
                checkbox2_orange.isEnabled = true
                checkbox2_grey.isEnabled = true
            }
        }
        checkbox2_green.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setCheckBoxState("Question1_checkbox2", isChecked)
            if (isChecked){
                selectedCount++
                if (selectedCount > 1){
                    checkbox2_green.isChecked = false
                    selectedCount--
                    Toast.makeText(requireContext(), "You can only select 1 option", Toast.LENGTH_SHORT).show()
                } else {
                    checkbox2_blue.isEnabled = false
                    checkbox2_pink.isEnabled = false
                    checkbox2_orange.isEnabled = false
                    checkbox2_grey.isEnabled = false
                }
            } else {
                selectedCount--
                checkbox2_blue.isEnabled = true
                checkbox2_pink.isEnabled = true
                checkbox2_orange.isEnabled = true
            }
        }
        checkbox2_orange.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setCheckBoxState("Question1_checkbox3", isChecked)
            if (isChecked){
                selectedCount++
                if (selectedCount > 1){
                    checkbox2_orange.isChecked = false
                    selectedCount--
                    Toast.makeText(requireContext(), "You can only select 1 option", Toast.LENGTH_SHORT).show()
                } else {
                    checkbox2_blue.isEnabled = false
                    checkbox2_pink.isEnabled = false
                    checkbox2_green.isEnabled = false
                    checkbox2_grey.isEnabled = false }

            } else {
                selectedCount--
                checkbox2_blue.isEnabled = true
                checkbox2_pink.isEnabled = true
                checkbox2_green.isEnabled = true
                checkbox2_grey.isEnabled = true
            }
        }

        checkbox2_grey.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setCheckBoxState("Question1_checkbox4", isChecked)
            if (isChecked){
                selectedCount++
                if (selectedCount > 1) {
                    checkbox2_grey.isChecked = false
                    selectedCount--
                    Toast.makeText(
                        requireContext(),
                        "You can only select 1 option",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    checkbox2_blue.isEnabled = false
                    checkbox2_pink.isEnabled = false
                    checkbox2_green.isEnabled = false
                    checkbox2_orange.isEnabled = false
                }
            }
            else {
                selectedCount--
                checkbox2_blue.isEnabled = true
                checkbox2_pink.isEnabled = true
                checkbox2_green.isEnabled = true
                checkbox2_orange.isEnabled = true
            }
        }

        checkbox2_blue.isChecked = viewModel.getCheckBoxState("Question1_checkbox0")
        checkbox2_pink.isChecked = viewModel.getCheckBoxState("Question1_checkbox1")
        checkbox2_green.isChecked = viewModel.getCheckBoxState("Question1_checkbox2")
        checkbox2_orange.isChecked = viewModel.getCheckBoxState("Question1_checkbox3")
        checkbox2_grey.isChecked = viewModel.getCheckBoxState("Question1_checkbox4")

    }
}