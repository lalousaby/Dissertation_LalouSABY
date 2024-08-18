package com.example.staffshaven

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.staffshaven.R
import com.example.staffshaven.databinding.ActivityFullQuestionnaireBinding
import com.example.staffshaven.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class FullQuestionnaireActivity : AppCompatActivity(), Questionnaire6.QuestionnaireListener {
    private var selectedTheme : Int = 0
    private var selectedNav : String = ""

    private lateinit var binding: ActivityFullQuestionnaireBinding


    override fun onQuestionnaireComplete(selectedOptions: List<String>) {
        Toast.makeText(
            this,
            "Account created.",
            Toast.LENGTH_SHORT,
        ).show()

        // set the theme based on the selected options
        if (selectedOptions.contains("Blue 1") && selectedOptions.contains("Blue 2") && selectedOptions.contains("Dark")) {
            setTheme(R.style.Theme_StaffsHaven_BlueDark)
            selectedTheme = R.style.Theme_StaffsHaven_BlueDark
        }
        if (selectedOptions.contains("Blue 1") && selectedOptions.contains("Blue 2") && selectedOptions.contains("Bright")) {
            setTheme(R.style.Theme_StaffsHaven_BlueBright)
            selectedTheme = R.style.Theme_StaffsHaven_BlueBright
        }
        if (selectedOptions.contains("Blue 1") && selectedOptions.contains("Blue 2") && selectedOptions.contains("Pastel")) {
            setTheme(R.style.Theme_StaffsHaven_BluePastel)
            selectedTheme = R.style.Theme_StaffsHaven_BluePastel
        }
        if (selectedOptions.contains("Pink 1") && selectedOptions.contains("Pink 2") && selectedOptions.contains("Dark")) {
            setTheme(R.style.Theme_StaffsHaven_PinkDark)
            selectedTheme = R.style.Theme_StaffsHaven_PinkDark
        }
        if (selectedOptions.contains("Pink 1") && selectedOptions.contains("Pink 2") && selectedOptions.contains("Bright")) {
            setTheme(R.style.Theme_StaffsHaven_PinkBright)
            selectedTheme = R.style.Theme_StaffsHaven_PinkBright
        }

        if (selectedOptions.contains("Pink 1") && selectedOptions.contains("Pink 2") && selectedOptions.contains("Pastel")) {
            setTheme(R.style.Theme_StaffsHaven_PinkPastel)
            selectedTheme = R.style.Theme_StaffsHaven_PinkPastel
        }
        if (selectedOptions.contains("Green 1") && selectedOptions.contains("Green 2") && selectedOptions.contains("Dark")) {
            setTheme(R.style.Theme_StaffsHaven_GreenDark)
            selectedTheme = R.style.Theme_StaffsHaven_GreenDark
        }
        if (selectedOptions.contains("Green 1") && selectedOptions.contains("Green 2") && selectedOptions.contains("Bright")) {
            setTheme(R.style.Theme_StaffsHaven_GreenBright)
            selectedTheme = R.style.Theme_StaffsHaven_GreenBright
        }
        if (selectedOptions.contains("Green 1") && selectedOptions.contains("Green 2") && selectedOptions.contains("Pastel")) {
            setTheme(R.style.Theme_StaffsHaven_GreenPastel)
            selectedTheme = R.style.Theme_StaffsHaven_GreenPastel
        }
        if (selectedOptions.contains("Orange 1") && selectedOptions.contains("Orange 2") && selectedOptions.contains("Dark")) {
            setTheme(R.style.Theme_StaffsHaven_OrangeDark)
            selectedTheme = R.style.Theme_StaffsHaven_OrangeDark
        }

        if (selectedOptions.contains("Orange 1") && selectedOptions.contains("Orange 2") && selectedOptions.contains("Bright")) {
            setTheme(R.style.Theme_StaffsHaven_OrangeBright)
            selectedTheme = R.style.Theme_StaffsHaven_OrangeBright
        }
        if (selectedOptions.contains("Orange 1") && selectedOptions.contains("Orange 2") && selectedOptions.contains("Pastel")) {
            setTheme(R.style.Theme_StaffsHaven_OrangePastel)
            selectedTheme = R.style.Theme_StaffsHaven_OrangePastel
        }
        if (selectedOptions.contains("Grey 1") && selectedOptions.contains("Grey 2") && selectedOptions.contains("Dark")) {
            setTheme(R.style.Theme_StaffsHaven_GreyDark)
            selectedTheme = R.style.Theme_StaffsHaven_GreyDark
        }
        if (selectedOptions.contains("Grey 1") && selectedOptions.contains("Grey 2") && selectedOptions.contains("Bright")) {
            setTheme(R.style.Theme_StaffsHaven_GreyBright)
            selectedTheme = R.style.Theme_StaffsHaven_GreyBright
        }
        if (selectedOptions.contains("Grey 1") && selectedOptions.contains("Grey 2") && selectedOptions.contains("Pastel")) {
            setTheme(R.style.Theme_StaffsHaven_GreyPastel)
            selectedTheme = R.style.Theme_StaffsHaven_GreyPastel
        }

        if (selectedOptions.contains("Blue 1") && selectedOptions.contains("Pink 2") && selectedOptions.contains("Dark")) {
            setTheme(R.style.Theme_StaffsHaven_BluePinkDark)
            selectedTheme = R.style.Theme_StaffsHaven_BluePinkDark
        }
        if (selectedOptions.contains("Blue 1") && selectedOptions.contains("Pink 2") && selectedOptions.contains("Bright")) {
            setTheme(R.style.Theme_StaffsHaven_BluePinkBright)
            selectedTheme = R.style.Theme_StaffsHaven_BluePinkBright
        }
        if (selectedOptions.contains("Blue 1") && selectedOptions.contains("Pink 2") && selectedOptions.contains("Pastel")) {
            setTheme(R.style.Theme_StaffsHaven_BluePinkPastel)
            selectedTheme = R.style.Theme_StaffsHaven_BluePinkPastel
        }
        if (selectedOptions.contains("Blue 1") && selectedOptions.contains("Green 2") && selectedOptions.contains("Dark")) {
            setTheme(R.style.Theme_StaffsHaven_BlueGreenDark)
            selectedTheme = R.style.Theme_StaffsHaven_BlueGreenDark
        }
        if (selectedOptions.contains("Blue 1") && selectedOptions.contains("Green 2") && selectedOptions.contains("Bright")) {
            setTheme(R.style.Theme_StaffsHaven_BlueGreenBright)
            selectedTheme = R.style.Theme_StaffsHaven_BlueGreenBright
        }

        if (selectedOptions.contains("Blue 1") && selectedOptions.contains("Green 2") && selectedOptions.contains("Pastel")) {
            setTheme(R.style.Theme_StaffsHaven_BlueGreenPastel)
            selectedTheme = R.style.Theme_StaffsHaven_BlueGreenPastel
        }
        if (selectedOptions.contains("Blue 1") && selectedOptions.contains("Orange 2") && selectedOptions.contains("Dark")) {
            setTheme(R.style.Theme_StaffsHaven_BlueOrangeDark)
            selectedTheme = R.style.Theme_StaffsHaven_BlueOrangeDark
        }
        if (selectedOptions.contains("Blue 1") && selectedOptions.contains("Orange 2") && selectedOptions.contains("Bright")) {
            setTheme(R.style.Theme_StaffsHaven_BlueOrangeBright)
            selectedTheme = R.style.Theme_StaffsHaven_BlueOrangeBright
        }
        if (selectedOptions.contains("Blue 1") && selectedOptions.contains("Orange 2") && selectedOptions.contains("Pastel")) {
            setTheme(R.style.Theme_StaffsHaven_BlueOrangePastel)
            selectedTheme = R.style.Theme_StaffsHaven_BlueOrangePastel
        }
        if (selectedOptions.contains("Blue 1") && selectedOptions.contains("Grey 2") && selectedOptions.contains("Dark")) {
            setTheme(R.style.Theme_StaffsHaven_BlueGreyDark)
            selectedTheme = R.style.Theme_StaffsHaven_BlueGreyDark
        }

        if (selectedOptions.contains("Blue 1") && selectedOptions.contains("Grey 2") && selectedOptions.contains("Bright")) {
            setTheme(R.style.Theme_StaffsHaven_BlueGreyBright)
            selectedTheme = R.style.Theme_StaffsHaven_BlueGreyBright
        }
        if (selectedOptions.contains("Blue 1") && selectedOptions.contains("Grey 2") && selectedOptions.contains("Pastel")) {
            setTheme(R.style.Theme_StaffsHaven_BlueGreyPastel)
            selectedTheme = R.style.Theme_StaffsHaven_BlueGreyPastel
        }
        if (selectedOptions.contains("Pink 1") && selectedOptions.contains("Blue 2") && selectedOptions.contains("Dark")) {
            setTheme(R.style.Theme_StaffsHaven_PinkBlueDark)
            selectedTheme = R.style.Theme_StaffsHaven_PinkBlueDark
        }
        if (selectedOptions.contains("Pink 1") && selectedOptions.contains("Blue 2") && selectedOptions.contains("Bright")) {
            setTheme(R.style.Theme_StaffsHaven_PinkBlueBright)
            selectedTheme = R.style.Theme_StaffsHaven_PinkBlueBright
        }
        if (selectedOptions.contains("Pink 1") && selectedOptions.contains("Blue 2") && selectedOptions.contains("Pastel")) {
            setTheme(R.style.Theme_StaffsHaven_PinkBluePastel)
            selectedTheme = R.style.Theme_StaffsHaven_PinkBluePastel
        }

        if (selectedOptions.contains("Pink 1") && selectedOptions.contains("Green 2") && selectedOptions.contains("Dark")) {
            setTheme(R.style.Theme_StaffsHaven_PinkGreenDark)
            selectedTheme = R.style.Theme_StaffsHaven_PinkGreenDark
        }
        if (selectedOptions.contains("Pink 1") && selectedOptions.contains("Green 2") && selectedOptions.contains("Bright")) {
            setTheme(R.style.Theme_StaffsHaven_PinkGreenBright)
            selectedTheme = R.style.Theme_StaffsHaven_PinkGreenBright
        }
        if (selectedOptions.contains("Pink 1") && selectedOptions.contains("Green 2") && selectedOptions.contains("Pastel")){
            setTheme(R.style.Theme_StaffsHaven_PinkGreenPastel)
            selectedTheme = R.style.Theme_StaffsHaven_PinkGreenPastel
        }
        if (selectedOptions.contains("Pink 1") && selectedOptions.contains("Orange 2") && selectedOptions.contains("Dark")) {
            setTheme(R.style.Theme_StaffsHaven_PinkOrangeDark)
            selectedTheme = R.style.Theme_StaffsHaven_PinkOrangeDark
        }
        if (selectedOptions.contains("Pink 1") && selectedOptions.contains("Orange 2") && selectedOptions.contains("Bright")) {
            setTheme(R.style.Theme_StaffsHaven_PinkOrangeBright)
            selectedTheme = R.style.Theme_StaffsHaven_PinkOrangeBright
        }

        if (selectedOptions.contains("Pink 1") && selectedOptions.contains("Orange 2") && selectedOptions.contains("Pastel")) {
            setTheme(R.style.Theme_StaffsHaven_PinkOrangePastel)
            selectedTheme = R.style.Theme_StaffsHaven_PinkOrangePastel
        }
        if (selectedOptions.contains("Pink 1") && selectedOptions.contains("Grey 2") && selectedOptions.contains("Dark")) {
            setTheme(R.style.Theme_StaffsHaven_PinkGreyDark)
            selectedTheme = R.style.Theme_StaffsHaven_PinkGreyDark
        }
        if (selectedOptions.contains("Pink 1") && selectedOptions.contains("Grey 2") && selectedOptions.contains("Bright")) {
            setTheme(R.style.Theme_StaffsHaven_PinkGreyBright)
            selectedTheme = R.style.Theme_StaffsHaven_PinkGreyBright
        }
        if (selectedOptions.contains("Pink 1") && selectedOptions.contains("Grey 2") && selectedOptions.contains("Pastel")) {
            setTheme(R.style.Theme_StaffsHaven_PinkGreyPastel)
            selectedTheme = R.style.Theme_StaffsHaven_PinkGreyPastel
        }
        if (selectedOptions.contains("Green 1") && selectedOptions.contains("Blue 2") && selectedOptions.contains("Dark")) {
            setTheme(R.style.Theme_StaffsHaven_GreenBlueDark)
            selectedTheme = R.style.Theme_StaffsHaven_GreenBlueDark
        }

        if (selectedOptions.contains("Green 1") && selectedOptions.contains("Blue 2") && selectedOptions.contains("Bright")) {
            setTheme(R.style.Theme_StaffsHaven_GreenBlueBright)
            selectedTheme = R.style.Theme_StaffsHaven_GreenBlueBright
        }
        if (selectedOptions.contains("Green 1") && selectedOptions.contains("Blue 2") && selectedOptions.contains("Pastel")) {
            setTheme(R.style.Theme_StaffsHaven_GreenBluePastel)
            selectedTheme = R.style.Theme_StaffsHaven_GreenBluePastel
        }
        if (selectedOptions.contains("Green 1") && selectedOptions.contains("Pink 2") && selectedOptions.contains("Dark")) {
            setTheme(R.style.Theme_StaffsHaven_GreenPinkDark)
            selectedTheme = R.style.Theme_StaffsHaven_GreenPinkDark
        }
        if (selectedOptions.contains("Green 1") && selectedOptions.contains("Pink 2") && selectedOptions.contains("Bright")) {
            setTheme(R.style.Theme_StaffsHaven_GreenPinkBright)
            selectedTheme = R.style.Theme_StaffsHaven_GreenPinkBright
        }
        if (selectedOptions.contains("Green 1") && selectedOptions.contains("Pink 2") && selectedOptions.contains("Pastel")){
            setTheme(R.style.Theme_StaffsHaven_GreenPinkPastel)
            selectedTheme = R.style.Theme_StaffsHaven_GreenPinkPastel
        }

        if (selectedOptions.contains("Green 1") && selectedOptions.contains("Orange 2") && selectedOptions.contains("Dark")) {
            setTheme(R.style.Theme_StaffsHaven_GreenOrangeDark)
            selectedTheme = R.style.Theme_StaffsHaven_GreenOrangeDark
        }
        if (selectedOptions.contains("Green 1") && selectedOptions.contains("Orange 2") && selectedOptions.contains("Bright")) {
            setTheme(R.style.Theme_StaffsHaven_GreenOrangeBright)
            selectedTheme = R.style.Theme_StaffsHaven_GreenOrangeBright
        }
        if (selectedOptions.contains("Green 1") && selectedOptions.contains("Orange 2") && selectedOptions.contains("Pastel")) {
            setTheme(R.style.Theme_StaffsHaven_GreenOrangePastel)
            selectedTheme = R.style.Theme_StaffsHaven_GreenOrangePastel
        }
        if (selectedOptions.contains("Green 1") && selectedOptions.contains("Grey 2") && selectedOptions.contains("Dark")) {
            setTheme(R.style.Theme_StaffsHaven_GreenGreyDark)
            selectedTheme = R.style.Theme_StaffsHaven_GreenGreyDark
        }
        if (selectedOptions.contains("Green 1") && selectedOptions.contains("Grey 2") && selectedOptions.contains("Bright")) {
            setTheme(R.style.Theme_StaffsHaven_GreenGreyBright)
            selectedTheme = R.style.Theme_StaffsHaven_GreenGreyBright
        }

        if (selectedOptions.contains("Green 1") && selectedOptions.contains("Grey 2") && selectedOptions.contains("Pastel")) {
            setTheme(R.style.Theme_StaffsHaven_GreenGreyPastel)
            selectedTheme = R.style.Theme_StaffsHaven_GreenGreyPastel
        }
        if (selectedOptions.contains("Orange 1") && selectedOptions.contains("Blue 2") && selectedOptions.contains("Dark")) {
            setTheme(R.style.Theme_StaffsHaven_OrangeBlueDark)
            selectedTheme = R.style.Theme_StaffsHaven_OrangeBlueDark
        }
        if (selectedOptions.contains("Orange 1") && selectedOptions.contains("Blue 2") && selectedOptions.contains("Bright")) {
            setTheme(R.style.Theme_StaffsHaven_OrangeBlueBright)
            selectedTheme = R.style.Theme_StaffsHaven_OrangeBlueBright
        }
        if (selectedOptions.contains("Orange 1") && selectedOptions.contains("Blue 2") && selectedOptions.contains("Pastel")) {
            setTheme(R.style.Theme_StaffsHaven_OrangeBluePastel)
            selectedTheme = R.style.Theme_StaffsHaven_OrangeBluePastel
        }
        if (selectedOptions.contains("Orange 1") && selectedOptions.contains("Pink 2") && selectedOptions.contains("Dark")) {
            setTheme(R.style.Theme_StaffsHaven_OrangePinkDark)
            selectedTheme = R.style.Theme_StaffsHaven_OrangePinkDark
        }

        if (selectedOptions.contains("Orange 1") && selectedOptions.contains("Pink 2") && selectedOptions.contains("Bright")) {
            setTheme(R.style.Theme_StaffsHaven_OrangePinkBright)
            selectedTheme = R.style.Theme_StaffsHaven_OrangePinkBright
        }
        if (selectedOptions.contains("Orange 1") && selectedOptions.contains("Pink 2") && selectedOptions.contains("Pastel")) {
            setTheme(R.style.Theme_StaffsHaven_OrangePinkPastel)
            selectedTheme = R.style.Theme_StaffsHaven_OrangePinkPastel
        }
        if (selectedOptions.contains("Orange 1") && selectedOptions.contains("Green 2") && selectedOptions.contains("Dark")) {
            setTheme(R.style.Theme_StaffsHaven_OrangeGreenDark)
            selectedTheme = R.style.Theme_StaffsHaven_OrangeGreenDark
        }
        if (selectedOptions.contains("Orange 1") && selectedOptions.contains("Green 2") && selectedOptions.contains("Bright")) {
            setTheme(R.style.Theme_StaffsHaven_OrangeGreenBright)
            selectedTheme = R.style.Theme_StaffsHaven_OrangeGreenBright
        }
        if (selectedOptions.contains("Orange 1") && selectedOptions.contains("Green 2") && selectedOptions.contains("Pastel")) {
            setTheme(R.style.Theme_StaffsHaven_OrangeGreenPastel)
            selectedTheme = R.style.Theme_StaffsHaven_OrangeGreenPastel
        }

        if (selectedOptions.contains("Orange 1") && selectedOptions.contains("Grey 2") && selectedOptions.contains("Dark")) {
            setTheme(R.style.Theme_StaffsHaven_OrangeGreyDark)
            selectedTheme = R.style.Theme_StaffsHaven_OrangeGreyDark
        }
        if (selectedOptions.contains("Orange 1") && selectedOptions.contains("Grey 2") && selectedOptions.contains("Bright")) {
            setTheme(R.style.Theme_StaffsHaven_OrangeGreyBright)
            selectedTheme = R.style.Theme_StaffsHaven_OrangeGreyBright
        }
        if (selectedOptions.contains("Orange 1") && selectedOptions.contains("Grey 2") && selectedOptions.contains("Pastel")) {
            setTheme(R.style.Theme_StaffsHaven_OrangeGreyPastel)
            selectedTheme = R.style.Theme_StaffsHaven_OrangeGreyPastel
        }
        if (selectedOptions.contains("Grey 1") && selectedOptions.contains("Blue 2") && selectedOptions.contains("Dark")) {
            setTheme(R.style.Theme_StaffsHaven_GreyBlueDark)
            selectedTheme = R.style.Theme_StaffsHaven_GreyBlueDark
        }
        if (selectedOptions.contains("Grey 1") && selectedOptions.contains("Blue 2") && selectedOptions.contains("Bright")) {
            setTheme(R.style.Theme_StaffsHaven_GreyBlueBright)
            selectedTheme = R.style.Theme_StaffsHaven_GreyBlueBright
        }

        if (selectedOptions.contains("Grey 1") && selectedOptions.contains("Blue 2") && selectedOptions.contains("Pastel")) {
            setTheme(R.style.Theme_StaffsHaven_GreyBluePastel)
            selectedTheme = R.style.Theme_StaffsHaven_GreyBluePastel
        }
        if (selectedOptions.contains("Grey 1") && selectedOptions.contains("Pink 2") && selectedOptions.contains("Dark")) {
            setTheme(R.style.Theme_StaffsHaven_GreyPinkDark)
            selectedTheme = R.style.Theme_StaffsHaven_GreyPinkDark
        }
        if (selectedOptions.contains("Grey 1") && selectedOptions.contains("Pink 2") && selectedOptions.contains("Bright")) {
            setTheme(R.style.Theme_StaffsHaven_GreyPinkBright)
            selectedTheme = R.style.Theme_StaffsHaven_GreyPinkBright
        }
        if (selectedOptions.contains("Grey 1") && selectedOptions.contains("Pink 2") && selectedOptions.contains("Pastel")) {
            setTheme(R.style.Theme_StaffsHaven_GreyPinkPastel)
            selectedTheme = R.style.Theme_StaffsHaven_GreyPinkPastel
        }
        if (selectedOptions.contains("Grey 1") && selectedOptions.contains("Green 2") && selectedOptions.contains("Dark")) {
            setTheme(R.style.Theme_StaffsHaven_GreyGreenDark)
            selectedTheme = R.style.Theme_StaffsHaven_GreyGreenDark
        }

        if (selectedOptions.contains("Grey 1") && selectedOptions.contains("Green 2") && selectedOptions.contains("Bright")) {
            setTheme(R.style.Theme_StaffsHaven_GreyGreenBright)
            selectedTheme = R.style.Theme_StaffsHaven_GreyGreenBright
        }
        if (selectedOptions.contains("Grey 1") && selectedOptions.contains("Green 2") && selectedOptions.contains("Pastel")) {
            setTheme(R.style.Theme_StaffsHaven_GreyGreenPastel)
            selectedTheme = R.style.Theme_StaffsHaven_GreyGreenPastel
        }
        if (selectedOptions.contains("Grey 1") && selectedOptions.contains("Orange 2") && selectedOptions.contains("Dark")) {
            setTheme(R.style.Theme_StaffsHaven_GreyOrangeDark)
            selectedTheme = R.style.Theme_StaffsHaven_GreyOrangeDark
        }
        if (selectedOptions.contains("Grey 1") && selectedOptions.contains("Orange 2") && selectedOptions.contains("Bright")) {
            setTheme(R.style.Theme_StaffsHaven_GreyOrangeBright)
            selectedTheme = R.style.Theme_StaffsHaven_GreyOrangeBright
        }
        if (selectedOptions.contains("Grey 1") && selectedOptions.contains("Orange 2") && selectedOptions.contains("Pastel")) {
            setTheme(R.style.Theme_StaffsHaven_GreyOrangePastel)
            selectedTheme = R.style.Theme_StaffsHaven_GreyOrangePastel
        }

        if (selectedOptions.contains("Click")) {
            selectedNav = "Click"
        }
        if (selectedOptions.contains("Swipe")) {
            selectedNav = "Swipe"
        }
        if (selectedOptions.contains("Slide")) {
            selectedNav = "Slide"
        }


        val sharedPrefs = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val editor = sharedPrefs.edit()
        editor.putInt("selectedTheme", selectedTheme)
        editor.putString("selectedNav", selectedNav)
        editor.apply()

        // store the theme in Firestore
        val db = Firebase.firestore
        val user = Firebase.auth.currentUser
        if (user != null) {
            val userEmail = user.email
            val userData = hashMapOf(
                "selectedTheme" to selectedTheme,
                "selectedNav" to selectedNav
            )
            db.collection("userData").document(userEmail!!) // Use email as document ID
                .set(userData)
                .addOnSuccessListener {
                    Toast.makeText(this, "Perferences saved!", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Failure saving preferences", Toast.LENGTH_SHORT).show()
                }
        }

        recreate()

        val intent = Intent(this, Login::class.java)
        startActivity(intent)
        finish()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //setContentView(R.layout.activity_full_questionnaire)
        binding = ActivityFullQuestionnaireBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.fullQuestActivity) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        replaceFragment(Questionnaire())
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fullQuestActivity,fragment)
        fragmentTransaction.commit()
    }


}