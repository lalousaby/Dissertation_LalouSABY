package com.example.staffshaven

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class Login : AppCompatActivity() {
    private lateinit var editTextEmail : TextInputEditText
    private lateinit var editTextPassword: TextInputEditText
    private lateinit var buttonLogin : Button
    private lateinit var auth: FirebaseAuth
    private lateinit var checkAnimationView : LottieAnimationView
    private lateinit var textView: TextView
    private lateinit var showHideBtn: ImageButton

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        auth = FirebaseAuth.getInstance()
        editTextEmail = findViewById(R.id.emailLogin)
        editTextPassword = findViewById(R.id.passwordLogin)
        buttonLogin = findViewById(R.id.btnLogin)
        checkAnimationView = findViewById(R.id.checkAnimationView)
        textView = findViewById(R.id.registerNow)
        showHideBtn = findViewById(R.id.showHideBtn)

        textView.setOnClickListener{
            val intent = Intent(this, Registration::class.java)
            startActivity(intent)
            finish()
        }

        val visibilityOff = getDrawable(R.drawable.visibility_off)
        val visibilityOn = getDrawable(R.drawable.visibility)

        editTextPassword.transformationMethod = PasswordTransformationMethod.getInstance()

        // Show or hide password
        showHideBtn.setOnClickListener {
            val currentImage = showHideBtn.drawable
            if(currentImage === visibilityOff){
                editTextPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                showHideBtn.setImageDrawable(visibilityOn)
            } else{
                editTextPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
                showHideBtn.setImageDrawable(visibilityOff)
            }
        }

        buttonLogin.setOnClickListener{
            val email : String = editTextEmail.getText().toString()
            val password : String = editTextPassword.getText().toString()
            checkAnimationView.visibility = View.VISIBLE

            if(TextUtils.isEmpty(email)){
                Toast.makeText(this, "Enter email", Toast.LENGTH_SHORT).show()
            }
            if(TextUtils.isEmpty(password)){
                Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT).show()
            }

            // Authentication with Firebase
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    checkAnimationView.visibility = View.GONE
                    if (task.isSuccessful) {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("signInWithEmail:failure", task.exception)
                        Toast.makeText(
                            this,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                }
        }

    }

}