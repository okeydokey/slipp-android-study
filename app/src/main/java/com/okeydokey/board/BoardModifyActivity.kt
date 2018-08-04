package com.okeydokey.board

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class BoardModifyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_modify)

        val no = intent.getLongExtra("board", 1)
        var category = findViewById<TextView>(R.id.category)
        val categoryNo = intent.getLongExtra("category", 1)
        category.text = categories[categoryNo.toInt() - 1].name
        val save = findViewById<Button>(R.id.register)

        val board = boards[no.toInt() - 1]

        val title = findViewById<EditText>(R.id.title)
        val content = findViewById<EditText>(R.id.content)

        title.setText(board.title)
        content.setText(board.content)

        save.setOnClickListener { it ->

            val new = Board(no
                    , categories[categoryNo.toInt() - 1]
                    , title.text.toString()
                    , content.text.toString()
                    , "작성자"
                    , SimpleDateFormat("yyyy.MM.dd").format(Date())
                    , "")

            boards.add(no.toInt() - 1, new)

            intent = Intent(this, BoardDetailActivity::class.java)
            intent.putExtra("board", no)
            startActivity(intent);
        }
    }
}
