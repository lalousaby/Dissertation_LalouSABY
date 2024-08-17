package com.example.staffshaven

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.activityViewModels


class JournalSlide3 : Fragment() {

    private val viewModel: MyViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_journal_slide3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val positive1 = view.findViewById<EditText>(R.id.positive1Slide)
        val positive2 = view.findViewById<EditText>(R.id.positive2Slide)
        val positive3 = view.findViewById<EditText>(R.id.positive3Slide)

        positive1.addTextChangedListener (object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                viewModel.onPositive1TextChangedSlide(s.toString())
            }
        })
        positive2.addTextChangedListener (object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                viewModel.onPositive2TextChangedSlide(s.toString())
            }
        })
        positive3.addTextChangedListener (object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                viewModel.onPositive3TextChangedSlide(s.toString())
            }
        })

        viewModel.positive1JournalSlide.observe(viewLifecycleOwner) { text1 ->if (positive1.text.toString() != text1) { // Check if text needs to be updated
            positive1.setText(text1)
        }
        }
        viewModel.positive2JournalSlide.observe(viewLifecycleOwner) { text2 ->if (positive2.text.toString() != text2) { // Check if text needs to be updated
            positive2.setText(text2)
        }
        }
        viewModel.positive3JournalSlide.observe(viewLifecycleOwner) { text3 ->if (positive3.text.toString() != text3) { // Check if text needs to be updated
            positive3.setText(text3)
        }
        }
    }
}