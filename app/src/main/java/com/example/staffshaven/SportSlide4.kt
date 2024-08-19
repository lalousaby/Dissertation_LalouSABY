package com.example.staffshaven

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

class SportSlide4 : Fragment() {
    private var urlYoga = "https://www.youtube.com/watch?v=YbAYMQC_ZaE"

    private lateinit var yogaLayout: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_sport_slide4, container, false)

        yogaLayout = view.requireViewById(R.id.yogaLayout)
        yogaLayout.setOnClickListener {
            val intent: Intent = Intent().apply {
                action = Intent.ACTION_VIEW
                data = Uri.parse(urlYoga)
            }
            startActivity(intent)
        }
        return view
    }

}