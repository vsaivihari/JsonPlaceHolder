package com.firstorion.project.ui.post

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firstorion.project.R
import com.firstorion.project.repo.post.Post

/**
 * Feel free to change as you wish
 * */
class PostsRVAdapter(
    private val postClickedListener: OnPostClickedListener
) : RecyclerView.Adapter<PostsRVAdapter.PostViewHolder>() {

    var posts: MutableList<Post> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.post_item, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(posts[position], postClickedListener)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    inner class PostViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val userId: TextView = v.findViewById(R.id.user_id)
        private val postTitle: TextView = v.findViewById(R.id.post_title)
        private val postBody: TextView = v.findViewById(R.id.post_body)


        fun bind(post: Post, postClickedListener: OnPostClickedListener) {
            userId.text = post.userId.toString()
            postTitle.text = post.title
            postBody.text = post.body
            itemView.setOnClickListener {
                postClickedListener.onPostClicked(post)
            }
        }
    }

    interface OnPostClickedListener {
        fun onPostClicked(post: Post)
    }


}