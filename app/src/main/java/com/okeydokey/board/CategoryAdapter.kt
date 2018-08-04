package com.okeydokey.board

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

data class Category(val no: Long, val name: String, val description: String)
data class CategoryViewHolder(val categoryName: TextView, val categoryDescription: TextView)

val categories = arrayOf(
        Category( 1,"A카테고리", "A설명")
        , Category( 2,"B카테고리", "B설명")
        , Category( 3,"C카테고리", "C설명")
        , Category( 4,"D카테고리", "D설명")
        , Category( 5,"E카테고리", "E설명")
)

class CategoryAdapter(private val mContext: Context) : BaseAdapter() {

    override fun getCount(): Int = categories.size

    override fun getItem(position: Int): Any? = categories[position]

    override fun getItemId(position: Int): Long = categories[position].no

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val view : View

        if (convertView == null) {
            val inflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.category_item, null, false)
        } else {
            view = convertView
        }

        val categoryViewHolder = CategoryViewHolder(view.findViewById(R.id.category_name), view.findViewById(R.id.category_description))

        view.tag = categoryViewHolder

        val categoryName = (view.tag as CategoryViewHolder).categoryName
        val categoryDescription = (view.tag as CategoryViewHolder).categoryDescription

        val currentCategory = categories[position]

        categoryName.text = currentCategory.name
        categoryDescription.text = currentCategory.description

        return view
    }
}



