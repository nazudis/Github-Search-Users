package com.fauzan.githubusers.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.fauzan.githubusers.model.Followers
import kotlinx.android.synthetic.main.list_followers.view.*

class FollowersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(model: Followers) {
        itemView.tv_loginFollowers.text = model.login
        itemView.tv_avatarFollowers.loadImage(model.avatar)
    }
}
