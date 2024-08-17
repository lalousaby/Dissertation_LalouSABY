package com.example.staffshaven

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.fragment.app.activityViewModels

class JournalSlide5 : Fragment() {
    private val viewModel: MyViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_journal_slide5, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val radioGroup = view.findViewById<RadioGroup>(R.id.radioGroupJournalSlide)

        viewModel.selectedRadioButtonIdSlide.observe(viewLifecycleOwner) { checkedId ->
            if (checkedId != -1) {
                radioGroup.check(checkedId)
            }
        }

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            viewModel.onRadioButtonSelectedSlide(checkedId)
        }

    }
}