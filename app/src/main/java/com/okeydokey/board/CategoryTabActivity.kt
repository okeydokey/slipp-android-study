package com.okeydokey.board

import android.app.ActivityGroup
import android.content.Intent
import android.os.Bundle
import android.widget.TabHost

class CategoryTabActivity : ActivityGroup() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.board_category_tab)

        val host : TabHost = findViewById(R.id.category_tab)
        host.setup(this.getLocalActivityManager())

        for ((index, value) in categories.withIndex()) {
            val spec = host.newTabSpec(value.name)
            spec.setIndicator(value.name)
            val intent = Intent(this, BoardListActivity::class.java)
            intent.putExtra("category", value.no)
            spec.setContent(intent)
            host.addTab(spec)
        }
    }
}