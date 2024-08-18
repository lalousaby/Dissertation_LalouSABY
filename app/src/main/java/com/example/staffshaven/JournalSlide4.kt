package com.example.staffshaven

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import androidx.fragment.app.activityViewModels
import com.airbnb.lottie.LottieAnimationView
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.Source
import com.google.firebase.firestore.firestore


class JournalSlide4 : Fragment() {

    private val viewModel: MyViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_journal_slide4, container, false)

        var animationNo : LottieAnimationView = view.findViewById(R.id.animationNo)

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
                                        animationNo.visibility = View.VISIBLE
                                    }
                                    "Text" -> {
                                        animationNo.visibility = View.GONE
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

        val ratingBar = view.findViewById<RatingBar>(R.id.sleepRatingBarSlide)
        ratingBar.onRatingBarChangeListener = RatingBar.OnRatingBarChangeListener { _, rating, _ ->
            viewModel.onRatingChangedSlide(rating)
        }

        viewModel.sleepRatingSlide.observe(viewLifecycleOwner) { rating ->
            ratingBar.rating = rating
        }
    }
}