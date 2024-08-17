package com.example.staffshaven

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.activityViewModels

class JournalSlide1 : Fragment() {
    private val viewModel: MyViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_journal_slide1, container, false)

        val editText = view.findViewById<EditText>(R.id.editDayTxt)
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                viewModel.onDayTextChangedSlide(s.toString())
            }
        })

        viewModel.dayTextJournalSlide.observe(viewLifecycleOwner) { text ->
            if (editText.text.toString() != text) {
                editText.setText(text)
            }
        }
        return view
    }


}