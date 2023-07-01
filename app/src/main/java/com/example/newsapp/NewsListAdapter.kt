package com.example.newsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsListAdapter(private val listener: NewsItemClicked): RecyclerView.Adapter<NewsViewHolder>() {

    private val items: ArrayList<News> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        // layout inflator converts xml to View
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)
        val viewHolder = NewsViewHolder(view)
        view.setOnClickListener{
            /*
                once the item is clicked anything happening after that
                should be handled by main-activity not by adapter
                So there should be a mechanism that will tell activity
                that an item is clicked which is referred to as callback

                Callback is made using an interface
            */

            listener.onItemClicked(items[viewHolder.bindingAdapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        // This function is called only once to get count of items to be shown on screen
        return items.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val  currentItem = items[position]
//        holder.titleView.text = currentItem
        holder.titleView.text = currentItem.title
        holder.author.text = currentItem.author
        Glide.with(holder.itemView.context).load(currentItem.urlToImage).into(holder.image)
    }

    fun updateNews(updatedNews: ArrayList<News>){
        items.clear()
        items.addAll(updatedNews)

        // This will invoke all the above three methods again
        notifyDataSetChanged()
    }
}
//This will have all the items which we have to inflate
class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val titleView: TextView = itemView.findViewById(R.id.title)
    val image: ImageView = itemView.findViewById(R.id.image)
    val author: TextView = itemView.findViewById(R.id.author)
}

interface NewsItemClicked{
    // interface just keeps track of what type of callback method we wish to use
    fun onItemClicked(item: News)
}