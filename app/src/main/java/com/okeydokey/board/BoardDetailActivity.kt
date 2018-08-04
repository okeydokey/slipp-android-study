package com.okeydokey.board

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.StrictMode
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import java.net.URL

class BoardDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_detail)

        val board = boards[intent.getLongExtra("board",1).toInt() - 1]

        val categoryName: TextView = findViewById(R.id.board_category_name)
        val boardTitle: TextView = findViewById(R.id.board_title)
        val boardRegister: TextView = findViewById(R.id.board_register)
        val boardRegistered: TextView = findViewById(R.id.board_registered)
        val boardImage: ImageView = findViewById(R.id.board_image)
        val boardContent: TextView = findViewById(R.id.board_content)

        categoryName.text = board.category.name
        boardTitle.text = board.title
        boardRegister.text = board.register
        boardRegistered.text = board.registered

        if(board.image?.isNotEmpty()) {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            boardImage.setImageBitmap(BitmapFactory.decodeStream(URL(board.image).openConnection().getInputStream()));
        }

        boardContent.text = board.content

        val modify = findViewById<Button>(R.id.modify)

        modify.setOnClickListener { it ->

            intent = Intent(this, BoardModifyActivity::class.java)
            intent.putExtra("board", board.no)
            intent.putExtra("category", board.category.no)
            startActivity(intent);
        }
    }
}
