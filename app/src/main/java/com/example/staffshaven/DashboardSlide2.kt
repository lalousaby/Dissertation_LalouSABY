package com.example.staffshaven

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.google.android.material.card.MaterialCardView

class DashboardSlide2 : Fragment() {
    private val viewModel: MyViewModel by activityViewModels()
    private lateinit var selectionSleepTextView: TextView
    private lateinit var dataJournaling: List<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard_slide2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        selectionSleepTextView = view.findViewById(R.id.selectionSleepTextView)
        dataJournaling = viewModel.selectedOptionsJournalingSlide

        for (element in dataJournaling) {
            if (element == "0.5 stars" || element == "1 star" || element == "1.5 stars" || element == "2 stars"){
                selectionSleepTextView.text = "You are having trouble sleeping lately, try getting some rest today and make sure you turn screens off before 10PM tonight! We believe in you, keep going!"
            }
            if (element == "2.5 stars" || element == "3 stars" || element == "3.5 stars"){
                selectionSleepTextView.text = "You could improve your sleep routine by reading a book before going to bed early tonight! \nKeep improving, we believe in you!"
            }
            if (element == "4 stars" || element == "4.5 stars" || element == "5 stars"){
                selectionSleepTextView.text = "Your sleep schedule seems great, congratulations and keep it up! The improvement is amazing!"
            }
        }
    }
}