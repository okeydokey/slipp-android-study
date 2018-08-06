package com.okeydokey.board

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import java.text.SimpleDateFormat
import java.util.*

class BoardCommentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_comment)

        val listView: ListView = findViewById(R.id.comment_list)
        listView.adapter = CommentListAdapter(this, intent)

        val save = findViewById<Button>(R.id.comment_register)

        save.setOnClickListener {
            if(!isSignIn) {
                intent = Intent(this, SignInActivity::class.java)
                startActivity(intent);
                return@setOnClickListener
            }

            val comment = findViewById<EditText>(R.id.comment)

            var no = (comments.size + 1).toLong()
            val boardNo = intent.getLongExtra("board", 1)
            val new = Comment(no
                    , boards[boardNo.toInt() - 1]
                    , comment.text.toString()
                    , "작성자"
                    , SimpleDateFormat("yyyy.MM.dd").format(Date())
            )

            comments.add(new)

            intent = Intent(this, BoardCommentActivity::class.java)
            intent.putExtra("board", boardNo)
            startActivity(intent);
        }
    }
}
