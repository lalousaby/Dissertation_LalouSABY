package com.example.staffshaven

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.card.MaterialCardView
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.Source
import com.google.firebase.firestore.firestore


class findFood : Fragment() {

    private lateinit var SUSLeekRoad: MaterialCardView
    private lateinit var SUSCollegeRoad: MaterialCardView
    private lateinit var aldi: MaterialCardView
    private lateinit var lidl: MaterialCardView

    private lateinit var imgLidl : ImageView
    private lateinit var imgAldi : ImageView

    private lateinit var txtLidl : TextView
    private lateinit var txtAldi : TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_find_food, container, false)
        SUSLeekRoad = view.findViewById(R.id.SUSLeekRoad)
        SUSCollegeRoad = view.findViewById(R.id.SUSCollegeRoad)
        aldi = view.findViewById(R.id.aldi)
        lidl = view.findViewById(R.id.lidl)
        imgLidl = view.findViewById(R.id.imgLidl)
        imgAldi = view.findViewById(R.id.imgAldi)
        txtLidl = view.findViewById(R.id.txtLidl)
        txtAldi = view.findViewById(R.id.txtAldi)

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
                                        imgLidl.visibility = View.VISIBLE
                                        imgAldi.visibility = View.VISIBLE
                                        txtLidl.visibility = View.GONE
                                        txtAldi.visibility = View.GONE
                                    }
                                    "Text" -> {
                                        imgLidl.visibility = View.GONE
                                        imgAldi.visibility = View.GONE
                                        txtLidl.visibility = View.VISIBLE
                                        txtAldi.visibility = View.VISIBLE
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


    // Open google maps and open the location's page
    private fun geolocate(location: String){
        val intent: Intent = Intent().apply {
            action = Intent.ACTION_VIEW
            data = Uri.parse("geo:0,0?q=" + location)
        }
        startActivity(intent)
    }
}