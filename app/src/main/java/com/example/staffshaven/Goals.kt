package com.example.staffshaven

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.os.Build
import android.view.ViewGroup
import android.app.NotificationChannel
import android.content.Intent
import android.net.Uri
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.navigation.fragment.findNavController
import com.google.android.material.card.MaterialCardView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class Goals : Fragment() {
    private lateinit var activityInterface: MainActivity.MyActivityInterface
    private lateinit var registerHanleyPark: FloatingActionButton
    private lateinit var enroll: FloatingActionButton
    private lateinit var exerciseScheme: FloatingActionButton
    private val urlEnroll = "https://forms.office.com/pages/responsepage.aspx?id=8nivV33IZkS3u2tsyZ7RJDyrEMBwprVLigLHEKhqLwlUME9MU0g5TEs5RFJCRzZQSU1YMkxXOVNRQiQlQCN0PWcu"
    private val urlHanleyRun = "https://www.parkrun.org.uk/hanley/"
    private val urlExerciseScheme = "https://www.staffs.ac.uk/students/support/student-wellbeing-and-safeguarding/exercise-referral-scheme"

    // Notification channel
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainActivity.MyActivityInterface) {
            activityInterface = context
        }
    }
    private fun callFunctionFromMainActivity() {
        activityInterface.showNotifications()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for goals fragment
        val view = inflater.inflate(R.layout.fragment_goals, container, false)

        val reminderFAB = view.findViewById<FloatingActionButton>(R.id.reminderBtn)
        enroll = view.findViewById(R.id.enroll)
        registerHanleyPark = view.findViewById(R.id.registerHanleyPark)
        exerciseScheme = view.findViewById(R.id.exerciseScheme)

        // Set the onClickListener for the notification
        reminderFAB.setOnClickListener{
            callFunctionFromMainActivity()
        }
        // Set click listeners to open links
        registerHanleyPark.setOnClickListener{
            val intent: Intent = Intent().apply {
                action = Intent.ACTION_VIEW
                data = Uri.parse(urlHanleyRun)
            }
            startActivity(intent)
        }
        // Set click listeners to open links
        enroll.setOnClickListener{
            val intent: Intent = Intent().apply {
                action = Intent.ACTION_VIEW
                data = Uri.parse(urlEnroll)
            }
            startActivity(intent)
        }
        // Set click listeners to open links
        exerciseScheme.setOnClickListener {
            val intent: Intent = Intent().apply {
                action = Intent.ACTION_VIEW
                data = Uri.parse(urlExerciseScheme)
            }
            startActivity(intent)
        }
        return view
    }
}