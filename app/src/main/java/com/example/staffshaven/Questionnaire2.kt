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

class Questionnaire2 : Fragment() {
    private val viewModel: MyViewModel by activityViewModels()

    private lateinit var RArrow2 : ImageButton
    private lateinit var LArrow2 : ImageButton

    private lateinit var checkbox3_blue: CheckBox
    private lateinit var checkbox3_pink: CheckBox
    private lateinit var checkbox3_green: CheckBox
    private lateinit var checkbox3_orange: CheckBox
    private lateinit var checkbox3_grey: CheckBox

    private var selectedCount = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_questionnaire2, container, false)

        RArrow2 = view.findViewById(R.id.RArrow3)
        RArrow2.setOnClickListener {
            val questionnaire3Fragment = Questionnaire3()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.questionnaire2Frag, questionnaire3Fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        LArrow2 = view.findViewById(R.id.LArrow3)
        LArrow2.setOnClickListener {
            val questionnaireFragment = Questionnaire()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.questionnaire2Frag, questionnaireFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //to keep the checkboxes checked even when changing fragments
        checkbox3_blue = view.findViewById(R.id.checkbox3Blue)
        checkbox3_pink = view.findViewById(R.id.checkbox3Pink)
        checkbox3_green = view.findViewById(R.id.checkbox3Green)
        checkbox3_orange = view.findViewById(R.id.checkbox3Orange)
        checkbox3_grey = view.findViewById(R.id.checkbox3Grey)

        if (checkbox3_blue.isChecked) selectedCount++
        if (checkbox3_pink.isChecked) selectedCount++
        if (checkbox3_green.isChecked) selectedCount++
        if (checkbox3_orange.isChecked) selectedCount++
        if (checkbox3_grey.isChecked) selectedCount++

        if (selectedCount >= 1){
            if (!checkbox3_blue.isChecked) checkbox3_blue.isEnabled = false
            if (!checkbox3_pink.isChecked) checkbox3_pink.isEnabled = false
            if (!checkbox3_green.isChecked) checkbox3_green.isEnabled = false
            if (!checkbox3_orange.isChecked) checkbox3_orange.isEnabled = false
            if (!checkbox3_grey.isChecked) checkbox3_grey.isEnabled = false
        }

        checkbox3_blue.setOnCheckedChangeListener{ _, isChecked ->
            viewModel.setCheckBoxState("Question2_checkbox0", isChecked)
            if (isChecked){
                selectedCount++
                if (selectedCount > 1){
                    checkbox3_blue.isChecked = false
                    selectedCount--
                    Toast.makeText(requireContext(), "You can only select 1 option", Toast.LENGTH_SHORT).show()
                } else {
                    checkbox3_pink.isEnabled = false
                    checkbox3_green.isEnabled = false
                    checkbox3_orange.isEnabled = false
                    checkbox3_grey.isEnabled = false
                }
            } else {
                selectedCount--
                checkbox3_pink.isEnabled = true
                checkbox3_green.isEnabled = true
                checkbox3_orange.isEnabled = true
                checkbox3_grey.isEnabled = true
            }
        }
        checkbox3_pink.setOnCheckedChangeListener{ _, isChecked ->
            viewModel.setCheckBoxState("Question2_checkbox1", isChecked)
            if (isChecked){
                selectedCount++
                if (selectedCount > 1){
                    checkbox3_pink.isChecked = false
                    selectedCount--
                    Toast.makeText(requireContext(), "You can only select 1 option", Toast.LENGTH_SHORT).show()
                } else {
                    checkbox3_blue.isEnabled = false
                    checkbox3_green.isEnabled = false
                    checkbox3_orange.isEnabled = false
                    checkbox3_grey.isEnabled = false
                }
            } else {
                selectedCount--
                checkbox3_blue.isEnabled = true
                checkbox3_green.isEnabled = true
                checkbox3_orange.isEnabled = true
                checkbox3_grey.isEnabled = true
            }
        }
        checkbox3_green.setOnCheckedChangeListener{ _, isChecked ->
            viewModel.setCheckBoxState("Question2_checkbox2", isChecked)
            if (isChecked) {
                selectedCount++
                if (selectedCount > 1) {
                    checkbox3_green.isChecked = false
                    selectedCount--
                    Toast.makeText(
                        requireContext(),
                        "You can only select 1 option",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    checkbox3_blue.isEnabled = false
                    checkbox3_pink.isEnabled = false
                    checkbox3_orange.isEnabled = false
                    checkbox3_grey.isEnabled = false
                }
            } else {
                selectedCount--
                checkbox3_blue.isEnabled = true
                checkbox3_pink.isEnabled = true
                checkbox3_orange.isEnabled = true
                checkbox3_grey.isEnabled = true
            }
        }
        checkbox3_orange.setOnCheckedChangeListener{ _, isChecked ->
            viewModel.setCheckBoxState("Question2_checkbox3", isChecked)
            if (isChecked) {
                selectedCount++
                if (selectedCount > 1) {
                    checkbox3_orange.isChecked = false
                    selectedCount--
                    Toast.makeText(
                        requireContext(),
                        "You can only select 1 option",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    checkbox3_blue.isEnabled = false
                    checkbox3_pink.isEnabled = false
                    checkbox3_green.isEnabled = false
                    checkbox3_grey.isEnabled = false
                }
            } else {
                selectedCount--
                checkbox3_blue.isEnabled = true
                checkbox3_pink.isEnabled = true
                checkbox3_green.isEnabled = true
                checkbox3_grey.isEnabled = true
            }
        }
        checkbox3_grey.setOnCheckedChangeListener{ _, isChecked ->
            viewModel.setCheckBoxState("Question2_checkbox4", isChecked)
            if (isChecked) {
                selectedCount++
                if (selectedCount > 1) {
                    checkbox3_grey.isChecked = false
                    selectedCount--
                    Toast.makeText(
                        requireContext(),
                        "You can only select 1 option",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    checkbox3_blue.isEnabled = false
                    checkbox3_pink.isEnabled = false
                    checkbox3_green.isEnabled = false
                    checkbox3_orange.isEnabled = false
                }
            } else {
                selectedCount--
                checkbox3_blue.isEnabled = true
                checkbox3_pink.isEnabled = true
                checkbox3_green.isEnabled = true
                checkbox3_orange.isEnabled = true
            }
        }

        checkbox3_blue.isChecked = viewModel.getCheckBoxState("Question2_checkbox0")
        checkbox3_pink.isChecked = viewModel.getCheckBoxState("Question2_checkbox1")
        checkbox3_green.isChecked = viewModel.getCheckBoxState("Question2_checkbox2")
        checkbox3_orange.isChecked = viewModel.getCheckBoxState("Question2_checkbox3")
        checkbox3_grey.isChecked = viewModel.getCheckBoxState("Question2_checkbox4")
    }
}