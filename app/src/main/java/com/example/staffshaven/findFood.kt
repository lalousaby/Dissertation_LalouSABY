package com.example.staffshaven

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.google.android.material.card.MaterialCardView


class findFood : Fragment() {

    private lateinit var SUSLeekRoad: MaterialCardView
    private lateinit var SUSCollegeRoad: MaterialCardView
    private lateinit var aldi: MaterialCardView
    private lateinit var lidl: MaterialCardView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_find_food, container, false)
        SUSLeekRoad = view.findViewById(R.id.SUSLeekRoad)
        SUSCollegeRoad = view.findViewById(R.id.SUSCollegeRoad)
        aldi = view.findViewById(R.id.aldi)
        lidl = view.findViewById(R.id.lidl)

        SUSLeekRoad.setOnClickListener {
            val location = "Leek road shop"
            geolocate(location)
        }
        SUSCollegeRoad.setOnClickListener {
            val location = "Staffordshire University Student Union Shop"
            geolocate(location)
        }
        aldi.setOnClickListener {
            val location = "ALDI, Victoria road"
            geolocate(location)
        }
        lidl.setOnClickListener {
            val location = "Lidl, London Road"
            geolocate(location)
        }
        return view
    }

    // Open google maps and open the location's page
    private fun geolocate(location: String){
        val intent: Intent = Intent().apply {
            action = Intent.ACTION_VIEW
            data = Uri.parse("geo:0,0?q=" + location)
        }
        startActivity(intent)
    }
}