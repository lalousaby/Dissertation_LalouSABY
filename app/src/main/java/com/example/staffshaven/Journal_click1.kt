package com.example.staffshaven

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels


class Journal_click1 : Fragment() {
    private val viewModel: MyViewModel by activityViewModels()

    private lateinit var RArrowClick : ImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_journal_click1, container, false)

        RArrowClick = view.findViewById(R.id.RArrowClick1)
        RArrowClick.setOnClickListener {
            val journalClick2Fragment = Journal_click2()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame_layout, journalClick2Fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editDayTxt = view.findViewById<EditText>(R.id.editDayTxt)
        editDayTxt.addTextChangedListener (object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                viewModel.onTextChanged(s.toString())
            }
        })

        viewModel.dayTextJournalClick.observe(viewLifecycleOwner) { text ->if (editDayTxt.text.toString() != text) { // Check if text needs to be updated
            editDayTxt.setText(text)
            }
        }
    }
}