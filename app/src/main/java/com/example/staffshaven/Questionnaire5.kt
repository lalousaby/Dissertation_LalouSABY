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

class Questionnaire5 : Fragment() {
    private val viewModel: MyViewModel by activityViewModels()

    private lateinit var RArrow5 : ImageButton
    private lateinit var LArrow5 : ImageButton

    private lateinit var checkboxImages: CheckBox
    private lateinit var checkboxText: CheckBox

    private var selectedCount = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_questionnaire5, container, false)

        RArrow5 = view.findViewById(R.id.RArrow6)
        RArrow5.setOnClickListener {
            val questionnaire6Fragment = Questionnaire6()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.questionnaire5Frag, questionnaire6Fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        LArrow5 = view.findViewById(R.id.LArrow6)
        LArrow5.setOnClickListener {
            val questionnaire4Fragment = Questionnaire4()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.questionnaire5Frag, questionnaire4Fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //to keep the checkboxes checked even when changing fragments
        checkboxImages = view.findViewById(R.id.checkboxImages)
        checkboxText = view.findViewById(R.id.checkboxText)

        if (checkboxImages.isChecked) selectedCount++
        if (checkboxText.isChecked) selectedCount++

        if (selectedCount >= 1) {
            if (!checkboxImages.isChecked) checkboxImages.isEnabled = false
            if (!checkboxText.isChecked) checkboxText.isEnabled = false
        }

        checkboxImages.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setCheckBoxState("Question5_checkbox0", isChecked)

            if (isChecked) {
                selectedCount++
                if (selectedCount > 1) {
                    checkboxImages.isChecked = false
                    selectedCount--
                    Toast.makeText(
                        requireContext(),
                        "You can only select 1 option",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else {
                    checkboxText.isEnabled = false
                }
            } else {
                selectedCount--
                checkboxText.isEnabled = true
            }
        }
        checkboxText.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setCheckBoxState("Question5_checkbox1", isChecked)
        }

        checkboxImages.isChecked = viewModel.getCheckBoxState("Question5_checkbox0")
        checkboxText.isChecked = viewModel.getCheckBoxState("Question5_checkbox1")
    }
}