package com.example.staffshaven

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.activityViewModels

class DashboardClick3 : Fragment() {
    private lateinit var LArrowClick3 : ImageButton
    private val viewModel: MyViewModel by activityViewModels()
    private lateinit var selectionFoodTextView: TextView
    private lateinit var dataJournaling: List<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dashboard_click3, container, false)

        LArrowClick3 = view.findViewById(R.id.LArrowClick3)
        LArrowClick3.setOnClickListener {
            val dashboardClick2Fragment = DashboardClick2()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame_layout, dashboardClick2Fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        selectionFoodTextView = view.findViewById(R.id.selectionFoodTextView)
        dataJournaling = viewModel.selectedOptionsJournaling

        for (element in dataJournaling) {
            if (element == "Only snacks"){
                selectionFoodTextView.text = "Eating only snacks is not enough for your body, it needs its fuel. \nPlease try to eat something, and eat at least a full meal tomorrow. \nYou can do it!!"
            }
            if (element == "1 meal"){
                selectionFoodTextView.text = "Eating only one meal is not enough for your body, but it is a good start. Try to eat more today, and the next fews days! \nYou can make it!"
            }
            if (element == "2 meals"){
                selectionFoodTextView.text = "There is improvement! 2 meals is great, but don't forget the third one! \nAll 3 meals are really important for your body, it will help you go through the day with ease!"
            }
            if (element == "3 meals!"){
                selectionFoodTextView.text = "Congratulations! 3 meals is amazing! \nYou are doing really well, continue this way!!"
            }
        }
    }

}