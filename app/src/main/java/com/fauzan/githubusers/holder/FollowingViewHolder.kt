package com.fauzan.githubusers.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.fauzan.githubusers.model.Following
import kotlinx.android.synthetic.main.list_following.view.*

class FollowingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(model: Following) {
        itemView.tv_loginFollowing.text = model.login
        itemView.tv_avatarFollowing.loadImage(model.avatar)
    }
}
