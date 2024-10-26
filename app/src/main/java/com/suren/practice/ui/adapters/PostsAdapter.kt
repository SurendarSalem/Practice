package com.suren.practice.ui.adapters

import android.view.ViewGroup

import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatCheckBox
import com.suren.practice.R
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.suren.practice.data.Post

class PostsAdapter(private val onClick: (Post) -> Unit, private val layout: Int) :
    ListAdapter<Post, PostsAdapter.PostViewHolder>(PostDiffCallback) {

    class PostViewHolder(itemView: View, val onClick: (Post) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val tvId: TextView = itemView.findViewById(R.id.tv_id)
        private val tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        private val cbCompleted: AppCompatCheckBox = itemView.findViewById(R.id.cb_completed)
        private var currentPost: Post? = null

        init {
            itemView.setOnClickListener {
                currentPost?.let {
                    onClick(it)
                }
            }
        }

        fun bind(post: Post) {
            currentPost = post
            tvId.text = post.id.toString()
            tvTitle.text = post.title
            cbCompleted.isChecked = post.completed == true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return PostViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        post?.let {
            holder.bind(it)
        }
    }
}

object PostDiffCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }
}