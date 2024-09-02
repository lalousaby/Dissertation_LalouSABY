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
import android.view.MenuInflater
import android.widget.Button
import android.widget.TextView
import androidx.core.app.NotificationCompat
import androidx.navigation.fragment.findNavController
import com.example.staffshaven.databinding.ActivityMainBinding
import com.google.android.material.card.MaterialCardView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.Source
import com.google.firebase.firestore.firestore


class Goals : Fragment() {
    private lateinit var activityInterface: MainActivity.MyActivityInterface
    private lateinit var registerHanleyPark: FloatingActionButton
    private lateinit var enroll: FloatingActionButton
    private lateinit var goal1: TextView
    private lateinit var goal2: TextView
    private lateinit var goal3: TextView
    private lateinit var goal4: TextView
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

        goal1 = view.findViewById(R.id.goal1)
        goal2 = view.findViewById(R.id.goal2)
        goal3 = view.findViewById(R.id.goal3)
        goal4 = view.findViewById(R.id.goal4)

        // store the theme in Firestore
        val db = Firebase.firestore
        val user = Firebase.auth.currentUser

        // Retrieve the theme preference on app launch
        Firebase.auth.addAuthStateListener { auth ->
            if (user != null) {
                val userEmail = user.email
                db.collection("userData").document(userEmail!!) // Use email as document ID
                    .get(Source.SERVER)
                    .addOnSuccessListener { document ->
                        if (document != null && document.exists()) {
                            val selectedContent = document.getString("selectedContent")

                            if (selectedContent != null) {
                                when (selectedContent) {
                                    "Food and Sleep" -> {
                                        goal1.text = "What do you fancy eating tomorrow for lunch? Go grocery shopping for it"
                                        goal2.text = "Eat 3 meals today"
                                        goal3.text = "Put your phone down before 9PM tonight"
                                        goal4.text = "Read a chapter of your book just before going to bed tonight"
                                    }

                                    "Food and Sport" -> {
                                        goal1.text = "Register for a 5km Run around Hanley Park"
                                        // Set click listeners to open links
                                        registerHanleyPark.setOnClickListener{
                                            val intent: Intent = Intent().apply {
                                                action = Intent.ACTION_VIEW
                                                data = Uri.parse(urlHanleyRun)
                                            }
                                            startActivity(intent)
                                        }
                                        goal2.text = "Stretch your body today"
                                        goal3.text = "What do you fancy eating tomorrow for lunch? Go grocery shopping for it"
                                        goal4.text = "Eat 3 meals today"
                                    }

                                    "Food and Relaxation" -> {
                                        goal1.text = "What do you fancy eating tomorrow for lunch? Go grocery shopping for it"
                                        goal2.text = "Eat 3 meals today"
                                        goal3.text = "Do 10 minutes of meditation"
                                        goal4.text = "Read a chapter of your book"
                                    }

                                    "Sleep and Sport" -> {
                                        goal1.text = "Register for a 5km Run around Hanley Park"
                                        // Set click listeners to open links
                                        registerHanleyPark.setOnClickListener{
                                            val intent: Intent = Intent().apply {
                                                action = Intent.ACTION_VIEW
                                                data = Uri.parse(urlHanleyRun)
                                            }
                                            startActivity(intent)
                                        }
                                        goal2.text = "Stretch your body today"
                                        goal3.text = "Put your phone down before 9PM tonight"
                                        goal4.text = "Read a chapter of your book just before going to bed tonight"
                                    }

                                    "Sleep and Relaxation" -> {
                                        goal1.text = "Put your phone down before 9PM tonight"
                                        goal2.text = "Read a chapter of your book just before going to bed tonight"
                                        goal3.text = "Do 10 minutes of meditation"
                                        goal4.text = "Read a chapter of your book"
                                    }

                                    "Sport and Relaxation" -> {
                                        goal1.text = "Register for a 5km Run around Hanley Park"
                                        // Set click listeners to open links
                                        registerHanleyPark.setOnClickListener{
                                            val intent: Intent = Intent().apply {
                                                action = Intent.ACTION_VIEW
                                                data = Uri.parse(urlHanleyRun)
                                            }
                                            startActivity(intent)
                                        }
                                        goal2.text = "Stretch your body today"
                                        goal3.text = "Do 10 minutes of meditation"
                                        goal4.text = "Read a chapter of your book"
                                    }

                                }
                            } else {
                                goal1.text = "What do you fancy eating tomorrow for lunch? Go grocery shopping for it"
                                goal2.text = "Eat 3 meals today"
                                goal3.text = "Put your phone down before 9PM tonight"
                                goal4.text = "Read a chapter of your book just before going to bed tonight"
                            }
                        }
                    }
            }
        }

        enroll = view.findViewById(R.id.enroll)
        registerHanleyPark = view.findViewById(R.id.registerHanleyPark)


        // Set click listeners to open links
        enroll.setOnClickListener{
            val intent: Intent = Intent().apply {
                action = Intent.ACTION_VIEW
                data = Uri.parse(urlEnroll)
            }
            startActivity(intent)
        }
        return view
    }
}