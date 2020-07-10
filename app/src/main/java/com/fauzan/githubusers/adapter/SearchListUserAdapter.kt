package com.fauzan.githubusers.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fauzan.githubusers.R
import com.fauzan.githubusers.holder.SearchUserViewHolder
import com.fauzan.githubusers.model.UserList
import com.fauzan.githubusers.ui.DetailUserActivity

class SearchListUserAdapter : RecyclerView.Adapter<SearchUserViewHolder>() {

    private val listUser = ArrayList<UserList>()
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setData(item: ArrayList<UserList>) {
        listUser.clear()
        listUser.addAll(item)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchUserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_gituser, parent, false)
        return SearchUserViewHolder(view)
    }

    override fun getItemCount(): Int = listUser.size

    override fun onBindViewHolder(holder: SearchUserViewHolder, position: Int) {
        val list = listUser[position]

        holder.bind(list)

        holder.itemView.setOnClickListener { view ->
            onItemClickCallback.onItemClicked(listUser[position])
            val moveToDetailUser =
                Intent(view.context.applicationContext, DetailUserActivity::class.java)
            moveToDetailUser.putExtra(DetailUserActivity.EXTRA_LOGIN, list.login)
            view.context.startActivity(moveToDetailUser)
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: UserList)
    }

}