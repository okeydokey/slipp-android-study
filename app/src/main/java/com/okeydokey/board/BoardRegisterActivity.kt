package com.okeydokey.board

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class BoardRegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_register)

        var category = findViewById<TextView>(R.id.category)
        val categoryNo = intent.getLongExtra("category", 1)
        category.text = categories[categoryNo.toInt() - 1].name
        val save = findViewById<Button>(R.id.register)

        save.setOnClickListener { it ->

            val title = findViewById<EditText>(R.id.title)
            val content = findViewById<EditText>(R.id.content)

            val new = Board((boards.size + 1).toLong()
                    , categories[categoryNo.toInt() - 1]
                    , title.text.toString()
                    , content.text.toString()
                    , "작성자"
                    , SimpleDateFormat("yyyy.MM.dd").format(Date())
                    , "")

            boards.add(new)

            intent = Intent(this, CategoryTabActivity::class.java)
            intent.putExtra("category", categoryNo)
            startActivity(intent);
        }
    }
}
