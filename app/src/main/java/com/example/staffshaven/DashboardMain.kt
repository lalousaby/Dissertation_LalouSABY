package com.example.staffshaven

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.card.MaterialCardView
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.Source
import com.google.firebase.firestore.firestore

class DashboardMain : Fragment() {

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
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dashboard_main, container, false)

        val viewPager = view.findViewById<ViewPager2>(R.id.viewPager)
        viewPager.adapter = DashboardSlideAdapter(this)

        return view
    }


}