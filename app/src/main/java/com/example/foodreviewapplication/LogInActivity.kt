package com.example.foodreviewapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class LogInActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    lateinit var email: EditText
    lateinit var password: EditText
    lateinit var login: Button
    lateinit var register: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        auth = FirebaseAuth.getInstance()
        checkLogin()
        findViews()
        goToSignUp()
        login()
    }

    private fun checkLogin() {
        if (auth.currentUser != null) {
            startActivity(Intent(this@LogInActivity, MenuActivity::class.java))
            finish()
        }


    }

    private fun findViews() {
        email = findViewById(R.id.editEmail)
        password = findViewById(R.id.editPassword)
        login = findViewById(R.id.sign_in_button)
        register = findViewById(R.id.go_to_sign_up)

        //make register clickable
        register.setOnClickListener {
            goToSignUp()
        }

        login.setOnClickListener {
            login()
        }
    }

    private fun login() {
        val email = email.text.toString()
        val password = password.text.toString()
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            return
        }
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this@LogInActivity, MenuActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show()
                }
            }
    }

    //go to sign up activity

    private fun goToSignUp() {
        register.setOnClickListener {
            startActivity(Intent(this@LogInActivity, SignUpActivity::class.java))
            finish()
        }
    }
}

