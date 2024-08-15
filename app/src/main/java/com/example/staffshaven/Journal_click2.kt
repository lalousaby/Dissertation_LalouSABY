package com.example.staffshaven

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.activityViewModels
import android.widget.Button
import android.widget.FrameLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import com.example.staffshaven.Journal_click3
import com.google.android.material.card.MaterialCardView


class Journal_click2 : Fragment() {
    private val viewModel: MyViewModel by activityViewModels()

    private lateinit var RArrowClick2 : ImageButton
    private lateinit var LArrowClick2 : ImageButton

    private lateinit var frameLayoutClickAnimationYes : FrameLayout
    private lateinit var frameLayoutClickAnimationNo : FrameLayout
    private lateinit var selectedStudyBtn: Button
    private lateinit var studyYes: Button
    private lateinit var studyNo: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_journal_click2, container, false)

        RArrowClick2 = view.findViewById(R.id.RArrowClick2)
        RArrowClick2.setOnClickListener {
            val journalClick3Fragment = Journal_click3()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame_layout, journalClick3Fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        LArrowClick2 = view.findViewById(R.id.LArrowClick2)
        LArrowClick2.setOnClickListener {
            val journalClick1Fragment = Journal_click1()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame_layout, journalClick1Fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        frameLayoutClickAnimationYes = view.findViewById(R.id.frameLayoutClickAnimationYes)
        frameLayoutClickAnimationYes.setOnClickListener {
            viewModel.selectedStudyBtn = R.id.btnStudyYes
        }
        frameLayoutClickAnimationNo = view.findViewById(R.id.frameLayoutClickAnimationNo)
        frameLayoutClickAnimationNo.setOnClickListener {
            viewModel.selectedStudyBtn = R.id.btnStudyNo
        }

        return view
    }
}