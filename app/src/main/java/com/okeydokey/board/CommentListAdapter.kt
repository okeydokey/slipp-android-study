package com.okeydokey.board

import android.content.Context
import android.content.Intent
import android.content.Intent.getIntentOld
import android.graphics.BitmapFactory
import android.os.StrictMode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import java.net.URL


data class Comment(val no: Long, val board: Board, val comment: String, val register: String, val registered: String)
data class CommentListViewHolder(val register: TextView, val comment: TextView, val registered: TextView)


var comments = arrayListOf(
        Comment( 1, boards[0],"댓글1", "김윤희", "2018.08.01")
        , Comment( 2, boards[0],"댓글2", "김윤희", "2018.08.02")
        , Comment( 3, boards[1],"댓글3", "김윤희", "2018.08.03")
        , Comment( 4, boards[2],"댓글4", "김윤희", "2018.08.04")
        , Comment( 5, boards[2],"댓글5", "김윤희", "2018.08.05")
        , Comment( 6, boards[3],"댓글6", "김윤희", "2018.08.06")
        , Comment( 7, boards[3],"댓글7", "김윤희", "2018.08.07")
        , Comment( 8, boards[3],"댓글8", "김윤희", "2018.08.08")
        , Comment( 9, boards[4],"댓글9", "김윤희", "2018.08.09")
        , Comment( 10, boards[4],"댓글10", "김윤희", "2018.08.10")
)

class CommentListAdapter : BaseAdapter {

    private val mContext: Context
    private val intent: Intent
    private val commentsByBoard: List<Comment>

    constructor(mContext: Context, intent: Intent) : super() {
        this.mContext = mContext
        this.intent = intent
        this.commentsByBoard = comments.filter { comment -> comment.board.no == intent.getLongExtra("board",1) }
    }

    override fun getCount(): Int = commentsByBoard.size

    override fun getItem(position: Int): Any? = commentsByBoard[position]

    override fun getItemId(position: Int): Long = commentsByBoard[position].no

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val view : View

        if (convertView == null) {
            val inflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.comment_list_item, null, false)
        } else {
            view = convertView
        }

        val commentViewHolder = CommentListViewHolder(
                view.findViewById(R.id.register)
                , view.findViewById(R.id.comment)
                , view.findViewById(R.id.registered)

        )

        view.tag = commentViewHolder

        val register = (view.tag as CommentListViewHolder).register
        val comment = (view.tag as CommentListViewHolder).comment
        val registered = (view.tag as CommentListViewHolder).registered

        val currentComment = commentsByBoard[position]

        register.text = currentComment.register
        comment.text = currentComment.comment
        registered.text = currentComment.registered

        return view
    }
}



