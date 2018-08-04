package com.okeydokey.board

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.AdapterView
import android.widget.ListView

class BoardListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.board_list)

        val listView: ListView = findViewById(R.id.board_list)
        listView.adapter = BoardListAdapter(this, getIntent())

        listView.onItemClickListener =
                AdapterView.OnItemClickListener { parent, v, position, id ->
                    intent = Intent(this, BoardListActivity::class.java)
                    startActivity(intent);
                }
    }
}

