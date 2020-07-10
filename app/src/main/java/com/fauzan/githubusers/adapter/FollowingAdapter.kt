package com.fauzan.githubusers.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fauzan.githubusers.R
import com.fauzan.githubusers.holder.FollowingViewHolder
import com.fauzan.githubusers.model.Following
import com.fauzan.githubusers.ui.DetailUserActivity

class FollowingAdapter : RecyclerView.Adapter<FollowingViewHolder>() {

    private val followingList = ArrayList<Following>()
    private lateinit var onItemClickCallBack: OnItemClickCallBack

    fun setOnItemClickCallBack(onItemClickCallBack: OnItemClickCallBack) {
        this.onItemClickCallBack = onItemClickCallBack
    }

    fun setData(item: ArrayList<Following>) {
        followingList.clear()
        followingList.addAll(item)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowingViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_following, parent, false)
        return FollowingViewHolder(view)
    }

    override fun getItemCount(): Int = followingList.size

    override fun onBindViewHolder(holder: FollowingViewHolder, position: Int) {
        val list = followingList[position]

        holder.bind(list)
        holder.itemView.setOnClickListener { view ->
            onItemClickCallBack.onItemClick(followingList[position])
            val moveToDetailUser =
                Intent(view.context.applicationContext, DetailUserActivity::class.java)
            moveToDetailUser.putExtra(DetailUserActivity.EXTRA_LOGIN, list.login)
            view.context.startActivity(moveToDetailUser)
        }
    }

    interface OnItemClickCallBack {
        fun onItemClick(data: Following)
    }
}