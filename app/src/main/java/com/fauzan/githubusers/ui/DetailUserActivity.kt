package com.fauzan.githubusers.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.fauzan.githubusers.R
import com.fauzan.githubusers.model.DetailUser
import com.fauzan.githubusers.ui.fragment.FollowersFragment
import com.fauzan.githubusers.ui.fragment.FollowingFragment
import com.fauzan.githubusers.viewmodel.DetailUserViewModel
import kotlinx.android.synthetic.main.activity_detail_user.*

open class DetailUserActivity : AppCompatActivity() {

    private lateinit var detailUserViewModel: DetailUserViewModel

    companion object {
        const val EXTRA_LOGIN = "extra_login"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_user)

        loading(true)

        //Get Login/username from search user
        val name = intent.getStringExtra(EXTRA_LOGIN)

        //Set up Toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar_detail)
        setSupportActionBar(toolbar)
        if (supportActionBar != null) {
            title = name
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
            toolbar.setNavigationOnClickListener { finish() }
        }

        //Get ViewModel
        detailUserViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(DetailUserViewModel::class.java)

        detailUserViewModel.setDataUser(name)

        //Observe Change
        detailUserViewModel.getDataUser().observe(this, Observer { user ->
            setViewDetailUser(user)
            loading(false)
        })

        moveToFollowActivity()

    }

    //Set View Detail User
    private fun setViewDetailUser(detailUser: DetailUser?) {

        if (detailUser != null) {
            tv_login_detail.text = detailUser.login
            tv_name.text = detailUser.name
            tv_company_user.text = detailUser.company
            tv_location.text = detailUser.location
            tv_repo.text = detailUser.repo.toString()
            tv_followers.text = detailUser.followers.toString()
            tv_following.text = detailUser.following.toString()

            Glide.with(this)
                .load(detailUser.avatar)
                .into(tv_avatar_detail)

        }

        val nothing = getString(R.string.nothing)
        if (detailUser?.login == null) tv_login_detail.text = nothing
        if (detailUser?.name == null) tv_name.text = nothing
        if (detailUser?.company == null) tv_company_user.text = nothing
        if (detailUser?.location == null) tv_location.text = nothing

        //Set Up Profile Info Title
        tv_followersTitle.text = getString(R.string.followers)
        tv_followingTitle.text = getString(R.string.following)
        tv_repoTitle.text = getString(R.string.repos)
        tv_company.text = getString(R.string.company)

    }

    private fun loading(state: Boolean) {
        if (state) progressBarDetail.visibility = View.VISIBLE
        else progressBarDetail.visibility = View.INVISIBLE
    }

    //Set Intent move to Followers and Following List
    private fun moveToFollowActivity() {
        //Get Username to Send to the FollowActivity
        val login = intent.getStringExtra(EXTRA_LOGIN)

        followers_btn.setOnClickListener { view ->
            val move = Intent(view.context.applicationContext, FollowActivity::class.java)
            move.putExtra(FollowActivity.EXTRA_LOGIN, login)
            move.putExtra(FollowersFragment.EXTRA_LOGIN, login)
            move.putExtra(FollowingFragment.EXTRA_LOGIN, login)
            view.context.startActivity(move)

        }

        following_btn.setOnClickListener { view ->
            val defaultPage = 1
            val move = Intent(view.context.applicationContext, FollowActivity::class.java)
            move.putExtra(FollowActivity.EXTRA_LOGIN, login)
            move.putExtra(FollowActivity.EXTRA_PAGE, defaultPage)
            move.putExtra(FollowersFragment.EXTRA_LOGIN, login)
            move.putExtra(FollowingFragment.EXTRA_LOGIN, login)
            view.context.startActivity(move)

        }

    }
}