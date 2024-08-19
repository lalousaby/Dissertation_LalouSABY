package com.example.staffshaven

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

class SportSlide3 : Fragment() {

    private var urlSocialSport = "https://www.staffs.ac.uk/student-life/sport/social-sport"

    private lateinit var socialSportLayout: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_sport_slide3, container, false)

        socialSportLayout = view.requireViewById(R.id.socialSportLayout)
        socialSportLayout.setOnClickListener {
            val intent: Intent = Intent().apply {
                action = Intent.ACTION_VIEW
                data = Uri.parse(urlSocialSport)
            }
            startActivity(intent)
        }

        return view
    }

}