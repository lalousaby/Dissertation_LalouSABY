package com.example.staffshaven

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.activityViewModels


class Journal_click3 : Fragment() {
    private val viewModel: MyViewModel by activityViewModels()

    private lateinit var RArrowClick3 : ImageButton
    private lateinit var LArrowClick3 : ImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_journal_click3, container, false)

        RArrowClick3 = view.findViewById(R.id.RArrowClick3)
        RArrowClick3.setOnClickListener {
            val journalClick4Fragment = Journal_click4()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame_layout, journalClick4Fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        LArrowClick3 = view.findViewById(R.id.LArrowClick3)
        LArrowClick3.setOnClickListener {
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

        val positive1 = view.findViewById<EditText>(R.id.positive1)
        val positive2 = view.findViewById<EditText>(R.id.positive2)
        val positive3 = view.findViewById<EditText>(R.id.positive3)

        positive1.addTextChangedListener (object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                viewModel.onPositive1TextChanged(s.toString())
            }
        })
        positive2.addTextChangedListener (object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                viewModel.onPositive2TextChanged(s.toString())
            }
        })
        positive3.addTextChangedListener (object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                viewModel.onPositive3TextChanged(s.toString())
            }
        })

        viewModel.positive1JournalClick.observe(viewLifecycleOwner) { text1 ->if (positive1.text.toString() != text1) { // Check if text needs to be updated
            positive1.setText(text1)
        }
        }
        viewModel.positive2JournalClick.observe(viewLifecycleOwner) { text2 ->if (positive2.text.toString() != text2) { // Check if text needs to be updated
            positive2.setText(text2)
        }
        }
        viewModel.positive3JournalClick.observe(viewLifecycleOwner) { text3 ->if (positive3.text.toString() != text3) { // Check if text needs to be updated
            positive3.setText(text3)
        }
        }
    }
}