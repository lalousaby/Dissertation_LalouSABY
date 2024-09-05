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
import com.example.staffshaven.databinding.ActivityMainBinding
import com.google.android.material.card.MaterialCardView
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.Source
import com.google.firebase.firestore.firestore


class Journal_click2 : Fragment() {
    private val viewModel: MyViewModel by activityViewModels()

    private lateinit var RArrowClick2 : ImageButton
    private lateinit var LArrowClick2 : ImageButton
    private lateinit var btnStudyYes : Button
    private lateinit var btnStudyNo : Button

    private lateinit var frameLayoutClickAnimationYes : FrameLayout
    private lateinit var frameLayoutClickAnimationNo : FrameLayout

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

        btnStudyYes = view.findViewById(R.id.btnStudyYes)
        btnStudyYes.setOnClickListener {
            viewModel.selectedStudyBtn = R.id.btnStudyYes
            updateButtonColors()
        }
        btnStudyNo = view.findViewById(R.id.btnStudyNo)
        btnStudyNo.setOnClickListener {
            viewModel.selectedStudyBtn = R.id.btnStudyNo
            updateButtonColors()
        }

        frameLayoutClickAnimationYes = view.findViewById(R.id.frameLayoutClickAnimationYes)
        frameLayoutClickAnimationYes.setOnClickListener {
            viewModel.selectedStudyBtn = R.id.btnStudyYes
            viewModel.onStudyYesSelected()
            updateAnimationSelection()
        }
        frameLayoutClickAnimationNo = view.findViewById(R.id.frameLayoutClickAnimationNo)
        frameLayoutClickAnimationNo.setOnClickListener {
            viewModel.selectedStudyBtn = R.id.btnStudyNo
            viewModel.onStudyNoSelected()
            updateAnimationSelection()
        }

        // store the theme in Firestore
        val db = Firebase.firestore
        val user = Firebase.auth.currentUser

        val btnStudyYes : Button = view.findViewById(R.id.btnStudyYes)
        val btnStudyNo : Button = view.findViewById(R.id.btnStudyNo)


        Firebase.auth.addAuthStateListener { auth ->
            if (user != null) {
                val userEmail = user.email
                db.collection("userData").document(userEmail!!) // Use email as document ID
                    .get(Source.SERVER)
                    .addOnSuccessListener { document ->
                        if (document != null && document.exists()) {
                            val selectedType = document.getString("selectedType")
                            if (selectedType != null) {
                                when (selectedType) {
                                    "Images" -> {
                                        frameLayoutClickAnimationYes.visibility = View.VISIBLE
                                        frameLayoutClickAnimationNo.visibility = View.VISIBLE
                                        btnStudyYes.visibility = View.GONE
                                        btnStudyNo.visibility = View.GONE
                                    }
                                    "Text" -> {
                                        frameLayoutClickAnimationYes.visibility = View.GONE
                                        frameLayoutClickAnimationNo.visibility = View.GONE
                                        btnStudyYes.visibility = View.VISIBLE
                                        btnStudyNo.visibility = View.VISIBLE
                                    }
//
                                }
                            }

                        }
                    }
            }
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateAnimationSelection()
    }

    private fun updateAnimationSelection() {
        val selectedStudyBtnId = viewModel.selectedStudyBtnId

        if (frameLayoutClickAnimationYes.visibility == View.VISIBLE) {
            frameLayoutClickAnimationYes.isSelected = viewModel.isStudyYesSelected
        } else {
            frameLayoutClickAnimationYes.isSelected = false // Set to false if GONE
        }
        if (frameLayoutClickAnimationNo.visibility == View.VISIBLE) {
            frameLayoutClickAnimationNo.isSelected = viewModel.isStudyNoSelected
        } else {
        frameLayoutClickAnimationNo.isSelected = false // Set to false if GONE
    }
    }

    private fun updateButtonColors() {

        val selectedButtonId = viewModel.selectedStudyBtn
        btnStudyYes.isSelected = selectedButtonId == R.id.btnStudyYes
        btnStudyNo.isSelected = selectedButtonId == R.id.btnStudyNo

        val typedArray = requireContext().theme.obtainStyledAttributes(
            intArrayOf(android.R.attr.colorAccent, android.R.attr.colorBackground)
        )
        val accentColor = typedArray.getColor(0, 0)
        val backgroundColor = typedArray.getColor(1,0)
        typedArray.recycle()

        btnStudyYes.setBackgroundColor(
            if (viewModel.selectedStudyBtn == R.id.btnStudyYes) backgroundColor else accentColor
        )
        btnStudyNo.setBackgroundColor(
            if (viewModel.selectedStudyBtn == R.id.btnStudyNo) backgroundColor else accentColor
        )
    }
}