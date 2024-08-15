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

class Questionnaire3 : Fragment() {
    private val viewModel: MyViewModel by activityViewModels()

    private lateinit var RArrow3 : ImageButton
    private lateinit var LArrow3 : ImageButton

    private lateinit var checkbox4_dark: CheckBox
    private lateinit var checkbox4_bright: CheckBox
    private lateinit var checkbox4_pastels: CheckBox

    private var selectedCount = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_questionnaire3, container, false)

        RArrow3 = view.findViewById(R.id.RArrow4)
        RArrow3.setOnClickListener {
            val questionnaire4Fragment = Questionnaire4()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.questionnaire3Frag, questionnaire4Fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        LArrow3 = view.findViewById(R.id.LArrow4)
        LArrow3.setOnClickListener {
            val questionnaire2Fragment = Questionnaire2()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.questionnaire3Frag, questionnaire2Fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //to keep the checkboxes checked even when changing fragments
        checkbox4_dark = view.findViewById(R.id.checkboxDark)
        checkbox4_bright = view.findViewById(R.id.checkboxBright)
        checkbox4_pastels = view.findViewById(R.id.checkboxPastels)

        if (checkbox4_dark.isChecked) selectedCount++
        if (checkbox4_bright.isChecked) selectedCount++
        if (checkbox4_pastels.isChecked) selectedCount++

        if (selectedCount >= 1){
            if (!checkbox4_dark.isChecked) checkbox4_dark.isEnabled = false
            if (!checkbox4_bright.isChecked) checkbox4_bright.isEnabled = false
            if (!checkbox4_pastels.isChecked) checkbox4_pastels.isEnabled = false
        }

        checkbox4_dark.setOnCheckedChangeListener{ _, isChecked ->
            viewModel.setCheckBoxState("Question3_checkbox0", isChecked)

            if (isChecked){
                selectedCount++
                if (selectedCount > 1) {
                    checkbox4_dark.isChecked = false
                    selectedCount--
                    Toast.makeText(
                        requireContext(),
                        "You can only select 1 option",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else {
                    checkbox4_bright.isEnabled = false
                    checkbox4_pastels.isEnabled = false
                }
            } else {
                selectedCount--
                checkbox4_bright.isEnabled = true
                checkbox4_pastels.isEnabled = true
            }
        }
        checkbox4_bright.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setCheckBoxState("Question3_checkbox1", isChecked)

            if (isChecked){
                selectedCount++
                if (selectedCount > 1) {
                    checkbox4_bright.isChecked = false
                    selectedCount--
                    Toast.makeText(
                        requireContext(), "You can only select 1 option", Toast.LENGTH_SHORT
                    ).show()
                }
                else {
                    checkbox4_dark.isEnabled = false
                    checkbox4_pastels.isEnabled = false
                }
            } else {
                selectedCount--
                checkbox4_dark.isEnabled = true
                checkbox4_pastels.isEnabled = true
            }
        }
        checkbox4_pastels.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setCheckBoxState("Question3_checkbox2", isChecked)

            if (isChecked){
                selectedCount++

                if (selectedCount > 1) {
                    checkbox4_pastels.isChecked = false
                    selectedCount--
                    Toast.makeText(
                        requireContext(), "You can only select 1 option", Toast.LENGTH_SHORT).show()
                }
                else {
                    checkbox4_dark.isEnabled = false
                    checkbox4_bright.isEnabled = false
                }
            } else {
                selectedCount--
                checkbox4_dark.isEnabled = true
                checkbox4_bright.isEnabled = true
            }
        }

        checkbox4_dark.isChecked = viewModel.getCheckBoxState("Question3_checkbox0")
        checkbox4_bright.isChecked = viewModel.getCheckBoxState("Question3_checkbox1")
        checkbox4_pastels.isChecked = viewModel.getCheckBoxState("Question3_checkbox2")

    }
}