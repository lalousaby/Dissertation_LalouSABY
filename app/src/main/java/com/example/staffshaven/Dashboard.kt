package com.example.staffshaven

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.staffshaven.databinding.ActivityMainBinding
import com.google.android.material.card.MaterialCardView
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.Source
import com.google.firebase.firestore.firestore

class Dashboard : Fragment() {
    private val viewModel: MyViewModel by activityViewModels()
    private lateinit var selectionTextView : TextView
    private lateinit var selectionSleepTextView: TextView
    private lateinit var selectionFoodTextView: TextView
    private lateinit var dataJournaling: List<String>
    private lateinit var card1: MaterialCardView
    private lateinit var card2: MaterialCardView
    private lateinit var card3: MaterialCardView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for dashboard fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val db = Firebase.firestore
        val user = Firebase.auth.currentUser

        Firebase.auth.addAuthStateListener { auth ->
            if (user != null) {
                val userEmail = user.email
                db.collection("userData").document(userEmail!!) // Use email as document ID
                    .get(Source.SERVER)
                    .addOnSuccessListener { document ->
                        if (document != null && document.exists() && isAdded) {
                            val selectedNav = document.getString("selectedNav")
                            when (selectedNav) {
                                "Swipe" -> {
                                    dataJournaling = viewModel.selectedOptions
                                }
                                "Slide" -> {
                                    dataJournaling = viewModel.selectedOptionsJournalingSlide
                                }
                                "Click" -> {
                                    dataJournaling = viewModel.selectedOptionsJournaling
                                }
                            }
                            selectionTextView = view.findViewById(R.id.selectionTextView)
                            selectionSleepTextView = view.findViewById(R.id.selectionSleepTextView)
                            selectionFoodTextView = view.findViewById(R.id.selectionFoodTextView)
                            card1 = view.findViewById(R.id.card1)
                            card2 = view.findViewById(R.id.card2)
                            card3 = view.findViewById(R.id.card3)

                            for (element in dataJournaling) {
                                if (element == "Study Yes"){
                                    card1.visibility = View.VISIBLE
                                    selectionTextView.text = "Congratulations on studying today!! Continue on this path to success!"
                                }
                                if (element == "Study No"){
                                    card1.visibility = View.VISIBLE
                                    selectionTextView.text = "Don't worry about studying today, you can do it, take your time and try studying again tomorrow!"
                                }
                                if (element == "0.5 stars" || element == "1 star" || element == "1.5 stars" || element == "2 stars"){
                                    card2.visibility = View.VISIBLE
                                    selectionSleepTextView.text = "You are having trouble sleeping lately, try getting some rest today and make sure you turn screens off before 10PM tonight! We believe in you, keep going!"
                                }
                                if (element == "2.5 stars" || element == "3 stars" || element == "3.5 stars"){
                                    card2.visibility = View.VISIBLE
                                    selectionSleepTextView.text = "You could improve your sleep routine by reading a book before going to bed early tonight! \nKeep improving, we believe in you!"
                                }
                                if (element == "4 stars" || element == "4.5 stars" || element == "5 stars"){
                                    card2.visibility = View.VISIBLE
                                    selectionSleepTextView.text = "Your sleep schedule seems great, congratulations and keep it up! The improvement is amazing!"
                                }
                                if (element == "Only snacks"){
                                    card3.visibility = View.VISIBLE
                                    selectionFoodTextView.text = "Eating only snacks is not enough for your body, it needs its fuel. \nPlease try to eat something, and eat at least a full meal tomorrow. \nYou can do it!!"
                                }
                                if (element == "1 meal"){
                                    card3.visibility = View.VISIBLE
                                    selectionFoodTextView.text = "Eating only one meal is not enough for your body, but it is a good start. Try to eat more today, and the next fews days! \nYou can make it!"
                                }
                                if (element == "2 meals"){
                                    card3.visibility = View.VISIBLE
                                    selectionFoodTextView.text = "There is improvement! 2 meals is great, but don't forget the third one! \nAll 3 meals are really important for your body, it will help you go through the day with ease!"
                                }
                                if (element == "3 meals!"){
                                    card3.visibility = View.VISIBLE
                                    selectionFoodTextView.text = "Congratulations! 3 meals is amazing! \nYou are doing really well, continue this way!!"
                                }

                            }

                        }
                    }
            }
        }


    }

}