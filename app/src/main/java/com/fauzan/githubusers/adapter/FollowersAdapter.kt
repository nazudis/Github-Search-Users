package com.fauzan.githubusers.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fauzan.githubusers.R
import com.fauzan.githubusers.holder.FollowersViewHolder
import com.fauzan.githubusers.model.Followers
import com.fauzan.githubusers.ui.DetailUserActivity

class FollowersAdapter : RecyclerView.Adapter<FollowersViewHolder>() {

    private val followerList = ArrayList<Followers>()
    private lateinit var onItemClickCallBack: OnItemClickCallBack

    fun setOnItemClickCallBack(onItemClickCallBack: OnItemClickCallBack) {
        this.onItemClickCallBack = onItemClickCallBack
    }

    fun setData(item: ArrayList<Followers>) {
        followerList.clear()
        followerList.addAll(item)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowersViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_followers, parent, false)
        return FollowersViewHolder(view)
    }

    override fun getItemCount(): Int = followerList.size

    override fun onBindViewHolder(holder: FollowersViewHolder, position: Int) {
        val list = followerList[position]

        holder.bind(list)
        holder.itemView.setOnClickListener { view ->
            onItemClickCallBack.onItemClick(followerList[position])
            val moveToDetailUser =
                Intent(view.context.applicationContext, DetailUserActivity::class.java)
            moveToDetailUser.putExtra(DetailUserActivity.EXTRA_LOGIN, list.login)
            view.context.startActivity(moveToDetailUser)
        }
    }

    interface OnItemClickCallBack {
        fun onItemClick(data: Followers)
    }

}