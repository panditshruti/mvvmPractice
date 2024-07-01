package com.rajnish.mydairy.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rajnish.mydairy.R
import com.rajnish.mydairy.adapter.interfaceForListner.UserProfileListener
import com.rajnish.mydairy.db.PostX
import com.rajnish.mydairy.db.ReactionsX

class PostAdapter(private val posts: List<PostX>,  private val userProfileListener: UserProfileListener) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = posts[position]
        holder.bind(post,userProfileListener)


    }
    override fun getItemCount(): Int = posts.size

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.post_title)
        private val body: TextView = itemView.findViewById(R.id.post_body)
        private val tagsLayout: LinearLayout = itemView.findViewById(R.id.post_tags)
        private val views: TextView = itemView.findViewById(R.id.post_views)
        private val reactions: TextView = itemView.findViewById(R.id.post_reactions)
        private val user: TextView = itemView.findViewById(R.id.post_user)




        @SuppressLint("SetTextI18n")
        fun bind(post: PostX,userProfileListener:UserProfileListener) {
            title.text = post.title
            body.text = post.body
            views.text = "Views: ${post.views}"
            reactions.text = "likes: ${post.reactions.likes} dislikes : ${post.reactions.dislikes}"
            user.text = "User ID: ${post.userId}"



            // Clear existing tags
            tagsLayout.removeAllViews()
            // Add tags
            for (tag in post.tags) {
                val tagView = TextView(itemView.context).apply {
                    text = tag
                    setPadding(8, 4, 8, 4)
                    setBackgroundResource(R.drawable.tag_background)
                }
                tagsLayout.addView(tagView)
            }
            user.setOnClickListener {
                userProfileListener.onUserProfileListener(post.userId.toString())
            }
        }
    }
}
