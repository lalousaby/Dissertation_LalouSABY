package com.example.staffshaven

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.fragment.app.activityViewModels

class SportClick5 : Fragment() {
    private val viewModel: MyViewModel by activityViewModels()

    private lateinit var LArrowClick5 : ImageButton

    private var urlComp = "https://www.staffs.ac.uk/student-life/sport/sports-teams"

    private lateinit var competitionLayout: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sport_click5, container, false)

        LArrowClick5 = view.findViewById(R.id.LArrowClick5)
        LArrowClick5.setOnClickListener {
            val sportClick4Fragment = SportClick4()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame_layout, sportClick4Fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

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