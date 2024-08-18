package com.example.staffshaven

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import androidx.fragment.app.activityViewModels
import com.airbnb.lottie.LottieAnimationView
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.Source
import com.google.firebase.firestore.firestore

class JournalSlide2 : Fragment() {
    private val viewModel: MyViewModel by activityViewModels()

    private lateinit var frameLayoutSlideAnimationYes : FrameLayout
    private lateinit var frameLayoutSlideAnimationNo : FrameLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_journal_slide2, container, false)

        frameLayoutSlideAnimationYes = view.findViewById(R.id.frameLayoutSlideAnimationYes)
        frameLayoutSlideAnimationYes.setOnClickListener {
            viewModel.selectedStudyBtnSlide = R.id.btnStudyYesSlide
            viewModel.onStudyYesSelectedSlide()
            updateAnimationSelection()
        }
        frameLayoutSlideAnimationNo = view.findViewById(R.id.frameLayoutSlideAnimationNo)
        frameLayoutSlideAnimationNo.setOnClickListener {
            viewModel.selectedStudyBtnSlide = R.id.btnStudyNoSlide
            viewModel.onStudyNoSelectedSlide()
            updateAnimationSelection()
        }


        val btnStudyYesSlide : Button = view.findViewById(R.id.btnStudyYesSlide)
        val btnStudyNoSlide : Button = view.findViewById(R.id.btnStudyNoSlide)

        // store the theme in Firestore
        val db = Firebase.firestore
        val user = Firebase.auth.currentUser

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
                                        frameLayoutSlideAnimationYes.visibility = View.VISIBLE
                                        frameLayoutSlideAnimationNo.visibility = View.VISIBLE
                                        btnStudyYesSlide.visibility = View.GONE
                                        btnStudyNoSlide.visibility = View.GONE
                                    }
                                    "Text" -> {
                                        frameLayoutSlideAnimationYes.visibility = View.GONE
                                        frameLayoutSlideAnimationNo.visibility = View.GONE
                                        btnStudyYesSlide.visibility = View.VISIBLE
                                        btnStudyNoSlide.visibility = View.VISIBLE
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
        val selectedStudyBtnId = viewModel.selectedStudyBtnIdSlide

        frameLayoutSlideAnimationYes.isSelected = viewModel.isStudyYesSelectedSlide
        frameLayoutSlideAnimationNo.isSelected = viewModel.isStudyNoSelectedSlide
    }
}