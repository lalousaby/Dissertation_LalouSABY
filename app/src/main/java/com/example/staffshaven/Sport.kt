package com.example.staffshaven

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView

class Sport : Fragment() {

    private lateinit var socialSport: CardView
    private lateinit var walk: CardView
    private lateinit var gymCard: CardView
    private lateinit var yogaCard: CardView
    private lateinit var compTeams: CardView

    private var urlGym = "https://www.staffs.ac.uk/about/facilities/sports-centre/gym-membership"
    private var urlYoga = "https://www.youtube.com/watch?v=YbAYMQC_ZaE"
    private var urlSocialSport = "https://www.staffs.ac.uk/student-life/sport/social-sport"
    private var urlComp = "https://www.staffs.ac.uk/student-life/sport/sports-teams"

    private var destination: String = "Hanley Park, Stoke-on-Trent"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sport, container, false)

        walk = view.requireViewById(R.id.walk)
        gymCard = view.requireViewById(R.id.gymCard)
        socialSport = view.requireViewById(R.id.socialSport)
        yogaCard = view.requireViewById(R.id.yogaCard)
        compTeams = view.requireViewById(R.id.compTeams)

        walk.setOnClickListener { walkClicked(it) }
        gymCard.setOnClickListener { gymClicked(it) }
        socialSport.setOnClickListener { socialClicked(it) }
        yogaCard.setOnClickListener { yogaClicked(it) }
        compTeams.setOnClickListener { compClicked(it) }
        return view
    }

    // Set click listeners to open link
    private fun socialClicked(view: View) {
        val intent: Intent = Intent().apply {
            action = Intent.ACTION_VIEW
            data = Uri.parse(urlSocialSport)
        }
        startActivity(intent)
    }

    // Set click listeners to open link
    private fun compClicked(view: View) {
        val intent: Intent = Intent().apply {
            action = Intent.ACTION_VIEW
            data = Uri.parse(urlComp)
        }
        startActivity(intent)
    }

    // Set click listeners to open youtube link
    private fun yogaClicked(view: View) {
        val intent: Intent = Intent().apply {
            action = Intent.ACTION_VIEW
            data = Uri.parse(urlYoga)
        }
        startActivity(intent)
    }

    // Set click listeners to open Google Maps, show the path from your location to the chosen location
    private fun walkClicked(view: View){
        val intent: Intent = Intent().apply {
            action = Intent.ACTION_VIEW
            data = Uri.parse("google.navigation:q=" + destination + "&mode=w")
        }
        intent.setPackage("com.google.android.apps.maps")
        startActivity(intent)

    }
    // Set click listeners to open link
    private fun gymClicked(view: View){
        val intent: Intent = Intent().apply {
            action = Intent.ACTION_VIEW
            data = Uri.parse(urlGym)
        }
        startActivity(intent)
    }

}