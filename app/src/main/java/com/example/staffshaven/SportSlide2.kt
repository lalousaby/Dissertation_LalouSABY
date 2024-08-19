package com.example.staffshaven

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

class SportSlide2 : Fragment() {
    private lateinit var gymLayout: LinearLayout

    private var urlGym = "https://www.staffs.ac.uk/about/facilities/sports-centre/gym-membership"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_sport_slide2, container, false)

        gymLayout = view.requireViewById(R.id.gymLayout)
        gymLayout.setOnClickListener{
            val intent: Intent = Intent().apply {
                action = Intent.ACTION_VIEW
                data = Uri.parse(urlGym)
            }
            startActivity(intent)
        }

        return view
    }

}