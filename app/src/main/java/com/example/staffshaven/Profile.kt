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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class Profile : AppCompatActivity() {
    private lateinit var buttonLogout : Button
    private lateinit var buttonGoBack : Button
    private lateinit var auth: FirebaseAuth
    private lateinit var textView: TextView
    private lateinit var user: FirebaseUser

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
        user = auth.currentUser!!

        // Display the user's email
        if(user != null){
            textView.text = user.email
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