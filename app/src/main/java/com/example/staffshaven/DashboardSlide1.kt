package com.example.staffshaven

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.google.android.material.card.MaterialCardView

class DashboardSlide1 : Fragment() {

    private lateinit var selectionTextView : TextView
    private val viewModel: MyViewModel by activityViewModels()
    private lateinit var dataJournaling: List<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard_slide1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        selectionTextView = view.findViewById(R.id.selectionTextView)
        dataJournaling = viewModel.selectedOptionsJournalingSlide

        for (element in dataJournaling) {
            if (element == "Study Yes") {
                selectionTextView.text =
                    "Congratulations on studying today!! Continue on this path to success!"
            }
            if (element == "Study No") {
                selectionTextView.text =
                    "Don't worry about studying today, you can do it, take your time and try studying again tomorrow!"
            }
        }
    }

}