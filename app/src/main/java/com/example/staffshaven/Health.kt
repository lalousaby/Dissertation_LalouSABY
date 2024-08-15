package com.example.staffshaven

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController

class Health : Fragment() {

    private lateinit var btnFood_fromHealth: Button
    private lateinit var btnSports_fromHealth: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_health, container, false)
        btnFood_fromHealth = view.findViewById(R.id.btnFood_fromHealth)
        btnFood_fromHealth.setOnClickListener {
            goToFood()
        }
        btnSports_fromHealth = view.findViewById(R.id.btnSports_fromHealth)
        btnSports_fromHealth.setOnClickListener {
            goToSport()
        }
        return view
    }

    private fun goToSport() {
        val sportFragment = Sport()
        val transaction = requireActivity().supportFragmentManager.beginTransaction()

        transaction.replace(R.id.healthFrag, sportFragment)
        transaction.commit()
    }

    private fun goToFood(){
        val foodFragment = Food()
        val transaction = requireActivity().supportFragmentManager.beginTransaction()

        transaction.replace(R.id.healthFrag, foodFragment)
        transaction.commit()
    }
}