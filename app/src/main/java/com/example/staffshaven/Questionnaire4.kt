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

class Questionnaire4 : Fragment() {
    private val viewModel: MyViewModel by activityViewModels()

    private lateinit var RArrow4 : ImageButton
    private lateinit var LArrow4 : ImageButton

    private lateinit var checkbox5_swipe: CheckBox
    private lateinit var checkbox5_slide: CheckBox
    private lateinit var checkbox5_click: CheckBox

    private var selectedCount = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_questionnaire4, container, false)

        RArrow4 = view.findViewById(R.id.RArrow5)
        RArrow4.setOnClickListener {
            val questionnaire5Fragment = Questionnaire5()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.questionnaire4Frag, questionnaire5Fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        LArrow4 = view.findViewById(R.id.LArrow5)
        LArrow4.setOnClickListener {
            val questionnaire3Fragment = Questionnaire3()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.questionnaire4Frag, questionnaire3Fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //to keep the checkboxes checked even when changing fragments
        checkbox5_swipe = view.findViewById(R.id.checkboxSwipe)
        checkbox5_slide = view.findViewById(R.id.checkboxSlide)
        checkbox5_click = view.findViewById(R.id.checkboxClick)

        if (checkbox5_swipe.isChecked) selectedCount++
        if (checkbox5_slide.isChecked) selectedCount++
        if (checkbox5_click.isChecked) selectedCount++

        if (selectedCount >= 1) {
            if (!checkbox5_swipe.isChecked) checkbox5_swipe.isEnabled = false
            if (!checkbox5_slide.isChecked) checkbox5_slide.isEnabled = false
            if (!checkbox5_click.isChecked) checkbox5_click.isEnabled = false
        }

        checkbox5_swipe.setOnCheckedChangeListener{ _, isChecked ->
            viewModel.setCheckBoxState("Question4_checkbox0", isChecked)

            if (isChecked){
                selectedCount++
                if (selectedCount > 1){
                    checkbox5_swipe.isChecked = false
                    selectedCount--
                    Toast.makeText(requireContext(), "You can only select 1 option", Toast.LENGTH_SHORT).show()
                }
                else {
                    checkbox5_slide.isEnabled = false
                    checkbox5_click.isEnabled = false
                }
            } else {
                selectedCount--
                checkbox5_slide.isEnabled = true
                checkbox5_click.isEnabled = true
            }
        }
        checkbox5_slide.setOnCheckedChangeListener{ _, isChecked ->
            viewModel.setCheckBoxState("Question4_checkbox1", isChecked)

            if (isChecked){
                selectedCount++
                if (selectedCount > 1){
                    checkbox5_slide.isChecked = false
                    selectedCount--
                    Toast.makeText(requireContext(), "You can only select 1 option", Toast.LENGTH_SHORT).show()
                } else{
                    checkbox5_swipe.isEnabled = false
                    checkbox5_click.isEnabled = false
                }
            } else {
                selectedCount--
                checkbox5_swipe.isEnabled = true
                checkbox5_click.isEnabled = true
            }
        }
        checkbox5_click.setOnCheckedChangeListener{ _, isChecked ->
            viewModel.setCheckBoxState("Question4_checkbox2", isChecked)

            if (isChecked){
                selectedCount++
                if (selectedCount > 1){
                    checkbox5_click.isChecked = false
                    selectedCount--
                    Toast.makeText(requireContext(), "You can only select 1 option", Toast.LENGTH_SHORT).show()
                } else{
                    checkbox5_swipe.isEnabled = false
                    checkbox5_slide.isEnabled = false
                }
            } else {
                selectedCount--
                checkbox5_swipe.isEnabled = true
                checkbox5_slide.isEnabled = true
            }
        }

        checkbox5_swipe.isChecked = viewModel.getCheckBoxState("Question4_checkbox0")
        checkbox5_slide.isChecked = viewModel.getCheckBoxState("Question4_checkbox1")
        checkbox5_click.isChecked = viewModel.getCheckBoxState("Question4_checkbox2")
    }
}