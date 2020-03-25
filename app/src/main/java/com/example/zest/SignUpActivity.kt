package com.example.zest

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_signup.*

class SignUpActivity:AppCompatActivity (){

    private lateinit var dbHelper: DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        dbHelper = DatabaseHelper(this)
        var btn_cancel = findViewById(R.id.btn_cancel) as Button
        //clears the email and password

        btn_submit.setOnClickListener{
            val email = et_email_signUp.text.toString().trim()
            val password = et_password_signUp.text.toString().trim()


            when{
                email.isEmpty()->{
                    et_email_signUp.error="Email Required!"
                    et_email_signUp.requestFocus()
                    return@setOnClickListener
                }
                password.isEmpty()->{
                    et_password_signUp.error="Password Required!"
                    et_password_signUp.requestFocus()
                    return@setOnClickListener
                }

                dbHelper.userPresents(email,password)->{
                    et_email_signUp.error = "This email is already exist."
                    et_email_signUp.requestFocus()
                    return@setOnClickListener
                }

                else ->{
                    dbHelper.insertUserData(et_email_signUp.text.toString(),et_password_signUp.text.toString())
                    }
                }
        }

        //cancel the registration
        btn_cancel.setOnClickListener{
            val et_email = findViewById(R.id.et_email_signUp)as EditText
            val et_password = findViewById(R.id.et_password_signUp)as EditText
           et_email.setText("")
           et_password.setText("")
        }


        login_sigUp.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
