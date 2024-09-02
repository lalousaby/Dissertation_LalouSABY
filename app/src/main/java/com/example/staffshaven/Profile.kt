package com.example.staffshaven

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.firestore
import org.w3c.dom.Text

class Profile : AppCompatActivity() {
    private lateinit var buttonLogout : Button
    private lateinit var buttonGoBack : Button
    private lateinit var auth: FirebaseAuth
    private lateinit var textView: TextView
    private lateinit var user: FirebaseUser
    private lateinit var settings: TextView
    private lateinit var settings2: TextView
    private lateinit var settings3: TextView


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        val sharedPrefs= getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val selectedTheme = sharedPrefs.getInt("selectedTheme", R.style.Base_Theme_StaffsHaven) // Default theme
        setTheme(selectedTheme)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        auth = FirebaseAuth.getInstance()
        buttonLogout = findViewById(R.id.logout)
        buttonGoBack = findViewById(R.id.goBack)
        textView = findViewById(R.id.userDetails)
        settings = findViewById(R.id.userSettings)
        settings2 = findViewById(R.id.userSettings2)
        settings3 = findViewById(R.id.userSettings3)
        user = auth.currentUser!!
        val db = Firebase.firestore
        val userEmail = user.email

        // Display the user's email
        if(user != null){
            if (userEmail != null) {
                db.collection("userData").document(userEmail)
                    .get()
                    .addOnSuccessListener { document ->
                        if (document != null && document.exists()) {
                            textView.text = user.email

                            val selectedContent = document.getString("selectedContent")
                            val selectedNav = document.getString("selectedNav")
                            val type = document.getString("selectedType")

                            settings.text = "$selectedContent"
                            settings2.text = "$selectedNav"
                            settings3.text = "$type"
                        }
                    }
            }

        }else{
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        }

        // Logout
        buttonLogout.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        }
        buttonGoBack.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}