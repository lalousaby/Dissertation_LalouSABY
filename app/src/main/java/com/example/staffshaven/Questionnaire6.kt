package com.example.staffshaven

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.app.ActivityCompat.recreate
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels

class Questionnaire6 : Fragment() {
    private val viewModel: MyViewModel by activityViewModels()

    private lateinit var endQuestBtn : Button
    private lateinit var LArrow6 : ImageButton

    private lateinit var checkbox7_sleep: CheckBox
    private lateinit var checkbox7_food: CheckBox
    private lateinit var checkbox7_sport: CheckBox
    private lateinit var checkbox7_relaxation: CheckBox

    private var selectedCount = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_questionnaire6, container, false)
        LArrow6 = view.findViewById(R.id.LArrow7)
        LArrow6.setOnClickListener {
            val questionnaire5Fragment = Questionnaire5()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.questionnaire6Frag, questionnaire5Fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //to keep the checkboxes checked even when changing fragments
        checkbox7_food = view.findViewById(R.id.checkboxFood)
        checkbox7_sleep = view.findViewById(R.id.checkboxSleep)
        checkbox7_sport = view.findViewById(R.id.checkboxSport)
        checkbox7_relaxation = view.findViewById(R.id.checkboxRelaxation)

        if (checkbox7_food.isChecked) selectedCount++
        if (checkbox7_sleep.isChecked) selectedCount++
        if (checkbox7_sport.isChecked) selectedCount++
        if (checkbox7_relaxation.isChecked) selectedCount++

        if (selectedCount >= 3){
            if (!checkbox7_food.isChecked) checkbox7_food.isEnabled = false
            if (!checkbox7_sleep.isChecked) checkbox7_sleep.isEnabled = false
            if (!checkbox7_sport.isChecked) checkbox7_sport.isEnabled = false
            if (!checkbox7_relaxation.isChecked) checkbox7_relaxation.isEnabled = false
        }

        checkbox7_sleep.setOnCheckedChangeListener{ _, isChecked ->
            viewModel.setCheckBoxState("Question6_checkbox0", isChecked)

            if (isChecked){
                selectedCount++
                if (selectedCount > 2){
                    checkbox7_sleep.isChecked = false
                    selectedCount--
                    Toast.makeText(requireContext(), "You can only select 2 options", Toast.LENGTH_SHORT).show()
                }
                else if (selectedCount == 2){
                    checkbox7_food.isEnabled = false
                    checkbox7_sport.isEnabled = false
                    checkbox7_relaxation.isEnabled = false
                }
            } else {
                selectedCount--
                checkbox7_food.isEnabled = true
                checkbox7_sport.isEnabled = true
                checkbox7_relaxation.isEnabled = true
            }
        }
        checkbox7_food.setOnCheckedChangeListener{ _, isChecked ->
            viewModel.setCheckBoxState("Question6_checkbox1", isChecked)

            if (isChecked){
                selectedCount++
                if (selectedCount > 2){
                    checkbox7_food.isChecked = false
                    selectedCount--
                    Toast.makeText(requireContext(), "You can only select 2 options", Toast.LENGTH_SHORT).show()
                }
                else if (selectedCount == 2){
                    checkbox7_sleep.isEnabled = false
                    checkbox7_sport.isEnabled = false
                    checkbox7_relaxation.isEnabled = false
                }
            } else {
                selectedCount--
                checkbox7_sleep.isEnabled = true
                checkbox7_sport.isEnabled = true
                checkbox7_relaxation.isEnabled = true
            }
        }
        checkbox7_sport.setOnCheckedChangeListener{ _, isChecked ->
            viewModel.setCheckBoxState("Question6_checkbox2", isChecked)

            if (isChecked){
                selectedCount++
                if (selectedCount > 2) {
                    checkbox7_sport.isChecked = false
                    selectedCount--
                    Toast.makeText(
                        requireContext(),
                        "You can only select 2 options",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (selectedCount == 2) {
                    checkbox7_sleep.isEnabled = false
                    checkbox7_food.isEnabled = false
                    checkbox7_relaxation.isEnabled = false
                }
            } else {
                selectedCount--
                checkbox7_sleep.isEnabled = true
                checkbox7_food.isEnabled = true
                checkbox7_relaxation.isEnabled = true
            }
        }
        checkbox7_relaxation.setOnCheckedChangeListener{ _, isChecked ->
            viewModel.setCheckBoxState("Question6_checkbox3", isChecked)

            if (isChecked){
                selectedCount++
                if (selectedCount > 2) {
                    checkbox7_relaxation.isChecked = false
                    selectedCount--
                    Toast.makeText(
                        requireContext(), "You can only select 2 options",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
                else if (selectedCount == 2){
                    checkbox7_sleep.isEnabled = false
                    checkbox7_food.isEnabled = false
                    checkbox7_sport.isEnabled = false
                }
            } else {
                selectedCount--
                checkbox7_sleep.isEnabled = true
                checkbox7_food.isEnabled = true
                checkbox7_sport.isEnabled = true
            }
        }

        checkbox7_food.isChecked = viewModel.getCheckBoxState("Question6_checkbox0")
        checkbox7_sleep.isChecked = viewModel.getCheckBoxState("Question6_checkbox1")
        checkbox7_sport.isChecked = viewModel.getCheckBoxState("Question6_checkbox2")
        checkbox7_relaxation.isChecked = viewModel.getCheckBoxState("Question6_checkbox3")

        // finish button
        endQuestBtn = view.findViewById(R.id.endQuestBtn)
        endQuestBtn.setOnClickListener {
            viewModel._selectedOptions.clear()

            if (viewModel.getCheckBoxState("Question1_checkbox0")) viewModel._selectedOptions.add("Blue 1")
            if (viewModel.getCheckBoxState("Question1_checkbox1")) viewModel._selectedOptions.add("Pink 1")
            if (viewModel.getCheckBoxState("Question1_checkbox2")) viewModel._selectedOptions.add("Green 1")
            if (viewModel.getCheckBoxState("Question1_checkbox3")) viewModel._selectedOptions.add("Orange 1")
            if (viewModel.getCheckBoxState("Question1_checkbox4")) viewModel._selectedOptions.add("Grey 1")
            if (viewModel.getCheckBoxState("Question2_checkbox0")) viewModel._selectedOptions.add("Blue 2")
            if (viewModel.getCheckBoxState("Question2_checkbox1")) viewModel._selectedOptions.add("Pink 2")
            if (viewModel.getCheckBoxState("Question2_checkbox2")) viewModel._selectedOptions.add("Green 2")
            if (viewModel.getCheckBoxState("Question2_checkbox3")) viewModel._selectedOptions.add("Orange 2")
            if (viewModel.getCheckBoxState("Question2_checkbox4")) viewModel._selectedOptions.add("Grey 2")
            if (viewModel.getCheckBoxState("Question3_checkbox0")) viewModel._selectedOptions.add("Dark")
            if (viewModel.getCheckBoxState("Question3_checkbox1")) viewModel._selectedOptions.add("Bright")
            if (viewModel.getCheckBoxState("Question3_checkbox2")) viewModel._selectedOptions.add("Pastel")
            if (viewModel.getCheckBoxState("Question4_checkbox0")) viewModel._selectedOptions.add("Swipe")
            if (viewModel.getCheckBoxState("Question4_checkbox1")) viewModel._selectedOptions.add("Slide")
            if (viewModel.getCheckBoxState("Question4_checkbox2")) viewModel._selectedOptions.add("Click")
            if (viewModel.getCheckBoxState("Question5_checkbox0")) viewModel._selectedOptions.add("Images")
            if (viewModel.getCheckBoxState("Question5_checkbox1")) viewModel._selectedOptions.add("Text")
            if (viewModel.getCheckBoxState("Question6_checkbox0")) viewModel._selectedOptions.add("Sleep")
            if (viewModel.getCheckBoxState("Question6_checkbox1")) viewModel._selectedOptions.add("Food")
            if (viewModel.getCheckBoxState("Question6_checkbox2")) viewModel._selectedOptions.add("Sport")
            if (viewModel.getCheckBoxState("Question6_checkbox3")) viewModel._selectedOptions.add("Relaxation")

            val selectedOptions = viewModel.selectedOptions
            val summary = "Selected options: ${selectedOptions.joinToString(", ")}"
            Toast.makeText(requireContext(), summary, Toast.LENGTH_SHORT).show()

            val questionnaireListener = activity as? QuestionnaireListener
            questionnaireListener?.onQuestionnaireComplete(selectedOptions)
        }
    }

    interface QuestionnaireListener{
        fun onQuestionnaireComplete(selectedOptions: List<String>)
    }
}