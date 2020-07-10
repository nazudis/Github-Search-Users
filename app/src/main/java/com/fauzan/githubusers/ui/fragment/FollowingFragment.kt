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
import com.fauzan.githubusers.adapter.FollowingAdapter
import com.fauzan.githubusers.model.Following
import com.fauzan.githubusers.viewmodel.FollowingViewModel
import kotlinx.android.synthetic.main.fragment_following.*

class FollowingFragment : Fragment() {

    private lateinit var adapter: FollowingAdapter
    private lateinit var followingViewModel: FollowingViewModel

    companion object {
        const val EXTRA_LOGIN = "extra_login"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_following, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Get Username/Login from DetailUserActivity
        val name = requireActivity().intent.getStringExtra(EXTRA_LOGIN)

        //Get ViewModel
        followingViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(FollowingViewModel::class.java)

        //SetValue
        loading(true)
        followingViewModel.setFollowing(name)

        //Observe Change
        followingViewModel.getFollowing().observe(viewLifecycleOwner, Observer { followingList ->
            if (!followingList.isNullOrEmpty()) {
                adapter.setData(followingList)
                loading(false)
            } else {
                Toast.makeText(context, "$name dont have any following", Toast.LENGTH_SHORT).show()
                loading(false)
            }
        })

        //show List Following
        rv_following.setHasFixedSize(true)
        showRecyclerlist()

    }

    private fun showRecyclerlist() {
        adapter = FollowingAdapter()
        adapter.notifyDataSetChanged()

        rv_following.layoutManager = LinearLayoutManager(context)
        rv_following.adapter = adapter

        adapter.setOnItemClickCallBack(object : FollowingAdapter.OnItemClickCallBack {
            override fun onItemClick(data: Following) {
            }

        })
    }

    private fun loading(state: Boolean) {
        if (state) progressBarFollowing.visibility = View.VISIBLE
        else progressBarFollowing.visibility = View.INVISIBLE
    }

}