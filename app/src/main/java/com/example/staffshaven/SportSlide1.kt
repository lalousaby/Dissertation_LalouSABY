package com.example.staffshaven

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import androidx.fragment.app.activityViewModels


class SportSlide1 : Fragment() {
    private val viewModel: MyViewModel by activityViewModels()
    private lateinit var walk : LinearLayout

    private var destination: String = "Hanley Park, Stoke-on-Trent"

    override fun onCreateView(
        inflater: LayoutInflater,container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_sport_slide1, container, false)

        walk = view.requireViewById(R.id.walkLayout)

        walk.setOnClickListener{
            val intent: Intent = Intent().apply {
                action = Intent.ACTION_VIEW
                data = Uri.parse("google.navigation:q=" + destination + "&mode=w")
            }
            intent.setPackage("com.google.android.apps.maps")
            startActivity(intent)
        }

        return view
    }
}