package com.example.staffshaven

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

class SportSlide5 : Fragment() {

    private var urlComp = "https://www.staffs.ac.uk/student-life/sport/sports-teams"

    private lateinit var competitionLayout: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_sport_slide5, container, false)

        competitionLayout = view.requireViewById(R.id.competitionLayout)
        competitionLayout.setOnClickListener {
            val intent: Intent = Intent().apply {
                action = Intent.ACTION_VIEW
                data = Uri.parse(urlComp)
            }
            startActivity(intent)
        }

        return view
    }

}