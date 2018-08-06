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


data class Board(val no: Long, val category: Category, val title: String, val content: String, val register: String, val registered: String, val image: String)
data class BoardListViewHolder(val categoryName: TextView, val title: TextView, val register: TextView, val registered: TextView, val image: ImageView)

var boards = arrayListOf(
        Board( 1, categories[0],"게시글1", "내용", "김윤희", "2018.08.01", "http://lh3.googleusercontent.com/-Vk3jWtpMBo0/AAAAAAAAAAI/AAAAAAAAAAA/AAnnY7oxEREwoWbk3yX5hg7lDeiZpB9-Zw/s64-c-mo/photo.jpg")
        , Board( 2, categories[0],"게시글2", "내용", "김윤희", "2018.08.02", "")
        , Board( 3, categories[1],"게시글3", "내용", "김윤희", "2018.08.03", "")
        , Board( 4, categories[3],"게시글4", "내용", "김윤희", "2018.08.04", "")
        , Board( 5, categories[4],"게시글5", "내용", "김윤희", "2018.08.05", "")
        , Board( 6, categories[4],"게시글6", "내용", "김윤희", "2018.08.06", "")
        , Board( 7, categories[4],"게시글7", "내용", "김윤희", "2018.08.07", "")
        , Board( 8, categories[4],"게시글8", "내용", "김윤희", "2018.08.08", "")
        , Board( 9, categories[4],"게시글9", "내용", "김윤희", "2018.08.09", "")
)

class BoardListAdapter : BaseAdapter {

    private val mContext: Context
    private val intent: Intent
    private val boardsByCategory: List<Board>

    constructor(mContext: Context, intent: Intent) : super() {
        this.mContext = mContext
        this.intent = intent
        this.boardsByCategory = boards.filter { board -> board.category.no == intent.getLongExtra("category",1) }
    }

    override fun getCount(): Int = boardsByCategory.size

    override fun getItem(position: Int): Any? = boardsByCategory[position]

    override fun getItemId(position: Int): Long = boardsByCategory[position].no

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val view : View

        if (convertView == null) {
            val inflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.board_list_item, null, false)
        } else {
            view = convertView
        }

        val boardViewHolder = BoardListViewHolder(
                view.findViewById(R.id.board_category_name)
                , view.findViewById(R.id.board_list_title)
                , view.findViewById(R.id.board_list_register)
                , view.findViewById(R.id.board_list_registered)
                , view.findViewById(R.id.board_list_image)

        )

        view.tag = boardViewHolder

        val categoryName = (view.tag as BoardListViewHolder).categoryName
        val title = (view.tag as BoardListViewHolder).title
        val register = (view.tag as BoardListViewHolder).register
        val registered = (view.tag as BoardListViewHolder).registered
        val image = (view.tag as BoardListViewHolder).image

        val currentBoard = boardsByCategory[position]

        categoryName.text = currentBoard.category.name
        title.text = currentBoard.title
        register.text = currentBoard.register
        registered.text = currentBoard.registered

        // TODO 이미지가 복사된다 ;
        if(currentBoard.image?.isNotEmpty()) {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            image.setImageBitmap(BitmapFactory.decodeStream(URL(currentBoard.image).openConnection().getInputStream()));
        }

        return view
    }
}



