package com.example.foodreviewapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    //Activity to do sign up with Firebase
    private lateinit var auth: FirebaseAuth

    private lateinit var email: EditText
    lateinit var password: EditText
    lateinit var confirmPassword: EditText
    lateinit var signUp: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        auth = FirebaseAuth.getInstance()
        findViews()

    }
    //initialize views
    private fun findViews() {
        email = findViewById(R.id.editEmail)
        password = findViewById(R.id.editPassword)
        confirmPassword = findViewById(R.id.confirmPassword)
        signUp = findViewById(R.id.sign_up_button)

        signUp.setOnClickListener {
            signUp()
        }

    }

    //register user with firebase and save it to the database
    private fun signUp() {
        val email = email.text.toString()
        val password = password.text.toString()
        val confirmPassword = confirmPassword.text.toString()

        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            return
        }
        if (password != confirmPassword) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            return
        }
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Sign up successful", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@SignUpActivity, LogInActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Sign up failed", Toast.LENGTH_SHORT).show()
                }
            }
    }

}
