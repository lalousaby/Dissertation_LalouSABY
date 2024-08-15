package com.example.staffshaven

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class Registration : AppCompatActivity() {
    private lateinit var editTextEmail : TextInputEditText
    private lateinit var editTextPassword: TextInputEditText
    private lateinit var buttonReg : Button
    private lateinit var auth: FirebaseAuth
    private lateinit var progressBar : ProgressBar
    private lateinit var textView: TextView

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
        setContentView(R.layout.activity_registration)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        auth = FirebaseAuth.getInstance()
        editTextEmail = findViewById(R.id.emailLogin)
        editTextPassword = findViewById(R.id.passwordLogin)
        buttonReg = findViewById(R.id.btnRegister)
        progressBar = findViewById(R.id.progressBar)
        textView = findViewById(R.id.loginNow)

        textView.setOnClickListener{
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        }

        buttonReg.setOnClickListener{
            val email : String = editTextEmail.getText().toString()
            val password : String = editTextPassword.getText().toString()
            progressBar.visibility = View.VISIBLE

            if(TextUtils.isEmpty(email)){
                Toast.makeText(this, "Enter email", Toast.LENGTH_SHORT).show()
            }
            if(TextUtils.isEmpty(password)){
                Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT).show()
            }

            // Retrieve the information entered by the user and open the session
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val intent = Intent(this, FullQuestionnaireActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("RegisterInWithEmail:failure", task.exception)
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