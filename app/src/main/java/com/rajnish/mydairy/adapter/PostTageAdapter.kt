package com.rajnish.mydairy.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rajnish.mydairy.R
import com.rajnish.mydairy.adapter.interfaceForListner.PostTagItemClickListner
import com.rajnish.mydairy.db.PostTag
import com.rajnish.mydairy.db.PostTagItem

class PostTagAdapter(private val items: PostTag,  private val postTagItemlistener: PostTagItemClickListner) : RecyclerView.Adapter<PostTagAdapter.PostTagViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostTagViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post_tag, parent, false)
        return PostTagViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostTagViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)

        holder.itemView.setOnClickListener {
            postTagItemlistener.onPostTagItemClickListner(item.url,item.slug)
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

    class PostTagViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewName: TextView = itemView.findViewById(R.id.postTagName)


        fun bind(item: PostTagItem) {
            textViewName.text = item.name


        }



    }
}
