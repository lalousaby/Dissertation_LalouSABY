package com.example.staffshaven

import android.os.Bundle
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

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        val editText = view.findViewById<EditText>(R.id.editDayTxt)
//
//        editText.addTextChangedListener { editable ->
//            viewModel.dayTextJournalClick.value = editable.toString()
//        }
//
//        viewModel.dayTextJournalClick.observe(viewLifecycleOwner) { content ->
//            editText.setText(content)
//        }
//    }

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

}