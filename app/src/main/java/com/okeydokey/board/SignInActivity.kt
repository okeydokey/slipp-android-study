package com.okeydokey.board

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sign_in.view.*

data class User(val no: Long, val email: String, val password: String)

var isSignIn = false

var users = arrayListOf(
        User( 1, "user01@google.com","1234")
        , User( 2, "user02@google.com","1111")
)

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val signIn = findViewById<Button>(R.id.sign_in)

        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.password)

        signIn.setOnClickListener {

            val exists = users.any { user -> user.email == email.text.toString() && user.password == password.text.toString() }

            if(exists) {
                isSignIn = true
                intent = Intent(this, CategoryTabActivity::class.java)
                startActivity(intent);
            } else {
                Toast.makeText(applicationContext, "아이디가 없거나 패스워드가 틀렸습니다.", Toast.LENGTH_LONG).show()
            }

        }

    }
}
