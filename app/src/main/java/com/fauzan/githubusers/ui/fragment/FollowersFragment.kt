package com.fauzan.githubusers.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fauzan.githubusers.R
import com.fauzan.githubusers.adapter.FollowersAdapter
import com.fauzan.githubusers.model.Followers
import com.fauzan.githubusers.viewmodel.FollowersViewModel
import kotlinx.android.synthetic.main.fragment_followers.*

class FollowersFragment : Fragment() {

    private lateinit var adapter: FollowersAdapter
    private lateinit var followersViewModel: FollowersViewModel

    companion object {
        const val EXTRA_LOGIN = "extra_login"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_followers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Get Username/Login from DetailUserActivity
        val name = requireActivity().intent.getStringExtra(EXTRA_LOGIN)

        //Get viewModel
        followersViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(FollowersViewModel::class.java)

        //Set value
        loading(true)
        followersViewModel.setFollowers(name)

        //Observe Change
        followersViewModel.getFollowers().observe(viewLifecycleOwner, Observer { followerList ->
            if (!followerList.isNullOrEmpty()) {
                adapter.setData(followerList)
                loading(false)
            } else {
                Toast.makeText(context, "$name dont have any followers", Toast.LENGTH_SHORT).show()
                loading(false)

            }
        })

        //show List followers
        rv_followers.setHasFixedSize(true)
        showFollowersList()

    }

    private fun showFollowersList() {
        adapter = FollowersAdapter()
        adapter.notifyDataSetChanged()

        rv_followers.layoutManager = LinearLayoutManager(context)
        rv_followers.adapter = adapter

        adapter.setOnItemClickCallBack(object : FollowersAdapter.OnItemClickCallBack {
            override fun onItemClick(data: Followers) {

            }
        })
    }

    private fun loading(state: Boolean) {
        if (state) progressBarFollowers.visibility = View.VISIBLE
        else progressBarFollowers.visibility = View.INVISIBLE
    }

}