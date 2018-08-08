package com.okeydokey.board

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SignOnActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_on)

        val signOn = findViewById<Button>(R.id.sign_on)

        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.password)

        signOn.setOnClickListener {

            val exists = users.any { user -> user.email == email.text.toString() }

            if(exists) {
                Toast.makeText(applicationContext, "이미 가입된 이메일 입니다.", Toast.LENGTH_LONG).show()
            } else {
                var no = (users.size + 1).toLong()
                val new = User(no
                        , email.text.toString()
                        , password.text.toString()
                )

                users.add(new)

                intent = Intent(this, SignInActivity::class.java)
                intent.putExtra("email", email.text.toString())
                startActivity(intent);
            }

        }
    }
}
